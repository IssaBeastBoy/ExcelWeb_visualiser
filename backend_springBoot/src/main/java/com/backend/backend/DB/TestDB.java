package com.backend.backend.DB;

import com.backend.backend.user.userInformationEntity;

public class TestDB {

    public static void main(String[] args) {
        userInfoTransactions userInfo = new userInfoTransactions();
        userInformationEntity userAdd = userInfo.findUser("1").get(0); //if user not found later
//        new userInformationEntity("951117", "testing","testing",
//                "testing@testing", "123456", "LAS0003");
//        Boolean user = userInfo.deleteUser(userAdd);
    }
}
