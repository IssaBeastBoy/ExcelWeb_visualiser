package com.backend.backend.chart;

import java.util.*;

public class pivotTableGen {
    private int verticalIndex;
    private int horizontalIndex;
    private Dictionary<String, List<String>> pivotTableValues;
    private Dictionary<String, Integer> pivotValue;
    private String verticalValues;
    private String horizontalValues;
    private List<String> pivotTableHeader;
    private List<String> columnNames;
    private List<String> rowNames;
    private int totalCustomers;
    private String displayType;
    private Dictionary<String, List<List<String>>> pivotTableBody;
    private Dictionary<Integer, List<String>> customerInfo;
    private Dictionary<String, Integer> verticalValuesIndex;

    public pivotTableGen(int verticalIndex, String verticalValues, int horizontalIndex, String horizontalValues,
                         Dictionary<Integer, List<String>> customerInfo, int totalCustomers, String displayType) {
        this.verticalIndex = verticalIndex;
        this.horizontalIndex = horizontalIndex;
        this.customerInfo = customerInfo;
        this.pivotValue = new Hashtable<>();
        this.horizontalValues = horizontalValues;
        this.verticalValues = verticalValues;
        this.pivotTableValues = new Hashtable<>();
        this.pivotTableHeader = new ArrayList<>();
        this.columnNames = new ArrayList<>();
        this.rowNames = new ArrayList<>();
        this.verticalValuesIndex = new Hashtable<>();
        this.pivotTableBody = new Hashtable<>();
        this.totalCustomers = totalCustomers;
        this.displayType = displayType;
    }

    private void setPivotValue(){
        int parse = 1;
        pivotTableHeader.add(horizontalValues + "\n vs \n"+verticalValues);
        while(customerInfo.get(parse) != null){
            List<String> row = customerInfo.get(parse);
            String tempValueCombination = row.get(horizontalIndex)+"^"+row.get(verticalIndex);
            if (pivotValue.get(tempValueCombination) != null && (!row.get(horizontalIndex).contains("No_") &&
                    !row.get(verticalIndex).contains("No_"))) {
                pivotValue.put(tempValueCombination, pivotValue.get(tempValueCombination) + Integer.parseInt(row.get(row.size()-1)));
            }
            else if (!row.get(horizontalIndex).contains("No_") && !row.get(verticalIndex).contains("No_")) {
                if(!columnNames.contains(row.get(verticalIndex))) {
                    columnNames.add(row.get(verticalIndex));
                    pivotTableHeader.add(row.get(verticalIndex));
                }
                if(!rowNames.contains(row.get(horizontalIndex)))
                    rowNames.add(row.get(horizontalIndex));
                pivotValue.put(tempValueCombination, Integer.parseInt(row.get(row.size()-1)));
            }
            parse++;
        }
    }

    private void setPivotTableValues(){
        List<List<String>> bodyItems = new ArrayList<>();
        for(Enumeration enm = pivotValue.keys(); enm.hasMoreElements();){
            String combination = (String) enm.nextElement();
            String tempVerticalValue = combination.split("\\^")[1];
            String tempHorizontalValue = combination.split("\\^")[0];
            if(pivotTableValues.get(tempHorizontalValue) != null ){
                List<String> tempValue = pivotTableValues.get(tempHorizontalValue);
                tempValue.add(String.valueOf(pivotValue.get(combination)));
                pivotTableValues.put(tempHorizontalValue, tempValue);
            }
            else{
                List<String> tempValue = new ArrayList<>();
                tempValue.add(String.valueOf(pivotValue.get(combination)));
                pivotTableValues.put(tempHorizontalValue, tempValue);
            }

        }
        pivotTableHeader.add("Total");
        pivotTableValues.put("Header", pivotTableHeader);
        pivotTableValues.put("colNames", columnNames);
        pivotTableValues.put("rowNames", rowNames);
    }

    public Dictionary<String, List<String>> getPivotTableValues() {
        setPivotValue();
        setPivotTableValues();
        return pivotTableValues;
    }

    private void setPivotTableBody(){
        List<List<String>> bodyItems = new ArrayList<>();
        for(int parseHorizontal=0; parseHorizontal < rowNames.size(); parseHorizontal++){
            List<String> rowValues = new ArrayList<>();
            rowValues.add(rowNames.get(parseHorizontal));
            int rowSum=0;
            double rowPercent=0.0;
            for(int parseVertical = 0; parseVertical < columnNames.size(); parseVertical++){
                if(displayType.equals("number")) {
                    rowSum+= pivotValue.get(rowNames.get(parseHorizontal) + "^" + columnNames.get(parseVertical));
                    rowValues.add(String.valueOf(pivotValue.get(rowNames.get(parseHorizontal) + "^" + columnNames.get(parseVertical))));
                }
                else{
                    double currCount = (double) (pivotValue.get(rowNames.get(parseHorizontal)+"^"+columnNames.get(parseVertical)))*100/totalCustomers;
                    rowPercent += currCount;
                    rowValues.add(String.valueOf(String.format("%.5f", currCount)));
                }
            }
            if(displayType.equals("number"))
                rowValues.add(String.valueOf(rowSum));
            else
                rowValues.add(String.valueOf(String.format("%.5f", rowPercent)));
            bodyItems.add(rowValues);
        }
        List<String> rowValues = new ArrayList<>();
        for(int parse=0; parse < bodyItems.size(); parse++){
            List<String> tempStorage = bodyItems.get(parse);
            for(int index=1; index < tempStorage.size(); index++){
                if(parse==0){
                    rowValues.add(tempStorage.get(index));
                }
                else{
                    if(displayType.equals("number")){
                        int currNum = Integer.parseInt(rowValues.get(index-1))+ Integer.parseInt(tempStorage.get(index));
                        rowValues.set(index-1,String.valueOf(currNum));
                    }
                    else{
                        double currNum = Double.parseDouble(rowValues.get(index-1))+ Double.parseDouble(tempStorage.get(index));
                        rowValues.set(index-1, String.valueOf(String.format("%.5f", currNum)));
                    }
                }
            }
        }
        rowValues.add(0, "Total");
        bodyItems.add(rowValues);
        pivotTableBody.put("Body", bodyItems);
    }

    public Dictionary<String, List<List<String>>> getPivotTableBody() {
        setPivotTableBody();
        return pivotTableBody;
    }
}
