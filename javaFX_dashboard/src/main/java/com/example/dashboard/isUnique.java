package com.example.dashboard;

import java.util.*;

public class isUnique {
    private String segment;
    //private String OffersCombination;
    private int total;
    private Dictionary<Integer, List<String>> customerInfo;
    private Dictionary<String, int[]> uniqueCombination;
    private Dictionary<Integer, String> uniqueCombinationIndex;
    private List<int[]> orderedList;

    public isUnique(String segment, Dictionary<Integer, List<String>> customerInfo){
        this.segment=segment;
        //this.OffersCombination=offersCombination;
        this.customerInfo=customerInfo;
        this.uniqueCombination = new Hashtable<>();
        uniqueCombinationIndex = new Hashtable<>();
        orderedList = new ArrayList<>();
        total = 0;
    }

    public int getTotal() {
        return total;
    }

    public List<int[]> getOrderedList() {
        return orderedList;
    }

    public Dictionary<Integer, String> getUniqueCombinationIndex() {
        setUnique();
        return uniqueCombinationIndex;
    }

    private void setUnique(){
        int position = 0;
        for (int index = 0; index < customerInfo.size(); index++){
            List<String> row = customerInfo.get(index+1);
            int customerCount = Integer.parseInt(row.get(10));
            if(uniqueCombination.get(row.get(0))==null && row.get(3).equals(segment)){
                int[] tempArry = {position, customerCount};
                orderedList.add(tempArry);
                uniqueCombinationIndex.put(tempArry[0], row.get(0));
                uniqueCombination.put(row.get(0), tempArry);
                position++;
                total += customerCount;
            }
            else if(uniqueCombination.get(row.get(0))!=null && row.get(3).equals(segment)){
                int[] tempArry =  uniqueCombination.get(row.get(0));
                int indexLocation = tempArry[0];
                tempArry[1] = tempArry[1]+customerCount;
                orderedList.set(indexLocation, tempArry);
                uniqueCombinationIndex.put(tempArry[0], row.get(0));
                uniqueCombination.put(row.get(0), tempArry);
                total += customerCount;
            }
        }
    }

    private String getCombination(List<String> row){
       return row.get(0);
    }
}
