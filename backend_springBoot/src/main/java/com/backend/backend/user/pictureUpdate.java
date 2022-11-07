package com.backend.backend.user;

public class pictureUpdate {
    private Boolean err;
    private String message;
    private String imgLoc;

    public pictureUpdate(Boolean err, String message, String imgLoc) {
        this.err = err;
        this.message = message;
        this.imgLoc = imgLoc;
    }

    public String getImgLoc() {
        return imgLoc;
    }

    public void setImgLoc(String imgLoc) {
        this.imgLoc = imgLoc;
    }

    public Boolean getErr() {
        return err;
    }

    public void setErr(Boolean err) {
        this.err = err;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
