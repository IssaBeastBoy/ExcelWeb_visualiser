package com.backend.backend;

public class newUserResponse {
    private String lasNumber;
    private Boolean userAdded;
    private userInformationEntity userAdd;

    public newUserResponse(String lasNumber, Boolean userAdded, userInformationEntity userAdd){
        this.lasNumber = lasNumber;
        this.userAdded = userAdded;
        this.userAdd = userAdd;
    }

    public String getLasNumber() {
        return lasNumber;
    }

    public void setLasNumber(String lasNumber) {
        this.lasNumber = lasNumber;
    }

    public Boolean getUserAdded() {
        return userAdded;
    }

    public void setUserAdded(Boolean userAdded) {
        this.userAdded = userAdded;
    }

    public userInformationEntity getUserAdd() {
        return userAdd;
    }

    public void setUserAdd(userInformationEntity userAdd) {
        this.userAdd = userAdd;
    }
}
