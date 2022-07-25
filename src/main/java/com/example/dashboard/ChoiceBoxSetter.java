package com.example.dashboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChoiceBoxSetter {
    private Map<Integer, List<String>> customerInfo;
    private List<String> dynamicArray;
    private int column;

    public ChoiceBoxSetter(int column, Map<Integer, List<String>> customerInfo){
        this.column = column;
        dynamicArray = new ArrayList<>();
        this.customerInfo = customerInfo;
    }

    public List<String> getDynamicArray() {
        setDynamicArray();
        return dynamicArray;
    }

    private void setDynamicArray(){
        for (int index = 0; index < customerInfo.size(); index++){
            List<String> row = customerInfo.get(index+1);
            if(!dynamicArray.contains(row.get(column))){
                dynamicArray.add(row.get(column));
            }
        }
    }
}
