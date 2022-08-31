package com.example.dashboard;

import java.util.*;

public class isUnique {
    private String segment;
    private int total;
    private Dictionary<Integer, List<String>> customerInfo;
    private Dictionary<String, int[]> uniqueCombination;
    private List<int[]> orderedList;

    public isUnique(String segment, Dictionary<Integer, List<String>> customerInfo){
        this.segment=segment;
        //this.OffersCombination=offersCombination;
        this.customerInfo=customerInfo;
        this.uniqueCombination = new Hashtable<>();
        orderedList = new ArrayList<>();
        total = 0;
    }

    public int getTotal() {
        return total;
    }

    public List<int[]> getOrderedList() {
        return orderedList;
    }

    public Dictionary<String, int[]> getUniqueCombination() {
        setUnique();
        return uniqueCombination;
    }

    private void setUnique(){
        int position = 0;
        int colIndex = customerInfo.get(-1).indexOf(segment);
        for (int index = 0; index < customerInfo.size(); index++){
            List<String> row = customerInfo.get(index+1);
            int customerCount = Integer.parseInt(row.get(row.size()-1));
            if(uniqueCombination.get(row.get(colIndex))==null){
                int[] tempArry = {position, customerCount};
                orderedList.add(tempArry);
                uniqueCombination.put(row.get(colIndex), tempArry);
                position++;
                total += customerCount;
            }
            else if(uniqueCombination.get(row.get(colIndex))!=null){
                int[] tempArry =  uniqueCombination.get(row.get(0));
                int indexLocation = tempArry[0];
                tempArry[1] = tempArry[1]+customerCount;
                orderedList.set(indexLocation, tempArry);
                uniqueCombination.put(row.get(colIndex), tempArry);
                total += customerCount;
            }
        }
    }

    private String getCombination(List<String> row){
       return row.get(0);
    }
}
