package com.backend.backend;

public class FileResponse {
    private String fileName;
    private String fileSize;
    private String fileDownloadUri;
    private String fileContentType;

    public FileResponse(String fileName, String fileDownloadUri, String fileContentType, long fileSize) {
        super();
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileContentType = fileContentType;
        this.fileSize = Long.toString(fileSize);
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
}
