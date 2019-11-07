package com.huifer.utils.utils;

import com.huifer.utils.entity.ExcelDataObject;

import java.util.ArrayList;
import java.util.List;

public class ExcelReaderTest {
    public static void main(String[] args) {
//        ExcelReader excelReader = new ExcelReaderImpl();
//        List<List<ExcelDataObject>> sheet = excelReader.getSheet("E:\\w_pro\\tt\\demo\\spring-source\\src\\main\\resources\\work01.xlsx");
//        System.out.println();

        getSheet();
    }

    private static void getSheet() {
        ExcelReader excelReader = new ExcelReaderImpl();
        excelReader.setExcelFile("E:\\w_pro\\tt\\demo\\spring-source\\src\\main\\resources\\work01.xlsx");

        int rowsUsed = excelReader.getRols();
        int cols = excelReader.getCols();
        List<List<ExcelDataObject>> sheet = new ArrayList<>();
        for (int i = 0; i < rowsUsed; i++) {
            List<ExcelDataObject> row = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                Object cellData = excelReader.getCellData(i, j);
                ExcelDataObject data = (ExcelDataObject) cellData;
                row.add(data);
                String cellData1 = data.getCellData();
                System.out.print(cellData1 + "\t");
            }
            sheet.add(row);
            System.out.println();
        }
    }
}