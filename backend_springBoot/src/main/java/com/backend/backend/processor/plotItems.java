package com.backend.backend.processor;

import java.util.*;

public class plotItems {

    private List<Dictionary<String, Integer>> items = new ArrayList<>();
    private Dictionary<String, Integer> mainItems = new Hashtable();
    private List<String[]> itemDisplay = new ArrayList<>();
    private Dictionary<Integer, List<String>> customerInfo = new Hashtable();
    private int mainIndex;
    private int filterOneIndex;
    private int filterTwoIndex;
    private String filterOneSelect;
    private String filterTwoSelect;

    public plotItems(Dictionary<Integer, List<String>> customerInfo,  Dictionary<String, Integer> mainItems, int mainIndex,
                     int filterOneIndex, int filterTwoIndex, String filterOneSelect, String filterTwoSelect){
        this.mainItems = mainItems;
        this.customerInfo = customerInfo;
        this.mainIndex = mainIndex;
        this.filterOneIndex = filterOneIndex;
        this.filterTwoIndex = filterTwoIndex;
        this.filterOneSelect = filterOneSelect;
        this.filterTwoSelect = filterTwoSelect;
    }

    private void setItems(){
        int sizeNet = 1;
        boolean endloop = false;
        while(!endloop){
            List<String> tempStore = customerInfo.get(sizeNet);
            if(tempStore != null){
                if(mainItems.get(tempStore.get(mainIndex)) != null && tempStore.get(filterOneIndex).equals(filterOneSelect)
                && tempStore.get(filterTwoIndex).equals(filterTwoSelect)){
                    mainItems.put(tempStore.get(mainIndex), mainItems.get(tempStore.get(mainIndex)) + Integer.valueOf(tempStore.get(tempStore.size()-1)));
                }
            }
            else{
                endloop = true;
            }
            sizeNet++;
        }
    }

    public List<String[]> getItemDisplay() {
        for(Enumeration enm = mainItems.keys(); enm.hasMoreElements();)
        {
            String tempKey = (String) enm.nextElement();
            String[] tempItems = {tempKey, String.valueOf(mainItems.get(tempKey))};
            itemDisplay.add(tempItems);
        }
        return itemDisplay;
    }

    public void setItemDisplay(List<String[]> itemDisplay) {

        this.itemDisplay = itemDisplay;
    }
/*
*  FUNCTION BELOW (getMainItems) MUST BE CALLED FIRST BEFORE (getItemsDisplay)
* */
    public Dictionary<String, Integer> getMainItems() {
        setItems();
        return mainItems;
    }

    public void setMainItems(Dictionary<String, Integer> mainItems) {
        this.mainItems = mainItems;
    }
}
