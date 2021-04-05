package com.bmw.psmg.sbb.utilities.loaders.option;

import com.bmw.psmg.sbb.generic.PSMGSBBConstants;
import com.bmw.psmg.sbb.utilities.loaders.LoadFileMarshaller;
import com.bmw.psmg.sbb.utilities.loaders.option.jaxb.CsvAddChoice;
import com.bmw.psmg.sbb.utilities.loaders.option.jaxb.CsvBeginChoice;
import com.bmw.psmg.sbb.utilities.loaders.option.jaxb.CsvBeginOption;
import com.bmw.psmg.sbb.utilities.loaders.option.jaxb.CsvBeginOptionSet;
import com.bmw.psmg.sbb.utilities.loaders.option.jaxb.CsvEndChoice;
import com.bmw.psmg.sbb.utilities.loaders.option.jaxb.CsvEndOption;
import com.bmw.psmg.sbb.utilities.loaders.option.jaxb.CsvEndOptionSet;
import com.bmw.psmg.sbb.utilities.loaders.option.jaxb.CsvOptionGroup;
import com.bmw.psmg.sbb.utilities.loaders.option.jaxb.CsvOptionPool;
import com.bmw.psmg.sbb.utilities.loaders.option.jaxb.CsvOptionSet;
import com.bmw.psmg.sbb.utilities.loaders.option.jaxb.CsvRegisterOptionSet;

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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/**
 * Tool converting BMW Options and Choice Excel file to Windchill LoadFile XML file. Sample usage: windchill
 * com.bmw.psmg.sbb.utilities.loaders.option.OptionChoicesExcelToLoaderXml -f [excel-file-path] -c [wt-container-path] -n [target-name]
 *
 * @author Karol Poliszkiewicz
 * @see wt.load.LoadFromFile
 */
public class OptionChoicesExcelToLoaderXml {

    private static final String SUFFIX_FALSE = "_FALSE";
    private static final String SUFFIX_TRUE = "_TRUE";
    private static final String STRING_TYPE = "STRING";
    private static final String BOOLEAN_TYPE = "BOOLEAN";
    private static final Logger LOGGER = Logger.getLogger(OptionChoicesExcelToLoaderXml.class.getName());
    private static final ConsoleAppender CONSOLE = new ConsoleAppender(new PatternLayout(""));

    private static final String ARGUMENT_FILE_PATH = "f";
    private static final String ARGUMENT_NAME = "n";
    private static final String ARGUMENT_CONTAINER_PATH = "c";
    private static final String ARGUMENT_OPTION_TYPE = "typeoption";
    private static final String ARGUMENT_CHOICE_TYPE = "typetchoice";
    private static final String ARGUMENT_NO_HEADER = "noheader";
    private static final String ARGUMENT_NO_FORMULA = "noformula";

    private static final int OPTION_GROUP = 0;
    private static final int OPTION_NAME = 1;
    private static final int OPTION_NUMBER = 2;
    private static final int CHOICE_NUMBER = 3;
    private static final int CHOICE_NAME = 4;
    private static final int CHOICE_DESCRIPTION = 5;
    private static final int OPTION_DESCRIPTION = 6;

    private static AtomicReference<String> optionTypeDef = new AtomicReference<>();
    private static AtomicReference<String> choiceTypeDef = new AtomicReference<>();
    private static Set<CsvOptionGroup> groups = new HashSet<>();
    private static Map<CsvBeginOption, Set<CsvBeginChoice>> options = new HashMap<>();

    private static LoadFileMarshaller loadFileMarshaller = new LoadFileMarshaller();

    static {
        CONSOLE.setThreshold(Level.INFO);
        LOGGER.setLevel(Level.INFO);
        LOGGER.addAppender(CONSOLE);
    }

