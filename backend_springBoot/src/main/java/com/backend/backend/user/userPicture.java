package com.backend.backend.user;

import org.springframework.web.multipart.MultipartFile;

public class userPicture {
    private String userName;
    private String imgDir;
    private MultipartFile imgFile;

    public MultipartFile getImgFile() {
        return imgFile;
    }

    public void setImgFile(MultipartFile imgFile) {
        this.imgFile = imgFile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImgDir() {
        return imgDir;
    }

    public void setImgDir(String imgDir) {
        this.imgDir = imgDir;
    }
}
