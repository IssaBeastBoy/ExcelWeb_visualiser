package com.backend.backend.user;

import java.io.File;

public class userProfile {
    private Boolean err;
    private String errMessage;
    private String imgLoc;

    public userProfile(Boolean err, String errMessage, String imgLoc) {
        this.err = err;
        this.errMessage = errMessage;
        this.imgLoc = imgLoc;
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

    public String getImgLoc() {
        return imgLoc;
    }

    public void setImgLoc(String imgLoc) {
        this.imgLoc = imgLoc;
    }
}
