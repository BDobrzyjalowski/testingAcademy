package com.bmw.psmg.sbb.utilities.loaders.expressions;

import com.bmw.psmg.sbb.utilities.ExpressionUtils;
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
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/**
 * Tool converting BMW Alias Expression Excel file to Windchill LoadFile XML file.
 * Sample usage: windchill com.bmw.psmg.sbb.utilities.loaders.expressions.AliasExpressionsLoadFileFromExcelGenerator -f [excel-file-path]  -c [container-path]
 * <p>
 * Supported Excel columns:
 * Internal Name | Alias Number | Alias Name | Alias Description | Alias Definition
 *
 * @author Karol Poliszkiewicz
 * @see wt.load.LoadFromFile
 */
public class AliasExpressionsLoadFileFromExcelGenerator {

    private static final Logger LOGGER = Logger.getLogger(AliasExpressionsLoadFileFromExcelGenerator.class.getName());
    private static final ConsoleAppender CONSOLE = new ConsoleAppender(new PatternLayout(""));
    private static final String ARGUMENT_FILE_PATH = "f";
    private static final String ARGUMENT_CONTAINER_PATH = "c";
    private static final String ALIAS_EXPRESSION_TYPE_DEFINITION = "com.ptc.windchill.option.model.ExpressionAlias";
    private static final int SHEET_INDEX = 0;
    private static final int TYPE_DEFINITION_INDEX = 0;
    private static final int NUMBER_INDEX = 1;
    private static final int NAME_INDEX = 2;
    private static final int DESCRIPTION_INDEX = 3;
    private static final int EXPRESSION_INDEX = 4;

    private static LoadFileMarshaller loadFileMarshaller = new LoadFileMarshaller();

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
        options.addOption(ARGUMENT_FILE_PATH, null, true, "The file path of the input Excel sheet file.");
        options.addOption(ARGUMENT_CONTAINER_PATH, null, true, "The container path of the exporting aliases.");
        execute(args, options);
    }

    private static void execute(String[] args, Options options) {
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine line = parser.parse(options, args);
            if (validateArgs(line)) {
                List<Object> aliases = load(line.getOptionValue(ARGUMENT_FILE_PATH), line.getOptionValue(ARGUMENT_CONTAINER_PATH));
                String output = generate(aliases);
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

    private static List<Object> load(String filePath, String containerPath) {
        List<Object> aliases = new LinkedList<>();
        try (ExcelReader excelReader = new ExcelReader(new File(filePath))) {
            Sheet sheet = excelReader.getSheet(SHEET_INDEX);

            Iterator<Row> iterator = sheet.rowIterator();
            if (iterator.hasNext()) {
                iterator.next(); // skip header row
            }
            while (iterator.hasNext()) {
                Row current = iterator.next();
                CsvBeginAlias csvBeginAlias = parseRow(current, containerPath);
                aliases.add(csvBeginAlias);
                aliases.add(new CsvEndAlias());
            }
        } catch (IOException e) {
            LOGGER.error("Cannot load Excel file.", e);
        }
        return aliases;
    }

    private static CsvBeginAlias parseRow(Row row, String containerPath) {
        String typeDef = getTypeDefinition(row.getCell(TYPE_DEFINITION_INDEX).getStringCellValue());
        String number = row.getCell(NUMBER_INDEX).getStringCellValue();
        String name = row.getCell(NAME_INDEX).getStringCellValue();
        String description = row.getCell(DESCRIPTION_INDEX).getStringCellValue();

        String expression = "";
        Cell cell = row.getCell(EXPRESSION_INDEX);
        if (cell != null) {
            expression = ExpressionUtils.adjust(cell.getStringCellValue());
        }
        return new CsvBeginAlias(typeDef, name, number, description, expression, containerPath);
    }

    private static String getTypeDefinition(String typeDef) {
        String value;
        if (StringUtils.isNotEmpty(typeDef)) {
            value = ALIAS_EXPRESSION_TYPE_DEFINITION.concat("|").concat(typeDef);
        } else {
            value = ALIAS_EXPRESSION_TYPE_DEFINITION;
        }
        return value;
    }

    private static String generate(List<Object> aliases) {
        CsvAliasExpression aliasExpression = new CsvAliasExpression();
        aliasExpression.setObjects(aliases);
        try {
            return loadFileMarshaller.marshal("AliasExpressionOutput", JAXBContext.newInstance(CsvAliasExpression.class, CsvBeginAlias.class, CsvEndAlias.class), aliasExpression);
        } catch (JAXBException e) {
            LOGGER.error("Cannot marshal JAXB Alias Expressions", e);
        }
        return null;
    }

}
