package com.bmw.psmg.sbb.utilities.loaders.variantspecification;

import com.bmw.psmg.sbb.generic.PSMGSBBConstants;
import com.bmw.psmg.sbb.utilities.loaders.LoadFileMarshaller;
import com.bmw.psmg.sbb.utilities.loaders.option.ExcelReader;
import com.bmw.psmg.sbb.utilities.loaders.variantspecification.jaxb.CsvBmwVariantSpec;
import com.bmw.psmg.sbb.utilities.loaders.variantspecification.jaxb.CsvVariantSpecLoader;
import com.bmw.psmg.sbb.utilities.loaders.variantspecification.model.AttributeModel;
import com.bmw.psmg.sbb.utilities.loaders.variantspecification.model.ChoiceModel;
import com.bmw.psmg.sbb.utilities.loaders.variantspecification.model.OptionModel;

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
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;


/**
 * Command line tool that generates LoadFromFile-compliant XML files out of information taken from Excel file.
 * Sample usage: windchill com.bmw.psmg.sbb.utilities.loaders.variantspecification.VariantSpecExcelToLoaderXml -file [excel-filepath]
 *
 * @author Piotr Otapowicz
 * @see wt.load.LoadFromFile
 */
public class VariantSpecExcelToLoaderXml {

    private static final Logger LOGGER = Logger.getLogger(VariantSpecExcelToLoaderXml.class.getName());
    private static final ConsoleAppender CONSOLE = new ConsoleAppender(new PatternLayout(""));
    private static final String ARGUMENT_FILE_PATH = "file";

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

