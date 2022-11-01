package com.backend.backend.chart;

public class pivotTableRequest {
    private String excelLoc;
    private String verticalSelect;
    private String horizontalSelect;
    private String displayType;

    public String getDisplayType() {
        return displayType;
    }

    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }

    public String getExcelLoc() {
        return excelLoc;
    }

    public void setExcelLoc(String excelLoc) {
        this.excelLoc = excelLoc;
    }

    public String getVerticalSelect() {
        return verticalSelect;
    }

    public void setVerticalSelect(String verticalSelect) {
        this.verticalSelect = verticalSelect;
    }

    public String getHorizontalSelect() {
        return horizontalSelect;
    }

    public void setHorizontalSelect(String horizontalSelect) {
        this.horizontalSelect = horizontalSelect;
    }
}
