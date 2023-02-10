package com.spring_boot.backend.restapi.model;


public class ResponseData {

    private String fileName;
    private String downloadURL;
    private String fileType;
    private long filesize;

    

    public ResponseData(String fileName, String downloadURL, String fileType, long filesize) {
        this.fileName = fileName;
        this.downloadURL = downloadURL;
        this.fileType = fileType;
        this.filesize = filesize;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getDownloadURL() {
        return downloadURL;
    }
    public void setDownloadURL(String downloadURL) {
        this.downloadURL = downloadURL;
    }
    public String getFileType() {
        return fileType;
    }
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    public long getFilesize() {
        return filesize;
    }
    public void setFilesize(long filesize) {
        this.filesize = filesize;
    }

    
    
}
