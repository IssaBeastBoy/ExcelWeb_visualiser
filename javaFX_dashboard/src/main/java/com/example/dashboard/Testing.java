package com.example.dashboard;

public class Testing {
    public static void main(String[] args) {
        Ingest_CustomerInfo customerInfo = new Ingest_CustomerInfo();
        customerInfo.setExcel_loc("C:\\Users\\f5462797\\Applications\\Grad_project\\HOLDINGS_OUTPUT.xlsx");
        customerInfo.parseSheet();
        isUnique unique = new isUnique("Offer_Combo", customerInfo.getCustomerInfo());
        unique.getUniqueCombination();
    }
}
