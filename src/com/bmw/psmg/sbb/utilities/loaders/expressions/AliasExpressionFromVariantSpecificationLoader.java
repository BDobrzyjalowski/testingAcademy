package com.bmw.psmg.sbb.utilities.loaders.expressions;

import com.bmw.psmg.sbb.utilities.loaders.LoadFileMarshaller;
import com.bmw.psmg.sbb.utilities.loaders.expressions.jaxb.CsvAliasExpression;
import com.bmw.psmg.sbb.utilities.loaders.expressions.jaxb.CsvBeginAlias;
import com.bmw.psmg.sbb.utilities.loaders.expressions.jaxb.CsvEndAlias;
import com.bmw.psmg.sbb.utilities.loaders.option.ExcelReader;

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
 * Sample usage: windchill com.bmw.psmg.sbb.utilities.loaders.expressions.AliasExpressionFromVariantSpecificationLoader -f [excel-file-path] -c [wt-container-path]
 *
 * @author Karol Poliszkiewicz
 */
public class AliasExpressionFromVariantSpecificationLoader {

    private static final Logger LOGGER = Logger.getLogger(AliasExpressionFromVariantSpecificationLoader.class.getName());

    private static final ConsoleAppender CONSOLE = new ConsoleAppender(new PatternLayout(""));
    private static final String ARGUMENT_FILE_PATH = "f";
    private static final String ARGUMENT_CONTAINER_PATH = "c";
    private static final String AND_OPERATOR = " AND ";

    private static final int OPTION_AND_CHOICE_TAB_INDEX = 0;
    private static final int START_COLUMN_INDEX = 3;
    private static final int END_COLUMN_INDEX = 657;
    private static final int SPACE_BETWEEN_COLUMNS = 3;

    private static final int TYP_NR_INDEX = 1;
    private static final int TYP_ENTW_BEZ_INDEX = 2;
    private static final int TYP_LAND_INDEX = 3;
    private static final int TYP_LENKUNG_INDEX = 4;
    private static final int TYP_KAROSS_INDEX = 5;
    private static final int TYP_KARAUSF_INDEX = 6;
    private static final int TYP_TUER_INDEX = 7;
    private static final int TYP_LCI_INDEX = 8;
    private static final int TYP_ANTRIEB_INDEX = 9;
    private static final int TYP_GETR_INDEX = 10;
    private static final int TYP_TSA_INDEX = 13;
    private static final int TYP_MONT_LAND = 14;
    private static final int TYP_PL_LAND = 15;
    private static final int TYP_HYBRID_INDEX = 16;
    private static final int TYP_ABGAS_INDEX = 17;
    private static final int TYP_ERW_INDEX = 18;
    private static final int TYP_FZGZUSDF_INDEX = 19;
    private static final int TYP_MARKE_INDEX = 20;
    private static final int TYP_SICHFZG_INDEX = 21;
    private static final int TYP_MSPORT_INDEX = 22;
    private static final int TYP_DERFAM_INDEX = 23;
    private static final int TYP_MODKAT_INDEX = 24;
    private static final int TYP_TEMPDIFF_INDEX = 29;
    private static final int TYP_MODAUSF_INDEX = 30;
    private static final int TYP_MOTOR_INDEX = 31;

    private static final List<Integer> CHOICE_INDEXES = Arrays.asList(TYP_ENTW_BEZ_INDEX, TYP_LAND_INDEX, TYP_LENKUNG_INDEX, TYP_KAROSS_INDEX, TYP_KARAUSF_INDEX, TYP_TUER_INDEX, TYP_LCI_INDEX,
            TYP_ANTRIEB_INDEX, TYP_GETR_INDEX, TYP_TSA_INDEX, TYP_MONT_LAND, TYP_PL_LAND, TYP_HYBRID_INDEX, TYP_ABGAS_INDEX, TYP_ERW_INDEX, TYP_FZGZUSDF_INDEX, TYP_MARKE_INDEX, TYP_SICHFZG_INDEX,
            TYP_MSPORT_INDEX, TYP_DERFAM_INDEX, TYP_MODKAT_INDEX, TYP_TEMPDIFF_INDEX, TYP_MODAUSF_INDEX, TYP_MOTOR_INDEX);

    private static LoadFileMarshaller loadFileMarshaller = new LoadFileMarshaller();

