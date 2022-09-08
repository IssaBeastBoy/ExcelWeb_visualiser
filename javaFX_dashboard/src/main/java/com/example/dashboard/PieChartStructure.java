package com.example.dashboard;

import javafx.beans.property.SimpleIntegerProperty;

public class PieChartStructure {
    private final SimpleIntegerProperty combinationAmount;
    private final SimpleIntegerProperty totalCustomers;

    public PieChartStructure(Integer combinationAmount, Integer totalCustomers){
        this.combinationAmount = new SimpleIntegerProperty(combinationAmount);
        this.totalCustomers = new SimpleIntegerProperty(totalCustomers);
    }

    public int getCombinationAmount() {
        return combinationAmount.get();
    }

    public SimpleIntegerProperty combinationAmountProperty() {
        return combinationAmount;
    }

    public void setCombinationAmount(int combinationAmount) {
        this.combinationAmount.set(combinationAmount);
    }

    public int getTotalCustomers() {
        return totalCustomers.get();
    }

    public SimpleIntegerProperty totalCustomersProperty() {
        return totalCustomers;
    }

    public void setTotalCustomers(int totalCustomers) {
        this.totalCustomers.set(totalCustomers);
    }
}
