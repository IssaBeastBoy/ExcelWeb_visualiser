package com.backend.backend;

import java.util.List;

public class uploadFilesResponse {
    private List<String> filesName;

    public uploadFilesResponse(List<String> filesName) {
        this.filesName = filesName;
    }

    public List<String> getFilesName() {
        return filesName;
    }

    public void setFilesName(List<String> filesName) {
        this.filesName = filesName;
    }
}