    private static final List<Integer> CHOICE_IDS = Arrays.asList(TYP_ENTW_BEZ_ID, TYP_LAND_ID, TYP_LENKUNG_ID, TYP_KAROSS_ID, TYP_KARAUSF_ID, TYP_TUER_ID, TYP_LCI_ID,
            TYP_ANTRIEB_ID, TYP_GETR_ID, TYP_TSA_ID, TYP_MONT_LAND, TYP_PL_LAND, TYP_HYBRID_ID, TYP_ABGAS_ID, TYP_ERW_ID, TYP_FZGZUSDF_ID, TYP_MARKE_ID, TYP_SICHFZG_ID,
            TYP_MSPORT_ID, TYP_DERFAM_ID, TYP_MODKAT_ID, TYP_TEMPDIFF_ID, TYP_MODAUSF_ID, TYP_MOTOR_ID);

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
        try {
            execute(args, options);
        } catch (ParseException e) {
            fail("Unexpected error: " + e.getLocalizedMessage());
            LOGGER.error(e);
        }
    }

    private static void execute(String[] args, Options options) throws ParseException {
        CommandLineParser parser = new DefaultParser();
        CommandLine line = parser.parse(options, args);
        if (validateArgs(line)) {
            String filePath = line.getOptionValue(ARGUMENT_FILE_PATH);
            try {
                String loadFilePath = parseAndGenerateXml(new File(filePath));
                success(loadFilePath);
            } catch (InvalidFormatException e) {
                LOGGER.error("Input file is not excel compatible", e);
            } catch (IOException e) {
                LOGGER.error("Error reading input file", e);
            } catch (JAXBException e) {
                LOGGER.error("Error creating xml load file", e);
            }
        } else {
            printHelp(options);
        }
    }

    private static String parseAndGenerateXml(File inputFile) throws IOException, InvalidFormatException, JAXBException {
        try (ExcelReader excelReader = new ExcelReader(inputFile)) {
            Map<String, List<ChoiceModel>> optionsAndChoices = getOptionAndChoicesMap(excelReader.getSheet(0));
            Map<String, AttributeModel> attributesMap = getAttributesMap(excelReader.getSheet(1));
            Map<String, String> statusMap = getStatusMap(excelReader.getSheet(2));
            return generateXmlFile(optionsAndChoices, attributesMap, statusMap);
        }
    }

    private static String generateXmlFile(Map<String, List<ChoiceModel>> optionAndChoicesMap, Map<String, AttributeModel> attributesMap, Map<String, String> statusMap) throws JAXBException {
        LoadFileMarshaller loadFileMarshaller = new LoadFileMarshaller();
        JAXBContext jaxbContext = JAXBContext.newInstance(CsvVariantSpecLoader.class, CsvBmwVariantSpec.class);
        List<CsvBmwVariantSpec> variantSpecs = new ArrayList<>();
        Set<String> variantSpecNames = new HashSet<>(attributesMap.keySet());
        variantSpecNames.addAll(optionAndChoicesMap.keySet());
        variantSpecNames.addAll(statusMap.keySet());
        variantSpecNames.forEach((variantSpec) -> {
            //filter all empty cells that could be imported
            if (variantSpec != null && !variantSpec.isEmpty()) {
                CsvBmwVariantSpec csvBmwVariantSpec = new CsvBmwVariantSpec(variantSpec, variantSpec);
                //Currently only TypeFilter subtype of VariantSpec should be handled
                csvBmwVariantSpec.setTypeInternalName(PSMGSBBConstants.Types.TYPE_FILTER_INTERNAL_NAME);

                AttributeModel attributeModel = attributesMap.get(variantSpec);
                csvBmwVariantSpec.setBmwAttributes(attributeModel == null ? "" : attributeModel.mapAttributesToString());

                List<ChoiceModel> choiceModels = optionAndChoicesMap.get(variantSpec);
                if (choiceModels != null) {
                    List<String> choices = choiceModels.stream().map(ChoiceModel::getChoice).collect(Collectors.toList());
                    csvBmwVariantSpec.setChoicesList(String.join(";", choices));
                }

                String state = statusMap.get(variantSpec);
                if (state != null) {
                    csvBmwVariantSpec.setState(state);
                }
                //If Variant Spec has no Choices it is ignored
                if (choiceModels != null && !choiceModels.isEmpty()) {
                    variantSpecs.add(csvBmwVariantSpec);
                }
            }
        });
        LOGGER.info("Amount of variantSpecs to be loaded: " + variantSpecs.size());
        CsvVariantSpecLoader variantSpecLoader = new CsvVariantSpecLoader(variantSpecs);

        return loadFileMarshaller.marshal("VariantSpecification", jaxbContext, variantSpecLoader);
    }

    private static Map<String, List<ChoiceModel>> getOptionAndChoicesMap(Sheet sheet) {
        sheet.removeRow(sheet.getRow(0));
        Iterator<Row> rowIterator = sheet.rowIterator();
        Row firstRow = rowIterator.next();
        Map<Integer, String> typeFilterColumns = getTypeFilterColumns(firstRow, 3);
        return getTypeFilterChoicesMap(rowIterator, typeFilterColumns);
    }

    private static Map<String, AttributeModel> getAttributesMap(Sheet sheet) {
        sheet.removeRow(sheet.getRow(0));
        Iterator<Row> rowIterator = sheet.rowIterator();
        Row firstRow = rowIterator.next();
        Map<Integer, String> typeFilterColumns = getTypeFilterColumns(firstRow, 2);
        Map<String, AttributeModel> typeFilterAttributes = new HashMap<>();
        DataFormatter dataFormatter = new DataFormatter();
        rowIterator.forEachRemaining(row -> row.cellIterator().forEachRemaining(cell -> {
            int cellColumnIndex = cell.getColumnIndex();
            String typeFilter = typeFilterColumns.get(cellColumnIndex);
            if (typeFilter != null) {
                String cellValue = dataFormatter.formatCellValue(cell);
                AttributeModel model = typeFilterAttributes.get(typeFilter);
                typeFilterAttributes.put(typeFilter, updateAttributeModelDependingOnRowNum(model, row.getRowNum(), cellValue));
            }
        }));
        return typeFilterAttributes;
    }

    private static Map<String, String> getStatusMap(Sheet sheet) {
        Row typeFiltersRow = sheet.getRow(1);
        Row statusRow = sheet.getRow(2);
        Map<String, String> typeFilterStatuses = new HashMap<>();
        DataFormatter dataFormatter = new DataFormatter();
        for (int i = 2; i < typeFiltersRow.getHeight(); i++) {
            Cell typeFilterCell = typeFiltersRow.getCell(i);
            String typeFilterCellValue = dataFormatter.formatCellValue(typeFilterCell);
            Cell statusCell = statusRow.getCell(i);
            String statusValue = dataFormatter.formatCellValue(statusCell);
            typeFilterStatuses.put(typeFilterCellValue, statusValue);
        }
        return typeFilterStatuses;
    }

    private static AttributeModel updateAttributeModelDependingOnRowNum(AttributeModel attributeModel, int rowNum, String value) {
        if (attributeModel == null) {
            attributeModel = new AttributeModel();
        }
        switch (rowNum) {
            case 2:
                attributeModel.setSop(value);
                break;
            case 3:
                attributeModel.setEop(value);
                break;
            case 4:
                attributeModel.addPlant(value);
                break;
            case 5:
                attributeModel.setTypeCategory(value);
                break;
            default:
                break;
        }
        return attributeModel;
    }

    private static Map<Integer, String> getTypeFilterColumns(Row firstRow, int firstDataColumnIndex) {
        Map<Integer, String> typeFilterColumns = new HashMap<>();
        DataFormatter dataFormatter = new DataFormatter();
        firstRow.cellIterator().forEachRemaining(cell -> {
            String cellValue = dataFormatter.formatCellValue(cell);
            if (!cellValue.isEmpty() && cell.getColumnIndex() >= firstDataColumnIndex) {
                typeFilterColumns.put(cell.getColumnIndex(), cellValue);
            }
        });
        return typeFilterColumns;
    }


    private static Map<String, List<ChoiceModel>> getTypeFilterChoicesMap(Iterator<Row> rowIterator, Map<Integer, String> typeFilterColumns) {
        Map<String, List<ChoiceModel>> typFilterChoices = new HashMap<>();
        rowIterator.forEachRemaining(row -> typeFilterColumns.forEach((key, typFilterName) -> {
            ChoiceModel choiceModel;
            if (row.getCell(key) != null && !row.getCell(key).getStringCellValue().isEmpty() && CHOICE_IDS.contains(row.getRowNum())) {
                try {
                    choiceModel = getChoiceModel(row, key);
                    List<ChoiceModel> actualChoiceInfo = typFilterChoices.get(typFilterName);
                    if (actualChoiceInfo != null) {
                        actualChoiceInfo.add(choiceModel);
                    } else {
                        typFilterChoices.put(typFilterName, new ArrayList<>(Collections.singletonList(choiceModel)));
                    }
                } catch (ParseException e) {
                    LOGGER.error("Error getting choice information", e);
                }
            }
        }));
        return typFilterChoices;
    }

    private static ChoiceModel getChoiceModel(Row row, Integer key) throws ParseException {
        OptionModel optionModel = getOptionModel(row);
        ChoiceModel choiceModel = new ChoiceModel(row.getCell(key).getStringCellValue(), optionModel);
        Cell choiceDECell = row.getCell(key + 1);
        if (choiceDECell != null && !choiceDECell.getStringCellValue().isEmpty()) {
            choiceModel.setChoiceDescriptionDE(choiceDECell.getStringCellValue());
        }
        Cell choiceENCell = row.getCell(key + 2);
        if (choiceENCell != null && !choiceENCell.getStringCellValue().isEmpty()) {
            choiceModel.setChoiceDescriptionEN(choiceENCell.getStringCellValue());
        }
        return choiceModel;
    }

    private static OptionModel getOptionModel(Row row) throws ParseException {
        Cell optionCell = row.getCell(0);
        if (optionCell == null) {
            throw new ParseException("Could not get option info in row: " + row.getRowNum());
        }
        String option = optionCell.getStringCellValue();
        String optionDescDe = row.getCell(1).getStringCellValue();
        String optionDescEn = row.getCell(2).getStringCellValue();
        return new OptionModel(option, optionDescDe, optionDescEn);
    }

    private static boolean validateArgs(CommandLine line) {
        return line.hasOption(ARGUMENT_FILE_PATH);
    }

    private static void printHelp(Options options) {
        String header = "Command line tool generates BMW LoadFromFile-compliant XML files out of information taken from Excel file.";
        new HelpFormatter().printHelp("windchill shell", header, options, "");
    }

    private static void fail(String message) {
        LOGGER.info("GENERATION FAILED");
        LOGGER.info(message);
    }

    private static void success(String outFile) {
        LOGGER.info("GENERATION SUCCESSFUL");
        LOGGER.info("Output file:");
        LOGGER.info(outFile);
    }

}
