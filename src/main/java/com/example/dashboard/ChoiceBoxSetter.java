package com.example.dashboard;

import javafx.fxml.FXML;

import java.util.*;

public class ChoiceBoxSetter<K,V> {
    private Dictionary<K, V> choiceBoxItems;
    private List<String> dynamicArray;

    public ChoiceBoxSetter( Dictionary<K, V> choiceBoxItems){
        this.choiceBoxItems = choiceBoxItems;
    }

    public List<String> getDynamicArray() {
        return dynamicArray;
    }

    public void setDynamicArrayColumn(int column){
        dynamicArray = new ArrayList<>();
        for (int index = 0; index < choiceBoxItems.size(); index++){
            List<String> row = (List<String>) choiceBoxItems.get(index+1);
            if(!dynamicArray.contains(row.get(column))){
                dynamicArray.add(row.get(column));
            }
        }
    }


    public void setDynamicArraySegmentOfferRemoveVone(){
        dynamicArray = new ArrayList<>();
        for (Enumeration i = choiceBoxItems.elements(); i.hasMoreElements();){
            dynamicArray.add((String) i.nextElement());
        }
    }

    public void setDynamicArraySegmentBoundApplyVone(){
        dynamicArray = new ArrayList<>();
        for (int key = 1; key < choiceBoxItems.size(); key++){
            if (key+1 == choiceBoxItems.size()){
                dynamicArray.add("Show all");
            }
            else {
                dynamicArray.add("Top "+ (key+1));
            }
        }
    }

}
