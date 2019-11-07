package com.huifer.utils.utils;


import com.huifer.utils.entity.ExcelDataObject;

import java.util.List;

public interface ExcelReader {
    public void setExcelFile(String path);

    public void switchToSheet(String sheetName) throws RuntimeException;

    public void switchToSheet(int number) throws RuntimeException;

    public ExcelDataObject[][] getCurrentSheetData();

    public ExcelDataObject[][] getSheetData(String sheetName);

    public ExcelDataObject[] getRowData(int rowNo);

    public ExcelDataObject getCellData(int rowNum, int colNum);

    public List<List<ExcelDataObject>> getSheet(String path);

    public int getRols();

    public int getCols();
}
