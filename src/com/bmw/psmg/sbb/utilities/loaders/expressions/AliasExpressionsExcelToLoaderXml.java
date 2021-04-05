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
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/**
 * Tool converting BMW Alias Expression Excel file to Windchill LoadFile XML file.
 * Sample usage: windchill com.bmw.psmg.sbb.utilities.loaders.expressions.AliasExpressionsExcelToLoaderXml -f [excel-file-path] -s [excel-sheet-index] -c [wt-container-path]
 *
 * @author Karol Poliszkiewicz
 * @see wt.load.LoadFromFile
 */
public class AliasExpressionsExcelToLoaderXml {

    private static final Logger LOGGER = Logger.getLogger(AliasExpressionsExcelToLoaderXml.class.getName());
    private static final String AND_OPERATOR = " AND ";
    private static final String OR_OPERATOR = " OR ";
    private static final String CLASS_SUFFIX = "_MMG";

    private static final ConsoleAppender CONSOLE = new ConsoleAppender(new PatternLayout(""));

    private static final String ARGUMENT_FILE_PATH = "f";
    private static final String ARGUMENT_SHEET_INDEX = "s";
    private static final String ARGUMENT_CONTAINER_PATH = "c";
    private static final String CLASS_INDEX = "A";
    private static final String DERIVATE_INDEX = "B";

    private static LoadFileMarshaller loadFileMarshaller = new LoadFileMarshaller();
    private static Map<String, List<String>> aliases = new HashMap<>();

    static {
        CONSOLE.setThreshold(Level.INFO);
        LOGGER.setLevel(Level.INFO);
        LOGGER.addAppender(CONSOLE);
    }

    /**
     * The method starts converting BMW Alias Expressions from Excel file to Windchill Load File XML file.
     *
     * @param args contains all input arguments from Windchill Shell
     */
    public static void main(String[] args) {
        Options options = new Options();
        options.addOption(ARGUMENT_FILE_PATH, null, true, "The file path of the input Excel sheet file");
        options.addOption(ARGUMENT_SHEET_INDEX, null, true, "Index of selected sheet of input file.");
        options.addOption(ARGUMENT_CONTAINER_PATH, null, true, "The container path of the exporting aliases.");
        execute(args, options);
    }

    private static void execute(String[] args, Options options) {
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine line = parser.parse(options, args);
            if (validateArgs(line)) {
                int index = line.hasOption(ARGUMENT_SHEET_INDEX) ? Integer.valueOf(line.getOptionValue(ARGUMENT_SHEET_INDEX)) : 0;
                load(line.getOptionValue(ARGUMENT_FILE_PATH), index);
                String output = generate(aliases, line.getOptionValue(ARGUMENT_CONTAINER_PATH));
                LOGGER.info("GENERATION SUCCESSFUL");
                LOGGER.info("Output file:\n" + output);
            } else {
                printHelp(options);
            }
        } catch (ParseException e) {
            LOGGER.error("Cannot parse argument", e);
        }
    }

    private static boolean validateArgs(CommandLine line) {
        return line.hasOption(ARGUMENT_FILE_PATH) && line.hasOption(ARGUMENT_CONTAINER_PATH);
    }

    private static void printHelp(Options options) {
        String header = "Tool converting BMW Alias Expressions Excel file to Windchill LoadFile XML file.";
        new HelpFormatter().printHelp("windchill shell", header, options, "");
    }

    private static void load(String filePath, int index) {
        try (ExcelReader excelReader = new ExcelReader(new File(filePath))) {
            Sheet sheet = excelReader.getSheet(index);
            FormulaEvaluator formulaEvaluator = excelReader.getFormulaEvaluator();
            sheet.forEach(row -> parseRow(row, formulaEvaluator));
        } catch (IOException e) {
            LOGGER.error("Cannot load Excel file.", e);
        }
    }

    private static void parseRow(Row row, FormulaEvaluator formulaEvaluator) {
        String aliasClass = ExcelReader.evaluateValue(formulaEvaluator, row.getCell(ExcelReader.getColumnIndex(CLASS_INDEX)));
        String derivate = ExcelReader.evaluateValue(formulaEvaluator, row.getCell(ExcelReader.getColumnIndex(DERIVATE_INDEX)));
        aliases.putIfAbsent(aliasClass, new LinkedList<>());
        aliases.get(aliasClass).add(derivate);
    }

    private static String generate(Map<String, List<String>> aliasExpressions, String containerPath) {
        CsvAliasExpression aliasExpression = new CsvAliasExpression();
        List<Object> objects = new LinkedList<>();

        for (Map.Entry<String, List<String>> entry : aliasExpressions.entrySet()) {
            String name = entry.getKey();
            StringBuilder expression = new StringBuilder();
            expression.append("\"").append(StringUtils.removeEnd(name, CLASS_SUFFIX)).append("\""); // set name of class without '_MMG' suffix as first element of expression
            expression.append(AND_OPERATOR).append("(");
            for (String choice : entry.getValue()) {
                expression.append("\"");
                expression.append(choice);
                expression.append("\"");
                expression.append(OR_OPERATOR);
            }
            expression.setLength(expression.length() - OR_OPERATOR.length());  // remove last OR operator
            expression.append(")");

            objects.add(new CsvBeginAlias(name, expression.toString(), containerPath));
            objects.add(new CsvEndAlias());
        }

        aliasExpression.setObjects(objects);

        try {
            return loadFileMarshaller.marshal("WT_AliasExpressions", JAXBContext.newInstance(CsvAliasExpression.class, CsvBeginAlias.class, CsvEndAlias.class), aliasExpression);
        } catch (JAXBException e) {
            LOGGER.error("Cannot marshal JAXB Alias Expressions", e);
        }
        return null;
    }

}
