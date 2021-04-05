package com.bmw.psmg.sbb.utilities.loaders.container;

import com.bmw.psmg.sbb.utilities.loaders.LoadFileMarshaller;
import com.bmw.psmg.sbb.utilities.loaders.container.jaxb.CsvAddPrincipalToRole;
import com.bmw.psmg.sbb.utilities.loaders.container.jaxb.CsvContainerLoader;
import com.bmw.psmg.sbb.utilities.loaders.container.jaxb.CsvProductContainer;

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
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellReference;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/**
 * Command line tool that generates LoadFromFile-compliant XML files out of information taken from Excel file.
 * Sample usage: windchill com.bmw.psmg.sbb.utilities.loaders.container.ContainerLoadFileGenerator -file [excel-filepath] -column [index-or-letter]
 *
 * @author Karol Poliszkiewicz
 * @see wt.load.LoadFromFile
 */
public class ContainerLoadFileGenerator {

    private static final Logger LOGGER = Logger.getLogger(ContainerLoadFileGenerator.class.getName());
    private static final ConsoleAppender CONSOLE = new ConsoleAppender(new PatternLayout(""));
    private static final String ARGUMENT_FILE_PATH = "file";
    private static final String ARGUMENT_COLUMN = "column";
    private static final String ARGUMENT_NO_FORMULA = "noformula";

    static {
        CONSOLE.setThreshold(Level.INFO);
        LOGGER.setLevel(Level.INFO);
        LOGGER.addAppender(CONSOLE);
    }

    /**
     * The method starts generating LoadFromFile-compliant XML files out of information taken from Excel file.
     *
     * @param args contains all input arguments from Windchill Shell
     */
    public static void main(String[] args) {
        Options options = new Options();
        options.addOption(ARGUMENT_FILE_PATH, null, true, "A relative file path of the input Excel sheet file");
        options.addOption(ARGUMENT_COLUMN, null, true, "Column index or letter, where stored container names");
        options.addOption(ARGUMENT_NO_FORMULA, null, false, "Gets raw cell String value not formula result");
        try {
            execute(args, options);
        } catch (JAXBException | ParseException | IOException e) {
            fail("Unexpected error: " + e.getLocalizedMessage());
            LOGGER.error(e);
        }
    }

    private static void execute(String[] args, Options options) throws ParseException, IOException, JAXBException {
        CommandLineParser parser = new DefaultParser();
        CommandLine line = parser.parse(options, args);
        if (validateArgs(line)) {
            String filePath = line.getOptionValue(ARGUMENT_FILE_PATH);
            int columnIndex = getColumnIndex(line.getOptionValue(ARGUMENT_COLUMN));
            generateContainers(load(filePath, columnIndex, line.hasOption(ARGUMENT_NO_FORMULA)));
        } else {
            printHelp(options);
        }
    }

    private static boolean validateArgs(CommandLine line) {
        return line.hasOption(ARGUMENT_FILE_PATH) && line.hasOption(ARGUMENT_COLUMN);
    }

    private static int getColumnIndex(String strNum) {
        if (strNum.matches("-?\\d+?")) {
            return Integer.parseInt(strNum);
        }
        return CellReference.convertColStringToIndex(strNum);
    }

    private static void printHelp(Options options) {
        String header = "Command line tool generates BMW LoadFromFile-compliant XML files out of information taken from Excel file.";
        new HelpFormatter().printHelp("windchill shell", header, options, "");
    }

    private static Set<CsvProductContainer> load(String filePath, int column, boolean noFormula) throws IOException {
        File excelSource = new File(filePath);
        LOGGER.info("Loading file " + excelSource.getAbsolutePath());
        Set<CsvProductContainer> containers = new HashSet<>();
        try (Workbook workbook = WorkbookFactory.create(excelSource)) {
            DataFormatter dataFormatter = new DataFormatter();
            FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
            Sheet sheet = workbook.getSheetAt(0);
            sheet.removeRow(sheet.getRow(0));   // removing the first row which contains table header
            sheet.forEach(row -> {
                String containerName;
                if (noFormula) {
                    containerName = dataFormatter.formatCellValue(row.getCell(column)).trim();
                } else {
                    containerName = formulaEvaluator.evaluate(row.getCell(column)).getStringValue().trim();
                }
                containers.add(new CsvProductContainer(containerName));
            });
        } catch (EncryptedDocumentException edex) {
            fail("File loading error: " + edex.getLocalizedMessage());
            LOGGER.error(edex);
        }
        return containers;
    }

    private static void generateContainers(Set<CsvProductContainer> containers) throws JAXBException {
        LoadFileMarshaller loadFileMarshaller = new LoadFileMarshaller();
        List<String> outFiles = new LinkedList<>();
        JAXBContext jaxbContext = JAXBContext.newInstance(CsvProductContainer.class, CsvAddPrincipalToRole.class, CsvContainerLoader.class);
        LOGGER.info("Generating containers");
        for (CsvProductContainer container : containers) {
            CsvContainerLoader containerLoader = new CsvContainerLoader();
            containerLoader.setContainer(container);
            containerLoader.setPrincipals(getPrincipals(container.getName()));
            String out = loadFileMarshaller.marshal(container.getName() + "-Product", jaxbContext, containerLoader);
            if (out != null && !out.isEmpty()) {
                outFiles.add(out);
            }
        }
        if (outFiles.isEmpty()) {
            fail("Empty result.");
        } else {
            success(outFiles);
        }
    }

    private static Set<CsvAddPrincipalToRole> getPrincipals(String containerName) {
        Set<CsvAddPrincipalToRole> principals = new HashSet<>();
        principals.add(new CsvAddPrincipalToRole("sbbAllManager", "PRODUCT MANAGER", containerName));
        principals.add(new CsvAddPrincipalToRole("sbbAllUser", "MEMBERS", containerName));
        return principals;
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
