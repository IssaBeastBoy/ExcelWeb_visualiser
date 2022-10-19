package com.backend.backend.uploads;

public class FileResponse {
    private String fileName;
    private String fileSize;
    private String fileDownloadUri;
    private String fileContentType;
    private String userDetails;

    public FileResponse(String fileName, String fileDownloadUri, String fileContentType, long fileSize, String userDetails) {
        super();
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileContentType = fileContentType;
        this.fileSize = Long.toString(fileSize);
        this.userDetails = userDetails;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    public void setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(String userDetails) {
        this.userDetails = userDetails;
    }
}
