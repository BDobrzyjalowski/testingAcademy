package com.bmw.psmg.sbb.utilities.loaders.option;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellReference;

import java.io.File;
import java.io.IOException;

/**
 * Reader to simplify open and read Microsoft Excel file.
 *
 * @author Karol Poliszkiewicz
 */
public class ExcelReader implements AutoCloseable {

    private static final Logger LOGGER = Logger.getLogger(ExcelReader.class.getName());

    private Workbook workbook;

    /**
     * Open and read Microsoft Excel file.
     *
     * @param resource contains reference to Excel file
     * @throws IOException when cannot open Excel file
     */
    public ExcelReader(File resource) throws IOException {
        LOGGER.debug("Initialize ExcelReader by File:<" + resource.getAbsolutePath() + '>');
        workbook = WorkbookFactory.create(resource);
    }

    /**
     * Finding workbook sheet in Excel file.
     *
     * @param index of workbook sheet
     * @return workbook sheet at index
     */
    public Sheet getSheet(int index) {
        LOGGER.debug("Getting Excel workbook sheet at index:<" + index + '>');
        return workbook.getSheetAt(index);
    }

    /**
     * Converting string index e.g. 'A', 'B' into numeric index position.
     *
     * @param strNum excel letter index
     * @return index as number
     */
    public static int getColumnIndex(String strNum) {
        if (strNum.matches("-?\\d+?")) {
            return Integer.parseInt(strNum);
        }
        return CellReference.convertColStringToIndex(strNum);
    }

    /**
     * Method returns the Excel formula result from cell.
     *
     * @param formulaEvaluator class which evaluating the Excel formula
     * @param cell             to evaluate
     * @return formula result
     */
    public static String evaluateValue(FormulaEvaluator formulaEvaluator, Cell cell) {
        if (cell.getCellType().equals(CellType.STRING)) {
            return formulaEvaluator.evaluate(cell).getStringValue().trim();
        }
        return formulaEvaluator.evaluate(cell).formatAsString();
    }

    /**
     * Getting formula evaluator from existing workbook to read Excel cell formula results.
     *
     * @return Excel cell formula evaluator
     */
    public FormulaEvaluator getFormulaEvaluator() {
        return workbook.getCreationHelper().createFormulaEvaluator();
    }

    /**
     * Closing workbook file.
     *
     * @throws IOException when cannot find file to close or file was closed earlier
     */
    @Override
    public void close() throws IOException {
        LOGGER.debug("Closing Excel workbook");
        workbook.close();
    }

}
