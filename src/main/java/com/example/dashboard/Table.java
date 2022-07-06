package com.example.dashboard;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Table {
    private final SimpleStringProperty combination;
    private final SimpleIntegerProperty combinationNumber;
    private final SimpleIntegerProperty customerCount;

    public Table(String combinations, int combinationNumber, int customerCount){
        this.combinationNumber = new SimpleIntegerProperty(combinationNumber);
        this.combination = new SimpleStringProperty(combinations);
        this.customerCount = new SimpleIntegerProperty(customerCount);
    }

    public String getCombination(){
        return combination.get();
    }

    public int getCombinationNumber(){
        return combinationNumber.get();
    }

    public int getCustomerCount() {
        return customerCount.get();
    }

    public SimpleIntegerProperty customerCountProperty() {
        return customerCount;
    }

    public void setCombination(String combination) {
        this.combination.set(combination);
    }

    public void setCombinationNumber(int combinationNumber) {
        this.combinationNumber.set(combinationNumber);
    }

    public void setCustomerCount(int customerCount) {
        this.customerCount.set(customerCount);
    }
}
