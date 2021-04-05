package com.bmw.psmg.sbb.utilities.loaders.variantspecification;

import com.bmw.psmg.sbb.generic.PSMGSBBConstants.Types;
import com.bmw.psmg.sbb.utilities.loaders.LoadFileMarshaller;
import com.bmw.psmg.sbb.utilities.loaders.option.ExcelReader;
import com.bmw.psmg.sbb.utilities.loaders.variantspecification.jaxb.CsvBmwVariantSpec;
import com.bmw.psmg.sbb.utilities.loaders.variantspecification.jaxb.CsvVariantSpecLoader;
import com.bmw.psmg.sbb.utilities.loaders.variantspecification.model.AttributeModel;

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
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/**
 * Command line tool that generates LoadFromFile-compliant XML files out of information taken from Excel file.
 * Sample usage: windchill com.bmw.psmg.sbb.utilities.loaders.variantspecification.CKDTypeFilterLoadFileGenerator -file [excel-filepath] -types [excel-type-filter-filepath]
 *
 * @author Karol Poliszkiewicz
 * @see wt.load.LoadFromFile
 */
public class CKDTypeFilterLoadFileGenerator {

    private static final Logger LOGGER = Logger.getLogger(CKDTypeFilterLoadFileGenerator.class.getName());
    private static final ConsoleAppender CONSOLE = new ConsoleAppender(new PatternLayout(""));
    private static final String ARGUMENT_FILE_PATH = "file";
    private static final String ARGUMENT_TYPE_FILTER_FILE_PATH = "types";
    private static final String SALAPA_CHOICES = "SA";

    private static final int NAME_ROW_INDEX = 0;
    private static final int SBB_TRIMSTYLE_ROW_INDEX = 14;
    private static final int MODEL_CODE_ROW_INDEX = 10;
    private static final int SBB_CODES_COLUMN_INDEX = 2;
    private static final int START_SBB_CODES_ROW_INDEX = 18;
    private static final int END_SBB_CODES_ROW_INDEX = 259;
    private static final int START_COLUMN_INDEX = 4;
    private static final int END_COLUMN_INDEX = 35;

    private static final int TYP_ENTW_BEZ_ID = 2;
    private static final int TYP_LAND_ID = 3;
    private static final int TYP_LENKUNG_ID = 4;
    private static final int TYP_KAROSS_ID = 5;
    private static final int TYP_KARAUSF_ID = 6;
    private static final int TYP_TUER_ID = 7;
    private static final int TYP_LCI_ID = 8;
    private static final int TYP_ANTRIEB_ID = 9;
    private static final int TYP_GETR_ID = 10;
    private static final int TYP_TSA_ID = 13;
    private static final int TYP_MONT_LAND = 14;
    private static final int TYP_PL_LAND = 15;
    private static final int TYP_HYBRID_ID = 16;
    private static final int TYP_ABGAS_ID = 17;
    private static final int TYP_ERW_ID = 18;
    private static final int TYP_FZGZUSDF_ID = 19;
    private static final int TYP_MARKE_ID = 20;
    private static final int TYP_SICHFZG_ID = 21;
    private static final int TYP_MSPORT_ID = 22;
    private static final int TYP_DERFAM_ID = 23;
    private static final int TYP_MODKAT_ID = 24;
    private static final int TYP_TEMPDIFF_ID = 29;
    private static final int TYP_MODAUSF_ID = 30;
    private static final int TYP_MOTOR_ID = 31;

    private static final List<Integer> CHOICE_IDS = Arrays.asList(TYP_ENTW_BEZ_ID, TYP_LAND_ID, TYP_LENKUNG_ID, TYP_KAROSS_ID, TYP_KARAUSF_ID, TYP_TUER_ID, TYP_LCI_ID, TYP_ANTRIEB_ID, TYP_GETR_ID,
            TYP_TSA_ID, TYP_MONT_LAND, TYP_PL_LAND, TYP_HYBRID_ID, TYP_ABGAS_ID, TYP_ERW_ID, TYP_FZGZUSDF_ID, TYP_MARKE_ID, TYP_SICHFZG_ID, TYP_MSPORT_ID, TYP_DERFAM_ID, TYP_MODKAT_ID,
            TYP_TEMPDIFF_ID, TYP_MODAUSF_ID, TYP_MOTOR_ID);

    private static LoadFileMarshaller loadFileMarshaller = new LoadFileMarshaller();

    /**
     * The method starts generating LoadFromFile-compliant XML files out of information taken from Excel file.
     *
     * @param args contains all input arguments from Windchill Shell
     */
    public static void main(String[] args) {
        CONSOLE.setThreshold(Level.INFO);
        LOGGER.setLevel(Level.INFO);
        LOGGER.addAppender(CONSOLE);
        new CKDTypeFilterLoadFileGenerator(args);
    }

