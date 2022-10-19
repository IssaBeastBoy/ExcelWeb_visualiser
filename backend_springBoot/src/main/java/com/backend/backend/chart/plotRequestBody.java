package com.backend.backend.chart;

public class plotRequestBody {
    private String Main;
    private String filterOne;
    private String filterTwo;
    private String filterOneSelect;
    private String filterTwoSelect;
    private String mainCount;
    private String excelLoc;

    public String getFilterOneSelect() {
        return filterOneSelect;
    }

    public void setFilterOneSelect(String filterOneSelect) {
        this.filterOneSelect = filterOneSelect;
    }

    public String getFilterTwoSelect() {
        return filterTwoSelect;
    }

    public void setFilterTwoSelect(String filterTwoSelect) {
        this.filterTwoSelect = filterTwoSelect;
    }

    public String getExcelLoc() {
        return excelLoc;
    }

    public void setExcelLoc(String excelLoc) {
        this.excelLoc = excelLoc;
    }

    public String getMain() {
        return Main;
    }

    public void setMain(String main) {
        Main = main;
    }

    public String getFilterOne() {
        return filterOne;
    }

    public void setFilterOne(String filterOne) {
        this.filterOne = filterOne;
    }

    public String getFilterTwo() {
        return filterTwo;
    }

    public void setFilterTwo(String filterTwo) {
        this.filterTwo = filterTwo;
    }

    public String getMainCount() {
        return mainCount;
    }

    public void setMainCount(String mainCount) {
        this.mainCount = mainCount;
    }
}
