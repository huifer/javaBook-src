package com.huifer.utils.utils;


import com.huifer.utils.entity.ExcelDataObject;

import java.util.List;

public interface ExcelReader {
    void setExcelFile(String path);

    void switchToSheet(String sheetName) throws RuntimeException;

    void switchToSheet(int number) throws RuntimeException;

    ExcelDataObject[][] getCurrentSheetData();

    ExcelDataObject[][] getSheetData(String sheetName);

    ExcelDataObject[] getRowData(int rowNo);

    ExcelDataObject getCellData(int rowNum, int colNum);

    List<List<ExcelDataObject>> getSheet(String path);

    int getRols();

    int getCols();
}