    /**
     * The method starts converting BMW Alias Expressions from Variant Specification Excel file to Windchill Load File XML file.
     *
     * @param args contains all input arguments from Windchill Shell
     */
    public static void main(String[] args) {
        CONSOLE.setThreshold(Level.INFO);
        LOGGER.setLevel(Level.INFO);
        LOGGER.addAppender(CONSOLE);
        execute(args);
    }

    private static void execute(String[] args) {
        Options options = new Options();
        options.addOption(ARGUMENT_FILE_PATH, null, true, "The file path of the input Excel sheet file");
        options.addOption(ARGUMENT_CONTAINER_PATH, null, true, "The container path of the exporting aliases.");

        CommandLineParser commandLineParser = new DefaultParser();
        try {
            CommandLine line = commandLineParser.parse(options, args);
            if (validateArgs(line)) {
                Map<String, List<String>> aliasExpressions = parse(line.getOptionValue(ARGUMENT_FILE_PATH));
                generate(aliasExpressions, line.getOptionValue(ARGUMENT_CONTAINER_PATH));
            } else {
                String header = "Tool converting BMW Alias Expressions Excel from Variant Specification file to Windchill LoadFile XML file.";
                new HelpFormatter().printHelp("windchill shell", header, options, "");
            }
        } catch (ParseException e) {
            LOGGER.error("Cannot parse argument", e);
        }
    }

    private static boolean validateArgs(CommandLine line) {
        return line.hasOption(ARGUMENT_FILE_PATH) && line.hasOption(ARGUMENT_CONTAINER_PATH);
    }

    private static Map<String, List<String>> parse(String filePath) {
        Map<String, List<String>> aliasExpressions = new HashMap<>();

        try (ExcelReader excelReader = new ExcelReader(new File(filePath))) {
            Sheet sheet = excelReader.getSheet(OPTION_AND_CHOICE_TAB_INDEX);
            FormulaEvaluator formulaEvaluator = excelReader.getFormulaEvaluator();

            for (int index = START_COLUMN_INDEX; index <= END_COLUMN_INDEX; index += SPACE_BETWEEN_COLUMNS) {
                String name = ExcelReader.evaluateValue(formulaEvaluator, sheet.getRow(TYP_NR_INDEX).getCell(index));
                aliasExpressions.putIfAbsent(name, new LinkedList<>());
                aliasExpressions.get(name).addAll(getChoices(formulaEvaluator, sheet, index));
            }

        } catch (IOException e) {
            LOGGER.error("Cannot load Excel file.", e);
        }

        return aliasExpressions;
    }

    private static Collection<String> getChoices(FormulaEvaluator formulaEvaluator, Sheet sheet, int cellIndex) {
        List<String> choices = new LinkedList<>();
        for (Integer index : CHOICE_INDEXES) {
            String choice = ExcelReader.evaluateValue(formulaEvaluator, sheet.getRow(index).getCell(cellIndex));
            choices.add(choice);
        }
        return choices;
    }

    private static void generate(Map<String, List<String>> aliasExpressions, String containerPath) {
        CsvAliasExpression aliasExpression = new CsvAliasExpression();
        List<Object> objects = new LinkedList<>();

        for (Map.Entry<String, List<String>> entry : aliasExpressions.entrySet()) {
            String name = entry.getKey();
            StringBuilder expression = new StringBuilder();
            for (String choice : entry.getValue()) {
                expression.append("\"");
                expression.append(choice);
                expression.append("\"");
                expression.append(AND_OPERATOR);
            }
            expression.setLength(expression.length() - AND_OPERATOR.length());

            objects.add(new CsvBeginAlias(name, expression.toString(), containerPath));
            objects.add(new CsvEndAlias());
        }

        aliasExpression.setObjects(objects);

        try {
            String output = loadFileMarshaller.marshal("WT_CarTypeAliasExpressions", JAXBContext.newInstance(CsvAliasExpression.class, CsvBeginAlias.class, CsvEndAlias.class), aliasExpression);
            LOGGER.info("GENERATION SUCCESSFUL");
            LOGGER.info("Output file:\n" + output);
        } catch (JAXBException e) {
            LOGGER.error("Cannot marshal JAXB Alias Expressions", e);
        }
    }


}