    private CKDTypeFilterLoadFileGenerator(String[] args) {
        Options options = new Options();
        options.addOption(ARGUMENT_FILE_PATH, null, true, "A file path of the input Excel sheet file");
        options.addOption(ARGUMENT_TYPE_FILTER_FILE_PATH, null, true, "A file path of the input Excel sheet file");
        try {
            CommandLineParser commandLineParser = new DefaultParser();
            CommandLine line = commandLineParser.parse(options, args);
            if (validateArgs(line)) {
                String typeFilterFilePath = line.getOptionValue(ARGUMENT_TYPE_FILTER_FILE_PATH);
                Map<String, AttributeModel> modelCodeAttributes = parseTypeFilters(typeFilterFilePath);

                String filePath = line.getOptionValue(ARGUMENT_FILE_PATH);
                List<CsvBmwVariantSpec> variantSpecs = parseExcel(filePath, modelCodeAttributes);

                Map<String, List<String>> choiceMap = parseChoices(typeFilterFilePath);

                for (CsvBmwVariantSpec variantSpec : variantSpecs) {
                    List<String> choices = new LinkedList<>(variantSpec.getChoices());
                    String modelCode = variantSpec.getModelCode();
                    if (choiceMap.containsKey(modelCode)) {
                        choices.addAll(choiceMap.get(modelCode));
                    }
                    variantSpec.setChoicesList(String.join(";", choices));
                }

                String outputFile = generateLoadFile(variantSpecs);
                LOGGER.info("GENERATION SUCCESS");
                LOGGER.info("Output file:\n" + outputFile);
            } else {
                printHelp(options);
            }
        } catch (ParseException | JAXBException e) {
            LOGGER.info("GENERATION FAILED");
            LOGGER.error(String.format("Unexpected error: '%s'", e.getLocalizedMessage()), e);
        }
    }

    private boolean validateArgs(CommandLine line) {
        return line.hasOption(ARGUMENT_FILE_PATH) && line.hasOption(ARGUMENT_TYPE_FILTER_FILE_PATH);
    }

    private void printHelp(Options options) {
        String header = "Command line tool generates BMW LoadFromFile-compliant XML files out of information taken from Excel file.";
        new HelpFormatter().printHelp("windchill shell", header, options, "");
    }

    private List<CsvBmwVariantSpec> parseExcel(String filePath, Map<String, AttributeModel> modelCodeAttributes) {
        LOGGER.info("Parsing CKD Filters.");
        List<CsvBmwVariantSpec> variantSpecs = new LinkedList<>();
        try (ExcelReader excelReader = new ExcelReader(new File(filePath))) {
            Sheet sheet = excelReader.getSheet(0);
            FormulaEvaluator formulaEvaluator = excelReader.getFormulaEvaluator();

            List<String> choices = new LinkedList<>();
            for (int cellIndex = START_COLUMN_INDEX; cellIndex <= END_COLUMN_INDEX; cellIndex++) {
                String nameOrNumber = ExcelReader.evaluateValue(formulaEvaluator, sheet.getRow(NAME_ROW_INDEX).getCell(cellIndex)).replaceAll("\"", "");
                choices.clear();
                String sbbCodeTrimstyle = ExcelReader.evaluateValue(formulaEvaluator, sheet.getRow(SBB_TRIMSTYLE_ROW_INDEX).getCell(cellIndex));
                choices.add(sbbCodeTrimstyle);
                for (int rowIndex = START_SBB_CODES_ROW_INDEX; rowIndex <= END_SBB_CODES_ROW_INDEX; rowIndex++) {

                    Row row = sheet.getRow(rowIndex);
                    Cell choiceNumberCell = row.getCell(SBB_CODES_COLUMN_INDEX);
                    if (CellType.STRING.equals(choiceNumberCell.getCellType())) {
                        String choiceNumber = ExcelReader.evaluateValue(formulaEvaluator, choiceNumberCell);
                        if (!CellType.BLANK.equals(row.getCell(cellIndex).getCellType())) {
                            choices.add(choiceNumber);
                        } else if (isSalapaChoice(formulaEvaluator, row.getCell(0))) {
                            choices.add("NOT_" + choiceNumber);
                        }
                    } else {
                        LOGGER.debug("Skip row no " + rowIndex + 1);
                    }
                }

                CsvBmwVariantSpec variantSpec = new CsvBmwVariantSpec(nameOrNumber, nameOrNumber);
                String modelCode = ExcelReader.evaluateValue(formulaEvaluator, sheet.getRow(MODEL_CODE_ROW_INDEX).getCell(cellIndex));
                AttributeModel attributeModel = modelCodeAttributes.get(modelCode);
                variantSpec.setModelCode(modelCode);
                variantSpec.setTypeInternalName(Types.CKD_FILTER_INTERNAL_NAME);
                variantSpec.setBmwAttributes(attributeModel.mapAttributesToString());
                variantSpec.setState(attributeModel.getState());
                variantSpec.setFolder("/Default/CKD Filter");
                variantSpec.setFilterMode("alternative");

                if (!choices.isEmpty()) {
                    variantSpec.setChoices(choices);
                }

                variantSpecs.add(variantSpec);
            }

        } catch (IOException e) {
            LOGGER.error("Cannot parse CKD Filters Excel file.", e);
        }
        return variantSpecs;
    }

