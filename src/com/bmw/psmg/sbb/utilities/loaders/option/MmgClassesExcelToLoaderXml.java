package com.bmw.psmg.sbb.utilities.loaders.option;

import com.bmw.psmg.sbb.generic.PSMGSBBConstants.Types;
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
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/**
 * Tool converting BMW MMG Class Options and Choice Excel file to Windchill LoadFile XML file. Sample usage: windchill
 * com.bmw.psmg.sbb.utilities.loaders.option.MmgClassesExcelToLoaderXml -f [excel-file-path] -c [wt-container-path]
 *
 * @author Karol Poliszkiewicz
 * @see wt.load.LoadFromFile
 */
public class MmgClassesExcelToLoaderXml {

    private static final Logger LOGGER = Logger.getLogger(MmgClassesExcelToLoaderXml.class.getName());
    private static final ConsoleAppender CONSOLE = new ConsoleAppender(new PatternLayout(""));
    private static final String NAME = "WT_Class";
    private static final String ARGUMENT_FILE_PATH = "f";
    private static final String STRING_TYPE = "STRING";
    private static final String ARGUMENT_CONTAINER_PATH = "c";

    private static CsvOptionGroup group = new CsvOptionGroup("Class", Types.CLASS_OPTION_INTERNAL_NAME);
    private static CsvBeginOption option = new CsvBeginOption("Class", "", "", group.getOptionTypeDef(), false, group.getName(), STRING_TYPE);
    private static Set<CsvBeginChoice> choices = new HashSet<>();

    private static LoadFileMarshaller loadFileMarshaller = new LoadFileMarshaller();

    static {
        CONSOLE.setThreshold(Level.INFO);
        LOGGER.setLevel(Level.INFO);
        LOGGER.addAppender(CONSOLE);
    }

    /**
     * The method starts generating BMW Class Options and Choice Excel file to Windchill Load File XML file.
     *
     * @param args contains all input arguments from Windchill Shell
     */
    public static void main(String[] args) {
        Options options = new Options();
        options.addOption(ARGUMENT_FILE_PATH, null, true, "The file path of the input Excel sheet file");
        options.addOption(ARGUMENT_CONTAINER_PATH, null, true, "The container path of the exported choices");
        execute(args, options);
    }

    private static void execute(String[] args, Options options) {
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine line = parser.parse(options, args);
            if (validateArgs(line)) {
                load(line.getOptionValue(ARGUMENT_FILE_PATH));
                String containerPath = line.getOptionValue(ARGUMENT_CONTAINER_PATH);

                LOGGER.info("Generating Windchill LoadFile XML files");
                String optionPool = generateOptionPool();
                String optionSet = generateOptionSet(containerPath);
                showResults(Arrays.asList(optionPool, optionSet));
            } else {
                printHelp(options);
            }
        } catch (ParseException e) {
            LOGGER.error("Cannot parse argument", e);
            fail("Cannot parse argument: " + e.getLocalizedMessage());
        }
    }

    private static void showResults(List<String> results) {
        if (results.isEmpty()) {
            fail("Not found containers in source Excel file.");
        } else {
            LOGGER.info("GENERATION SUCCESSFUL");
            LOGGER.info("Output files:");
            results.forEach(LOGGER::info);
        }
    }

    private static boolean validateArgs(CommandLine line) {
        return line.hasOption(ARGUMENT_FILE_PATH) && line.hasOption(ARGUMENT_CONTAINER_PATH);
    }

    private static void printHelp(Options options) {
        String header = "Tool converting BMW Options and Choice Excel file to Windchill LoadFile XML file.";
        new HelpFormatter().printHelp("windchill shell", header, options, "");
    }

    private static void load(String filePath) {
        try (ExcelReader excelReader = new ExcelReader(new File(filePath))) {
            Sheet sheet = excelReader.getSheet(0);
            FormulaEvaluator formulaEvaluator = excelReader.getFormulaEvaluator();
            sheet.forEach(row -> parse(row, formulaEvaluator));
        } catch (IOException e) {
            LOGGER.error("Cannot parse Excel file.", e);
            fail("Cannot parse Excel file:<" + filePath + '>');
        }
        LOGGER.info("Loaded successful classes ( choices=" + choices.size() + " )");
    }

    private static void parse(Row row, FormulaEvaluator formulaEvaluator) {
        String name = formulaEvaluator.evaluate(row.getCell(0)).getStringValue().trim();
        choices.add(new CsvBeginChoice(name, "", Types.CLASS_CHOICE_INTERNAL_NAME, option.getName()));
    }

    private static String generateOptionPool() {
        CsvOptionPool classesOptionPool = new CsvOptionPool();
        classesOptionPool.setGroups(Collections.singleton(group));
        List<Object> objects = new LinkedList<>();

        objects.add(option);
        for (CsvBeginChoice choice : choices) {
            objects.add(choice);
            objects.add(new CsvEndChoice());
        }
        objects.add(new CsvEndOption());

        classesOptionPool.setObjects(objects);
        String xmlFile = NAME + "OptionPool";

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(CsvOptionPool.class, CsvBeginOption.class, CsvBeginChoice.class, CsvEndChoice.class, CsvEndOption.class);
            return loadFileMarshaller.marshal(xmlFile, jaxbContext, classesOptionPool);
        } catch (JAXBException e) {
            LOGGER.error("Cannot marshal JAXB Option Pool objects", e);
            fail("Cannot marshal JAXB Option Pool objects: " + e.getLocalizedMessage());
        }
        return null;
    }

    private static String generateOptionSet(String containerPath) {
        String xmlFile = NAME + "OptionSet";
        CsvOptionSet optionSet = new CsvOptionSet();
        optionSet.setBeginOptionSet(new CsvBeginOptionSet(xmlFile, ""));
        List<CsvAddChoice> addChoices = new LinkedList<>();
        for (CsvBeginChoice choice : choices) {
            addChoices.add(new CsvAddChoice(choice.getName(), containerPath));
        }
        optionSet.setAddChoices(addChoices);
        optionSet.setEndOptionSet(new CsvEndOptionSet());

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

}
