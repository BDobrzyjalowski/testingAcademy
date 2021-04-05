package com.bmw.psmg.sbb.utilities.loaders.folder.readers;

import com.bmw.psmg.sbb.utilities.loaders.folder.model.FolderModel;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import wt.method.RemoteAccess;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * The class generates xml file for loading folder structure generated from list of values form excel file
 *
 * @author Pawel Miron
 */
public class FolderStructureExcelParser implements RemoteAccess {

    private static final Logger LOGGER = Logger.getLogger(FolderStructureExcelParser.class.getName());
    private static final Integer GROUP_COLUMN_INDEX = 0;
    private static final Integer FIRST_DATA_ROW_INDEX = 1;
    private static final Integer DESIGNATION_COLUMN_INDEX = 2;
    private static final String SHEET_NAME = "Sheet1";

    private FolderStructureExcelParser() {
    }

    /**
     * Method generates xml load file from FolderModel objects list
     *
     * @param inputFilePath input file path
     * @param outputDirectoryPath output file path
     * @throws IOException during getting folder model list
     */
    public static void generateXMLLoadFile(String inputFilePath, String outputDirectoryPath) throws IOException {
        List<FolderModel> folderModelList = getFolderModelList(inputFilePath);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "<!DOCTYPE NmLoader SYSTEM \"standardX26.dtd\">\n" + "<NmLoader>\n");
        for (FolderModel folderModel : folderModelList) {
            stringBuilder.append("    <csvSubFolder handler=\"wt.folder.LoadFolder.createSubFolder\">\n").append("        <csvuser/>\n").append("        <csvfolderPath>/Default/").
                    append(folderModel.getGroup()).append("/</csvfolderPath" + ">\n").append("        <csvadminDomain/>\n").append("    </csvSubFolder>\n");
        }
        stringBuilder.append("</NmLoader>");
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(outputDirectoryPath + File.separator + "Materialstamm_folder_structure.xml"), StandardCharsets.UTF_8)) {
            writer.write(stringBuilder.toString());
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    /**
     * Gets list of Folder Names objects from excel file
     *
     * @param sheet Excel sheet
     * @return list of Folder Names objects from excel file
     */
    public static List<String> getFolderNamesList(Sheet sheet) {
        Integer lastDataRowIndex = getLastDataRowIndex(sheet);
        List<String> folderModelList = new ArrayList<>();
        for (int rowNumber = FIRST_DATA_ROW_INDEX; rowNumber < lastDataRowIndex; rowNumber = rowNumber + 2) {
            String group = getCell(sheet, rowNumber, GROUP_COLUMN_INDEX).getStringCellValue();
            folderModelList.add(group);
        }
        return folderModelList;
    }

    /**
     * Gets list of FolderModel objects from excel file
     *
     * @return list of FolderModel objects from excel file
     */
    private static List<FolderModel> getFolderModelList(String input) throws IOException {
        XSSFWorkbook excelWorkbook = getXssfWorkbook(input);
        Sheet sheet = getSheet(excelWorkbook, SHEET_NAME);
        Integer lastDataRowIndex = getLastDataRowIndex(sheet);
        List<FolderModel> folderModelList = new ArrayList<>();
        for (int rowNumber = FIRST_DATA_ROW_INDEX; rowNumber < lastDataRowIndex; rowNumber = rowNumber + 2) {
            FolderModel folderModel = new FolderModel();
            folderModel.setGroup(getCell(sheet, rowNumber, GROUP_COLUMN_INDEX).getStringCellValue());
            folderModel.setDesignationDE(getCell(sheet, rowNumber, DESIGNATION_COLUMN_INDEX).getStringCellValue());
            folderModel.setDesignationEN(getCell(sheet, rowNumber + 1, DESIGNATION_COLUMN_INDEX).getStringCellValue());
            folderModelList.add(folderModel);
        }
        return folderModelList;
    }

    /**
     * Gets index number of last row which contains data
     *
     * @param contextTemplateSheet read excel sheet
     * @return last data row index
     */
    private static Integer getLastDataRowIndex(Sheet contextTemplateSheet) {
        int rowNumber = FIRST_DATA_ROW_INDEX;
        int columnNumber = 0;
        Cell cell = getCell(contextTemplateSheet, rowNumber, columnNumber);
        while (cell != null) {
            rowNumber++;
            try {
                cell = getCell(contextTemplateSheet, rowNumber, columnNumber);
            } catch (NullPointerException e) {
                LOGGER.debug(e);
                return rowNumber;
            }
        }
        return FIRST_DATA_ROW_INDEX;
    }

    /**
     * Gets cell object from sheet with given row and column indexes.
     *
     * @param contextTemplateSheet excel sheet from which cell is read
     * @param row                  read row index number
     * @param column               read column index number
     */
    private static Cell getCell(Sheet contextTemplateSheet, int row, int column) {
        return contextTemplateSheet.getRow(row).getCell(column);
    }

    /**
     * Gets sheet which name equals given name
     *
     * @param workbook  excel workbook
     * @param sheetName sheet name
     * @return sheet which name equals given name
     */
    private static Sheet getSheet(XSSFWorkbook workbook, String sheetName) {
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            if (workbook.getSheetAt(i).getSheetName().equals(sheetName)) {
                return workbook.getSheetAt(i);
            }
        }
        return null;
    }

    /**
     * Gets Excel Workbook from given location
     *
     * @param location the location of Acl Excel file
     * @return excel workbook object
     */
    private static XSSFWorkbook getXssfWorkbook(String location) throws IOException {
        LOGGER.debug("Opening acl excel file");
        try (FileInputStream excelFile = new FileInputStream(new File(location))) {
            return new XSSFWorkbook(excelFile);
        }
    }


}
