package com.bmw.psmg.sbb.utilities.loaders.rules;

import com.bmw.psmg.sbb.utilities.loaders.LoadFileMarshaller;
import com.bmw.psmg.sbb.utilities.loaders.option.ExcelReader;
import com.bmw.psmg.sbb.utilities.loaders.rules.jaxb.*;

import com.ptc.windchill.option.model.ChoiceMaster;
import com.ptc.windchill.option.model.ExpressionAliasMaster;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/**
 * Tool converting BMW Alias Expression Excel from Variant Specification file to Windchill LoadFile XML file.
 * Sample usage: windchill com.bmw.psmg.sbb.utilities.loaders.rules.ConditionalRuleFromVariantSpecificationLoader -f [excel-file-path] -c [wt-container-path]
 *
 * @author Bartlomiej Dobrzyjalowski
 */
public class ConditionalRuleFromVariantSpecificationLoader {

    private static final Logger LOGGER = Logger.getLogger(ConditionalRuleFromVariantSpecificationLoader.class.getName());

    private static final ConsoleAppender CONSOLE_ADAPTER = new ConsoleAppender(new PatternLayout());
    private static final String CONDITIONALS_ARGUMENT_FILE_PATH = "f";
    private static final String CONDITIONALS_ARGUMENT_CONTAINER_PATH = "c";
    private static final String NAME_PREFIX = "_DESCRIBING";

    private static final int OPTION_AND_CHOICE_TAB_INDEX = 0;
    private static final int START_COLUMN_INDEX = 3;
    private static final int END_COLUMN_INDEX = 657;
    private static final int SPACE_BETWEEN_COLUMNS = 3;

    private static final int TYP_NR_INDEX = 1;
    private static final int TYP_KAROSS_INDEX = 5;
    private static final int TYP_KARAUSF_INDEX = 6;
    private static final int TYP_TUER_INDEX = 7;
    private static final int TYP_PL_INDEX = 15;
    private static final int TYP_ABGAS_INDEX = 17;
    private static final int TYP_MARKE_INDEX = 20;
    private static final int TYP_MODKAT_INDEX = 24;

    private static final List<Integer> ACTION_MEMBER_INDEXES = Arrays.asList(TYP_KAROSS_INDEX, TYP_KARAUSF_INDEX, TYP_TUER_INDEX, TYP_PL_INDEX, TYP_ABGAS_INDEX, TYP_MARKE_INDEX, TYP_MODKAT_INDEX);

    private static LoadFileMarshaller loadFileMarshaller = new LoadFileMarshaller();

    /**
     * The method starts converting BMW Conditional Rules from Variant Specification Excel file to Windchill Load File XML file.
     *
     * @param args contains all input arguments from Windchill Shell
     */
    public static void main(String[] args) {
        CONSOLE_ADAPTER.setThreshold(Level.INFO);
        LOGGER.setLevel(Level.INFO);
        LOGGER.addAppender(CONSOLE_ADAPTER);
        execute(args);
    }

    private static void execute(String[] args) {
        Options options = new Options();
        options.addOption(CONDITIONALS_ARGUMENT_FILE_PATH, null, true, "The file path of the input Excel sheet file");
        options.addOption(CONDITIONALS_ARGUMENT_CONTAINER_PATH, null, true, "The container path of the exporting rules.");

        CommandLineParser commandLineParser = new DefaultParser();
        try {
            CommandLine line = commandLineParser.parse(options, args);
            if (validateArgs(line)) {
                Map<String, List<String>> conditionalRules = parse(line.getOptionValue(CONDITIONALS_ARGUMENT_FILE_PATH));
                generate(conditionalRules, line.getOptionValue(CONDITIONALS_ARGUMENT_CONTAINER_PATH));
            } else {
                String header = "Tool converting BMW Conditional Rules Excel from Variant Specification file to Windchill LoadFile XML file.";
                new HelpFormatter().printHelp("windchill shell", header, options, "");
            }
        } catch (ParseException e) {
            LOGGER.error("Cannot parse argument", e);
        }
    }

    private static boolean validateArgs(CommandLine line) {
        return line.hasOption(CONDITIONALS_ARGUMENT_FILE_PATH) && line.hasOption(CONDITIONALS_ARGUMENT_CONTAINER_PATH);
    }

    private static Map<String, List<String>> parse(String filePath) {
        Map<String, List<String>> conditionalRules = new HashMap<>();

        try (ExcelReader excelReader = new ExcelReader(new File(filePath))) {
            Sheet sheet = excelReader.getSheet(OPTION_AND_CHOICE_TAB_INDEX);
            FormulaEvaluator formulaEvaluator = excelReader.getFormulaEvaluator();
            for (int index = START_COLUMN_INDEX; index <= END_COLUMN_INDEX; index += SPACE_BETWEEN_COLUMNS) {
                String ruleName = ExcelReader.evaluateValue(formulaEvaluator, sheet.getRow(TYP_NR_INDEX).getCell(index));
                conditionalRules.putIfAbsent(ruleName, new LinkedList<>());
                conditionalRules.get(ruleName).addAll(getActionMembers(formulaEvaluator, sheet, index));
            }

        } catch (IOException e) {
            LOGGER.error("Cannot load Excel file.", e);
        }

        return conditionalRules;
    }

    private static Collection<String> getActionMembers(FormulaEvaluator formulaEvaluator, Sheet excelSheet, int cellId) {
        List<String> actionMembers = new LinkedList<>();
        for (Integer id : ACTION_MEMBER_INDEXES) {
            actionMembers.add(ExcelReader.evaluateValue(formulaEvaluator, excelSheet.getRow(id).getCell(cellId)));
        }
        return actionMembers;
    }

    private static void generate(Map<String, List<String>> conditionals, String containerPath) {
        CsvConditionalRule conditionalRule = new CsvConditionalRule();
        List<Object> objects = new LinkedList<>();

        for (Map.Entry<String, List<String>> entry : conditionals.entrySet()) {
            String name = entry.getKey();
            objects.add(new CsvBeginConditionalRule(name + NAME_PREFIX));
            objects.add(new CsvBeginAssignExpression());
            objects.add(new CsvAddLogicalExpression("", "\"" + name + "\""));
            objects.add(new CsvAddLogicalExpressionMember(name, ExpressionAliasMaster.class.getName(), containerPath));
            objects.add(new CsvEndLogicalExpression());
            objects.add(new CsvEndAssignExpression());
            objects.add(new CsvBeginRuleMemberAction());
            for (String choice : entry.getValue()) {
                objects.add(new CsvAddRuleActionMember(choice, ChoiceMaster.class.getName(), containerPath));
            }
            objects.add(new CsvEndRuleMemberAction());
            objects.add(new CsvEndConditionalRule());
        }
        conditionalRule.setObjects(objects);

        try {
            String output = loadFileMarshaller.marshal("WT_CarTypeConditionalRules", JAXBContext.newInstance(CsvConditionalRule.class, CsvBeginConditionalRule.class, CsvBeginAssignExpression.class,
                    CsvAddLogicalExpression.class, CsvAddLogicalExpressionMember.class, CsvEndLogicalExpression.class, CsvEndAssignExpression.class, CsvBeginRuleMemberAction.class,
                    CsvAddRuleActionMember.class, CsvEndRuleMemberAction.class, CsvEndConditionalRule.class), conditionalRule);
            LOGGER.info("GENERATION SUCCESSFUL");
            LOGGER.info("Output file:\n" + output);
        } catch (JAXBException e) {
            LOGGER.error("Cannot marshal JAXB Conditional Rules", e);
        }
    }


}
