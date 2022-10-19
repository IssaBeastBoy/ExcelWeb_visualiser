package com.backend.backend.chart;

import java.util.List;

public class columnNames {
    private List<String> columnNames;
    private Boolean err;
    private String errMessage;

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public Boolean getErr() {
        return err;
    }

    public void setErr(Boolean err) {
        this.err = err;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(List<String> columnNames) {
        this.columnNames = columnNames;
    }
}