    /**
     * The method starts generating BMW Options and Choice Excel file to Windchill Load File XML file.
     *
     * @param args contains all input arguments from Windchill Shell
     */
    public static void main(String[] args) {
        Options options = new Options();
        options.addOption(ARGUMENT_FILE_PATH, null, true, "The file path of the input Excel sheet file");
        options.addOption(ARGUMENT_NAME, null, true, "The name prefix of the exported XML files");
        options.addOption(ARGUMENT_CONTAINER_PATH, null, true, "The container path of the exported choices");
        options.addOption(ARGUMENT_OPTION_TYPE, true, "* Optional Internal Name of Option model type definition");
        options.addOption(ARGUMENT_CHOICE_TYPE, true, "* Optional Internal Name of Choice model type definition");
        options.addOption(ARGUMENT_NO_HEADER, null, false, "Skip header row");
        options.addOption(ARGUMENT_NO_FORMULA, null, false, "Gets raw cell String value not formula result");
        execute(args, options);
    }

    private static void execute(String[] args, Options options) {
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine line = parser.parse(options, args);
            optionTypeDef.set(line.hasOption(ARGUMENT_OPTION_TYPE) ? line.getOptionValue(ARGUMENT_OPTION_TYPE) : PSMGSBBConstants.Types.CONFIGURATION_OPTION_INTERNAL_NAME);
            choiceTypeDef.set(line.hasOption(ARGUMENT_CHOICE_TYPE) ? line.getOptionValue(ARGUMENT_CHOICE_TYPE) : PSMGSBBConstants.Types.CONFIGURATION_CHOICE_INTERNAL_NAME);
            if (validateArgs(line)) {
                load(line.getOptionValue(ARGUMENT_FILE_PATH), !line.hasOption(ARGUMENT_NO_HEADER), line.hasOption(ARGUMENT_NO_FORMULA));
                String name = line.getOptionValue(ARGUMENT_NAME);
                String containerPath = line.getOptionValue(ARGUMENT_CONTAINER_PATH);
                LOGGER.info("Generating Windchill LoadFile XML files");
                List<String> outFiles = new LinkedList<>();
                String optionPool = generateOptionPool(name);
                if (optionPool != null && !optionPool.isEmpty()) {
                    outFiles.add(optionPool);
                }
                String optionSet = generateOptionSet(name, containerPath);
                if (optionSet != null && !optionSet.isEmpty()) {
                    outFiles.add(optionSet);
                }
                if (outFiles.isEmpty()) {
                    fail("Not found containers in source Excel file.");
                } else {
                    success(outFiles);
                }
            } else {
                printHelp(options);
            }
        } catch (ParseException e) {
            LOGGER.error("Cannot parse argument", e);
            fail("Cannot parse argument: " + e.getLocalizedMessage());
        }
    }

    private static boolean validateArgs(CommandLine line) {
        return line.hasOption(ARGUMENT_FILE_PATH) && line.hasOption(ARGUMENT_NAME) && line.hasOption(ARGUMENT_CONTAINER_PATH);
    }

    private static void printHelp(Options options) {
        String header = "Tool converting BMW Options and Choice Excel file to Windchill LoadFile XML file.";
        new HelpFormatter().printHelp("windchill shell", header, options, "");
    }

    private static void load(String filePath, boolean hasHeader, boolean noFormula) {
        try (ExcelReader excelReader = new ExcelReader(new File(filePath))) {
            Sheet sheet = excelReader.getSheet(0);
            if (hasHeader) {
                sheet.removeRow(sheet.getRow(0)); // removing the first row which contains table header
            }
            if (noFormula) {
                DataFormatter dataFormatter = new DataFormatter();
                sheet.forEach(row -> parseData(row, dataFormatter));
            } else {
                FormulaEvaluator formulaEvaluator = excelReader.getFormulaEvaluator();
                sheet.forEach(row -> parseFormula(row, formulaEvaluator));
            }
        } catch (IOException e) {
            LOGGER.error("Cannot load Excel file.", e);
            fail("Cannot find Excel file:<" + filePath + '>');
        }
        int choices = options.values().stream().mapToInt(Set::size).sum();
        LOGGER.info("Loaded successful ( groups=" + groups.size() + ", options=" + options.keySet().size() + ", choices=" + choices + " )");
    }

    private static void parseData(Row row, DataFormatter dataFormatter) {
        String groupName = dataFormatter.formatCellValue(row.getCell(OPTION_GROUP));
        String optionName = dataFormatter.formatCellValue(row.getCell(OPTION_NAME));
        String optionNumber = dataFormatter.formatCellValue(row.getCell(OPTION_NUMBER));
        String choiceNumber = dataFormatter.formatCellValue(row.getCell(CHOICE_NUMBER));
        String choiceName = dataFormatter.formatCellValue(row.getCell(CHOICE_NAME));
        String choiceDesc = dataFormatter.formatCellValue(row.getCell(CHOICE_DESCRIPTION));
        String optionDesc = dataFormatter.formatCellValue(row.getCell(OPTION_DESCRIPTION));
        groups.add(new CsvOptionGroup(groupName, optionTypeDef.get()));
        boolean isSingleChoice = false;
        String optionDataType = STRING_TYPE;
        if (choiceNumber.indexOf('.') > -1 && choiceNumber.substring(0, choiceNumber.indexOf(".")).equals(choiceNumber.substring(choiceNumber.indexOf(".") + 1))) {
            optionDataType = BOOLEAN_TYPE;
            isSingleChoice = true;
        }
        CsvBeginOption option = new CsvBeginOption(optionName, optionNumber, optionDesc, optionTypeDef.get(), isSingleChoice, groupName, optionDataType);
        options.putIfAbsent(option, new HashSet<>());
        options.get(option).add(new CsvBeginChoice(choiceName, choiceNumber, choiceDesc, choiceTypeDef.get(), option.getName()));
    }

    private static void parseFormula(Row row, FormulaEvaluator formulaEvaluator) {
        String groupName = getEvaluateValue(formulaEvaluator, row.getCell(OPTION_GROUP));
        String optionName = getEvaluateValue(formulaEvaluator, row.getCell(OPTION_NAME));
        String optionNumber = getEvaluateValue(formulaEvaluator, row.getCell(OPTION_NUMBER));
        String choiceNumber = getEvaluateValue(formulaEvaluator, row.getCell(CHOICE_NUMBER));
        String choiceName = getEvaluateValue(formulaEvaluator, row.getCell(CHOICE_NAME));
        String choiceDesc = getEvaluateValue(formulaEvaluator, row.getCell(CHOICE_DESCRIPTION));
        String optionDesc = getEvaluateValue(formulaEvaluator, row.getCell(OPTION_DESCRIPTION));
        groups.add(new CsvOptionGroup(groupName, optionTypeDef.get()));
        String optionDataType = STRING_TYPE;
        boolean isSingleChoice = false;
        if (choiceNumber.indexOf('.') > -1 && choiceNumber.substring(0, choiceNumber.indexOf(".")).equals(choiceNumber.substring(choiceNumber.indexOf(".") + 1))) {
            optionDataType = BOOLEAN_TYPE;
            isSingleChoice = true;
        }
        CsvBeginOption option = new CsvBeginOption(optionName, optionNumber, optionDesc, optionTypeDef.get(), isSingleChoice, groupName, optionDataType);
        options.putIfAbsent(option, new HashSet<>());
        options.get(option).add(new CsvBeginChoice(choiceName, choiceNumber, choiceDesc, choiceTypeDef.get(), option.getName()));
    }

    private static String getEvaluateValue(FormulaEvaluator formulaEvaluator, Cell cell) {
        CellValue cellValue = formulaEvaluator.evaluate(cell);
        String value = cellValue != null && cellValue.getStringValue() != null ? cellValue.getStringValue() : "";
        return value.trim();
    }

    private static String generateOptionPool(String name) {
        CsvOptionPool optionPool = new CsvOptionPool();
        optionPool.setGroups(groups);
        List<Object> objects = new LinkedList<>();
        for (Map.Entry<CsvBeginOption, Set<CsvBeginChoice>> entry : options.entrySet()) {
            objects.add(entry.getKey());
            for (CsvBeginChoice choice : entry.getValue()) {
                String cNumber = choice.getNumber();
                String cName = choice.getName();

                if (cNumber.indexOf('.') > -1 && cNumber.substring(0, cNumber.indexOf(".")).equals(cNumber.substring(cNumber.indexOf(".") + 1))) {
                    cNumber = cNumber.substring(0, cNumber.indexOf("."));
                    CsvBeginChoice choiceTrue = new CsvBeginChoice(cName + SUFFIX_TRUE, cNumber + SUFFIX_TRUE, choice.getDescription(), choice.getTypeDef(), choice.getOption());
                    objects.add(choiceTrue);
                    CsvBeginChoice choiceFalse = new CsvBeginChoice(cName + SUFFIX_FALSE, cNumber + SUFFIX_FALSE, choice.getDescription(), choice.getTypeDef(), choice.getOption());
                    objects.add(choiceFalse);
                } else {
                    objects.add(choice);
                }
                objects.add(new CsvEndChoice());
            }
            objects.add(new CsvEndOption());
        }
        optionPool.setObjects(objects);
        String xmlFile = "OptionPool";

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(CsvOptionPool.class, CsvBeginOption.class, CsvBeginChoice.class, CsvEndChoice.class, CsvEndOption.class);
            return loadFileMarshaller.marshal(xmlFile, jaxbContext, optionPool);
        } catch (JAXBException e) {
            LOGGER.error("Cannot marshal JAXB Option Pool objects", e);
            fail("Cannot marshal JAXB Option Pool objects: " + e.getLocalizedMessage());
        }
        return null;
    }

    private static String generateOptionSet(String name, String containerPath) {
        CsvOptionSet optionSet = new CsvOptionSet();
        optionSet.setBeginOptionSet(new CsvBeginOptionSet(name, ""));
        List<CsvAddChoice> addChoices = new LinkedList<>();
        for (Map.Entry<CsvBeginOption, Set<CsvBeginChoice>> entry : options.entrySet()) {
            for (CsvBeginChoice choice : entry.getValue()) {
                String cNumber = choice.getNumber();
                if (cNumber.indexOf('.') > -1 && cNumber.substring(0, cNumber.indexOf(".")).equals(cNumber.substring(cNumber.indexOf(".") + 1))) {
                    cNumber = cNumber.substring(0, cNumber.indexOf("."));
                    addChoices.add(new CsvAddChoice(cNumber + SUFFIX_TRUE, containerPath));
                    addChoices.add(new CsvAddChoice(cNumber + SUFFIX_FALSE, containerPath));
                } else {
                    addChoices.add(new CsvAddChoice(choice.getName(), containerPath));
                }
            }
        }
        optionSet.setAddChoices(addChoices);
        optionSet.setEndOptionSet(new CsvEndOptionSet());
        optionSet.setRegisterOptionSet(new CsvRegisterOptionSet(name));
        String xmlFile = "OptionSet";

        try {
            return loadFileMarshaller.marshal(xmlFile, JAXBContext.newInstance(CsvOptionSet.class), optionSet);
        } catch (JAXBException e) {
            LOGGER.error("Cannot marshal JAXB Option Set objects", e);
            fail("Cannot marshal JAXB Option Set objects: " + e.getLocalizedMessage());
        }
        return null;
    }

    private static void fail(String message) {
        LOGGER.info("GENERATION FAILED");
        LOGGER.info(message);
    }

    private static void success(List<String> outFiles) {
        LOGGER.info("GENERATION SUCCESSFUL");
        LOGGER.info("Output files:");
        outFiles.forEach(LOGGER::info);
    }

}
