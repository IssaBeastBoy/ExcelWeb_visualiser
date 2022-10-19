package com.backend.backend.uploads;

import java.util.List;

public class uploadFilesResponse {
    private List<String> filesName;
    private List<String> columnNames;


    public uploadFilesResponse(List<String> filesName) {
        this.filesName = filesName;
    }

    public List<String> getFilesName() {
        return filesName;
    }

    public void setFilesName(List<String> filesName) {
        this.filesName = filesName;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(List<String> columnNames) {
        this.columnNames = columnNames;
    }
}