    private boolean isSalapaChoice(FormulaEvaluator formulaEvaluator, Cell cell) {
        if (cell == null || !CellType.STRING.equals(cell.getCellType())) {
            return false;
        }
        return SALAPA_CHOICES.equals(ExcelReader.evaluateValue(formulaEvaluator, cell));
    }

    private Map<String, List<String>> parseChoices(String filePath) {
        LOGGER.info("Parsing Type Filters choices.");
        Map<String, List<String>> choices = new HashMap<>();

        try (ExcelReader excelReader = new ExcelReader(new File(filePath))) {
            Sheet sheet = excelReader.getSheet(0);

            for (int column = ExcelReader.getColumnIndex("D"); column <= ExcelReader.getColumnIndex("YH"); column += 3) {
                Row row = sheet.getRow(2);
                String choiceName = sheet.getRow(1).getCell(column).getStringCellValue();
                List<String> choiceList = new LinkedList<>();
                for (int rowIndex = 2; rowIndex <= 26; rowIndex++) {
                    if (row.getCell(column) != null && !row.getCell(column).getStringCellValue().isEmpty() && CHOICE_IDS.contains(rowIndex)) {
                        String choice = sheet.getRow(rowIndex).getCell(column).getStringCellValue();
                        choiceList.add(choice);
                    }
                }
                choices.put(choiceName, choiceList);
            }
        } catch (IOException e) {
            LOGGER.error("Cannot load Excel file.", e);
        }

        return choices;
    }

    private Map<String, AttributeModel> parseTypeFilters(String filePath) {
        LOGGER.info("Parsing Type Filters attributes.");
        Map<String, AttributeModel> modelCodeAttributes = new HashMap<>();

        try (ExcelReader excelReader = new ExcelReader(new File(filePath))) {
            Sheet sheet = excelReader.getSheet(1);
            FormulaEvaluator formulaEvaluator = excelReader.getFormulaEvaluator();
            DataFormatter dataFormatter = new DataFormatter();

            Row modelCodeRow = sheet.getRow(1);
            Row sopRow = sheet.getRow(2);
            Row eopRow = sheet.getRow(3);
            Row plantRow = sheet.getRow(4);
            Row categoryRow = sheet.getRow(5);

            for (int column = ExcelReader.getColumnIndex("C"); column <= ExcelReader.getColumnIndex("TU"); column++) {
                String modelCode = ExcelReader.evaluateValue(formulaEvaluator, modelCodeRow.getCell(column));
                AttributeModel attribute = modelCodeAttributes.get(modelCode);
                if (attribute == null) {
                    attribute = new AttributeModel();
                }

                String sop = dataFormatter.formatCellValue(sopRow.getCell(column));
                attribute.setSop(sop);

                String eop = dataFormatter.formatCellValue(eopRow.getCell(column));
                attribute.setEop(eop);

                String plant = ExcelReader.evaluateValue(formulaEvaluator, plantRow.getCell(column));
                attribute.addPlant(plant);

                String category = ExcelReader.evaluateValue(formulaEvaluator, categoryRow.getCell(column));
                attribute.setTypeCategory(category);

                modelCodeAttributes.put(modelCode, attribute);
            }

            LOGGER.info("Getting statuses");

            Sheet statusSheet = excelReader.getSheet(2);
            Row modelNameRow = statusSheet.getRow(1);
            Row modelStatusRow = statusSheet.getRow(2);

            for (int column = ExcelReader.getColumnIndex("C"); column <= ExcelReader.getColumnIndex("HM"); column++) {
                String filterName = ExcelReader.evaluateValue(formulaEvaluator, modelNameRow.getCell(column));
                AttributeModel attribute = modelCodeAttributes.get(filterName);
                if (attribute == null) {
                    attribute = new AttributeModel();
                }

                String status = dataFormatter.formatCellValue(modelStatusRow.getCell(column));
                attribute.setState(status);

                modelCodeAttributes.put(filterName, attribute);
            }

        } catch (IOException e) {
            LOGGER.error("Cannot load Excel file.", e);
        }

        return modelCodeAttributes;
    }

    private String generateLoadFile(List<CsvBmwVariantSpec> variantSpecs) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(CsvVariantSpecLoader.class, CsvBmwVariantSpec.class);
        CsvVariantSpecLoader variantSpecLoader = new CsvVariantSpecLoader(variantSpecs);
        return loadFileMarshaller.marshal("WT_CKD_Type_Filters", jaxbContext, variantSpecLoader);
    }

}
