package com.example.dashboard;

import java.util.*;

public class PivotTableSetup {
    private List<String> Combinations;
    private Dictionary<String, int[]> pivotValues;
    private List<String> vertical;
    private List<String> horzitonal;
    private int numberHoz;
    private int numberVert;
    private int pivotTotalCount;
    private Dictionary<Integer, List<String>> customerInfo;
    private List<int[]> forSorting;
    private List<String> sortedVertical;
    private List<String> sortedHorzitonal;
    private int verticalDisplay;
    private int horzitonalDisplay;
    private int endParseValue;

    public PivotTableSetup(List<String> vertical, List<String> horzitonal, Dictionary<Integer, List<String>> customerInfo, int numberHoz,
                           int numberVert, int horzitonalDisplay, int verticalDisplay){
        this.vertical = vertical;
        this.horzitonal = horzitonal;
        this.customerInfo = customerInfo;
        this.numberHoz = numberHoz;
        this.horzitonalDisplay = horzitonalDisplay;
        this.verticalDisplay = verticalDisplay;
        this.numberVert = numberVert;
        pivotValues = new Hashtable<>();
        Combinations = new ArrayList<>();
        forSorting = new ArrayList<>();
        sortedVertical = new ArrayList<>();
        sortedHorzitonal = new ArrayList<>();
        pivotTotalCount = 0;
        endParseValue = verticalDisplay>horzitonalDisplay ? verticalDisplay:horzitonalDisplay;
    }

    public Dictionary<String, int[]> getPivotValues() { // MUST BE CALLED FIRST FOR INITIALISATION BEFORE THE OTHER GETTERS
        aggregateCombos();
        setPivotValues();
        setForSorting();
        MatrixSetup();
        setPivotTotalCount();
        return pivotValues;
    }

    public List<String> getSortedVertical() {
        return sortedVertical;
    }

    public List<String> getSortedHorzitonal() {
        return sortedHorzitonal;
    }

    private void setPivotTotalCount() {
        for (int parseHorzi = 0; parseHorzi < sortedHorzitonal.size(); parseHorzi++) {
            for (int parseVert = 0; parseVert < sortedVertical.size(); parseVert++) {
                String currCombo = sortedHorzitonal.get(parseHorzi) + ","
                        + sortedVertical.get(parseVert);
                pivotTotalCount += pivotValues.get(currCombo)[1];
            }
        }
    }

    public int getPivotTotalCount() {
        return pivotTotalCount;
    }

    private void aggregateCombos(){
        int index = 0;
        for (int verIndex=0; verIndex<vertical.size(); verIndex++){
            for(int horzIndex = 0; horzIndex< horzitonal.size(); horzIndex++){
                if(!vertical.get(verIndex).contains("No_") &&  !horzitonal.get(horzIndex).contains("No_")) {
                    String combination = vertical.get(verIndex) + "," + horzitonal.get(horzIndex);
                    Combinations.add(combination);
                    int[] tempValue = {index, 0};
                    pivotValues.put(combination, tempValue);
                    index++;
                }
            }
        }
    }

    private void setPivotValues(){
        for(int parseCustomersInfo =0; parseCustomersInfo< customerInfo.size(); parseCustomersInfo++){
            List<String> tempCustomerInfo_Store = customerInfo.get(parseCustomersInfo+1);
            if (tempCustomerInfo_Store != null){
                String tempCombination = tempCustomerInfo_Store.get(numberHoz-1)+","+tempCustomerInfo_Store.get(numberVert-1);
                if (pivotValues.get(tempCombination)!=null){
                    int[] tempValue = pivotValues.get(tempCombination);
                    tempValue[1] = tempValue[1]+Integer.parseInt(tempCustomerInfo_Store.get(tempCustomerInfo_Store.size()-1));
                    pivotValues.put(tempCombination, tempValue);
                }
            }
        }
    }

    private void setForSorting(){
        for (Enumeration i = pivotValues.elements(); i.hasMoreElements();)
        {
            forSorting.add((int[]) i.nextElement());
        }
        Sorter sorter = new Sorter(forSorting);
        forSorting = sorter.getSortedList();
    }

    private void MatrixSetup(){
        for (int parseSortedList =forSorting.size()-1; parseSortedList >= 0 ; parseSortedList--){
            String[] tempCombinationValues = Combinations.get(forSorting.get(parseSortedList)[0]).split(",");
            if(sortedHorzitonal.size() == horzitonalDisplay && sortedVertical.size() == verticalDisplay)
                break;
            if(sortedHorzitonal.size() < horzitonalDisplay){
                if(sortedHorzitonal.size() == 0){
                    sortedHorzitonal.add(tempCombinationValues[0]);
                }
                else if(!sortedHorzitonal.contains(tempCombinationValues[0])){
                    sortedHorzitonal.add(tempCombinationValues[0]);
                }
            }
            if(sortedVertical.size() < verticalDisplay){
                if(sortedVertical.size() == 0){
                    sortedVertical.add(tempCombinationValues[1]);
                }
                else if(!sortedVertical.contains(tempCombinationValues[1])){
                    sortedVertical.add(tempCombinationValues[1]);
                }
            }
        }
    }

}
