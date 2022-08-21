package com.backend.backend;

import java.util.List;

public class TestDB {

    public static void main(String[] args) {
        userInfoTransactions userInfo = new userInfoTransactions();
        userInformationEntity userAdd = userInfo.findUser("4").get(0); //if user not found later
//        new userInformationEntity("951117", "testing","testing",
//                "testing@testing", "123456", "LAS0003");
        userAdd.setPassword("951117");
        Boolean user = userInfo.updateUser(userAdd);
    }
}
