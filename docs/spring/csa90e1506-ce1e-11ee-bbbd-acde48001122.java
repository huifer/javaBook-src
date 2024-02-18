package com.huifer.utils.entity;

public class ExcelDataObject {
    /**
     * 数据
     */
    private String cellData;
    private int regionId;

    public ExcelDataObject() {
    }

    public ExcelDataObject(String cellData, int regionId) {
        this.cellData = cellData;
        this.regionId = regionId;
    }

    public String getCellData() {
        return cellData;
    }

    public void setCellData(String cellData) {
        this.cellData = cellData;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }
}