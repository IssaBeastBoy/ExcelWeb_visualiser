package com.backend.backend.chart;

import java.util.*;

public class pivotDetails {
    private Dictionary<String, List<String>> values;
    private Dictionary<String, List<List<String>>> pivotTable;

    private Boolean err;
    private String errMessage;
    private int totalCustonerCount;


    public pivotDetails(Dictionary<String, List<String>> values, boolean err, String errMessage,
                        Dictionary<String, List<List<String>>> pivotTable, int totalCustomerCount){
        this.values = values;
        this.err = err;
        this.errMessage = errMessage;
        this.pivotTable = pivotTable;
        this.totalCustonerCount = totalCustomerCount;
    }

    public Dictionary<String, List<String>> getValues() {
        return values;
    }

    public Dictionary<String, List<List<String>>> getPivotTable() {
        return pivotTable;
    }

    public Boolean getErr() {
        return err;
    }

    public void setErr(Boolean err) {
        this.err = err;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public int getTotalCustonerCount() {
        return totalCustonerCount;
    }

    public void setTotalCustonerCount(int totalCustonerCount) {
        this.totalCustonerCount = totalCustonerCount;
    }
}
