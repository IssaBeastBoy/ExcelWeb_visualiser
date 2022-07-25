package com.example.dashboard;

import java.util.*;

public class isUnique {
    private String segment;
    //private String OffersCombination;
    private Map<Integer, List<String>> customerInfo;
    private Dictionary<String, Integer> uniqueCombination;

    public isUnique(String segment, Map<Integer, List<String>> customerInfo){
        this.segment=segment;
        //this.OffersCombination=offersCombination;
        this.customerInfo=customerInfo;
        this.uniqueCombination = new Hashtable<>();
    }

    public Dictionary<String, Integer> getUniqueCombination() {
        setUnique();
        return uniqueCombination;
    }

    private void setUnique(){
        int total = 0;
        for (int index = 0; index < customerInfo.size(); index++){
            List<String> row = customerInfo.get(index+1);
            int customerCount = Integer.parseInt(row.get(10));
            if(uniqueCombination.get(row.get(0))==null && row.get(3).equals(segment)){
                uniqueCombination.put(row.get(0), customerCount);
                total += customerCount;
            }
            else if(uniqueCombination.get(row.get(0))!=null && row.get(3).equals(segment)){
                int tempCount =  uniqueCombination.get(row.get(0));
                uniqueCombination.put(row.get(0),customerCount+tempCount);
                total += customerCount;
            }
        }
        uniqueCombination.put("Total", total);
    }

    private String getCombination(List<String> row){
       return row.get(0);
    }
}
