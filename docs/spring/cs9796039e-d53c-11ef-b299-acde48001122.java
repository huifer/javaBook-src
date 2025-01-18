package com.huifer.utils.utils;

import com.huifer.utils.entity.ExcelDataObject;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;


public class ExcelReaderImpl implements ExcelReader {

    private XSSFWorkbook excelWBook;
    private XSSFSheet excelWSheet;

    private List<CellRangeAddress> mergedRegions;

    private Logger log = Logger.getLogger(this.getClass().getName());

    /**
     * 具体获取日期 或者其他值
     *
     * @param cell
     * @return
     */
    private static String getValue(Cell cell) {
        if (cell.getCellType() == cell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
            //数值类型又具体区分日期类型，单独处理
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                Date date = cell.getDateCellValue();
                String pattern;
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                return simpleDateFormat.format(date);
            } else {
                return NumberToTextConverter.toText(cell.getNumericCellValue());
            }
        } else {
            return String.valueOf(cell.getStringCellValue());
        }
    }

    /**
     * 打开文件
     */
    @Override
    public void setExcelFile(String path) {
        try {
            FileInputStream ExcelFile = new FileInputStream(path);
            excelWBook = new XSSFWorkbook(ExcelFile);
            ExcelFile.close();
            switchToSheet(0);
        } catch (Exception e) {
            throw new RuntimeException("文件有问题开不开 " + e.getMessage(), e);
        }
    }

    /**
     * 根据 sheet name 设置
     */
    @Override
    public void switchToSheet(String sheetName) throws RuntimeException {
        int sheetIndex = excelWBook.getSheetIndex(sheetName);
        switchToSheet(sheetIndex);
    }

    /**
     * 返回指定 sheet
     */
    @Override
    public void switchToSheet(int number) throws RuntimeException {
        excelWSheet = excelWBook.getSheetAt(number);
        mergedRegions = excelWSheet.getMergedRegions();
    }

    /**
     * 获取整个 sheet
     */
    @Override
    public ExcelDataObject[][] getCurrentSheetData() {
        int usedRows = getRowsUsed();
        ExcelDataObject[][] data = new ExcelDataObject[usedRows][];
        for (int i = 0; i < usedRows; i++) {
            data[i] = getRowData(i);
        }
        return data;
    }

    /**
     * 获取整个 sheet
     */
    @Override
    public ExcelDataObject[][] getSheetData(String sheetName) {
        switchToSheet(sheetName);
        int usedRows = getRowsUsed();
        ExcelDataObject[][] data = new ExcelDataObject[usedRows][];
        for (int i = 0; i <= usedRows; i++) {
            data[i] = getRowData(i);
        }
        return data;
    }

    /**
     * 获取行数据
     */
    @Override
    public ExcelDataObject[] getRowData(int rowNo) {
        int usedColumns = getColumnsUsed(rowNo);
        ExcelDataObject[] rowData = new ExcelDataObject[usedColumns];
        for (int i = 0; i < usedColumns; i++) {
            rowData[i] = getCellData(rowNo, i);
        }
        return rowData;
    }

    /**
     * 获取这个单元格的值
     */
    @Override
    public ExcelDataObject getCellData(int rowNum, int colNum) {
        int region = getMergedRegion(rowNum, colNum);
        XSSFCell cell;
        try {
            cell = region >= 0 ? getMergedRegionStringValue(rowNum, colNum) :
                    excelWSheet.getRow(rowNum).getCell(colNum);
            return getStringValueFromCell(region, cell);
        } catch (Exception e) {
//            log.info("这个单元格没有值 " + e.getMessage());
            return new ExcelDataObject("e", region);
        }
    }

    /**
     * 获取这个单元格的字符串值
     *
     * @param region
     * @param cell
     * @return
     */
    private ExcelDataObject getStringValueFromCell(int region, XSSFCell cell) {

        return new ExcelDataObject(getValue(cell), region);
    }

    /**
     * 行数
     *
     * @return
     */
    private int getRowsUsed() {
        if (excelWSheet == null) {
            return 0;
        }
        return excelWSheet.getLastRowNum();
    }

    @Override
    public int getRols() {
        if (excelWSheet == null) {
            return 0;
        }
        return excelWSheet.getLastRowNum() + 1;
    }


    /**
     * 列数
     *
     * @param rowNo
     * @return
     */
    private int getColumnsUsed(int rowNo) {
        if (excelWSheet == null) {
            return 0;
        }
        return excelWSheet.getRow(rowNo).getPhysicalNumberOfCells();
    }


    @Override
    public int getCols() {
        return excelWSheet.getRow(0).getPhysicalNumberOfCells();
    }


    @Override
    public List<List<ExcelDataObject>> getSheet(String path) {
        setExcelFile(path);

        int rowsUsed = getRols();
        int cols = getCols();
        List<List<ExcelDataObject>> sheet = new ArrayList<>();
        for (int i = 0; i < rowsUsed; i++) {
            List<ExcelDataObject> row = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                Object cellData = getCellData(i, j);
                ExcelDataObject data = (ExcelDataObject) cellData;
                row.add(data);
                String cellData1 = data.getCellData();
                System.out.print(cellData1 + "\t");
            }
            sheet.add(row);
            System.out.println();
        }
        return sheet;
    }

    /**
     * 合并的坐标值
     *
     * @param rowNum
     * @param colNum
     * @return
     */
    private int getMergedRegion(int rowNum, int colNum) {
        for (int i = 0; i < mergedRegions.size(); i++) {
            if (mergedRegions.get(i).isInRange(rowNum, colNum)) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 获取合并单元格的值
     *
     * @param row
     * @param column
     * @return
     */
    private XSSFCell getMergedRegionStringValue(int row, int column) {
        int mergedRegionNumber = getMergedRegion(row, column);
        CellRangeAddress region = excelWSheet.getMergedRegion(mergedRegionNumber);

        int firstRegionColumn = region.getFirstColumn();
        int firstRegionRow = region.getFirstRow();

        return excelWSheet.getRow(firstRegionRow).getCell(firstRegionColumn);
    }
}
