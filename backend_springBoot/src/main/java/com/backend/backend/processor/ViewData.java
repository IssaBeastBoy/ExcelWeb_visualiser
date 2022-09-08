package com.backend.backend.processor;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.List;

public class ViewData {
    private List<List<String>> tableView;
    private List<List<String>> pieChartView;
    private List<List<String>> BarGraphView;
    private Dictionary<Integer, List<String>> customerInfo;
    private String selectedCol;
    private int min;
    private int max;
    private int size;

    public ViewData(Dictionary<Integer, List<String>> customerInfo, String selectedCol, String min, String max){
        this.customerInfo = customerInfo;
        this.selectedCol = selectedCol;
        this.min = Integer.parseInt(min);
        this.max = Integer.parseInt(max);
        pieChartView = new ArrayList<>();
        tableView = new ArrayList<>();
        BarGraphView = new ArrayList<>();
        size = 0;
    }

    public ViewData(Dictionary<Integer, List<String>> customerInfo, String selectedCol){
        this.customerInfo = customerInfo;
        this.selectedCol = selectedCol;
        pieChartView = new ArrayList<>();
        tableView = new ArrayList<>();
        BarGraphView = new ArrayList<>();
        size = 0;
    }

    public int getSize (){
        setSize();
        return size;
    }

    public List<List<String>> getTableView() {
        setTableView();
        return tableView;
    }

    public List<List<String>> getPieView() {
        setPieView();
        return pieChartView;
    }

    public List<List<String>> getBarGraphView() {
        setBarGraphView();
        return BarGraphView;
    }

    private void setSize(){
        isUnique isUnique = new isUnique(selectedCol, customerInfo);
        Dictionary<String, int[]> getUnique = isUnique.getUniqueCombination();
        size = getUnique.size();
    }

    private void setBarGraphView() {
        isUnique isUnique = new isUnique(selectedCol, customerInfo);
        Dictionary<Integer, String> indexFinder = isUnique.getUniqueCombinationSortedFinder();
        Sorter sorter = new Sorter(isUnique.getOrderedList());
        List<int[]> sorted = sorter.getSortedList();
        for(int parse = 0; sorted.size() > parse; parse++){
            List<String> currAxisValues = new ArrayList<>();
            currAxisValues.add(indexFinder.get(sorted.get(parse)[0]));
            float Percentage = (float) sorted.get(parse)[1]*100/isUnique.getTotal();
            currAxisValues.add(String.valueOf(Percentage));
            if(parse<max  && parse >=min)
                BarGraphView.add(currAxisValues);
            else if (parse>max)
                break;
        }
    }

    private void setPieView() {
        isUnique isUnique = new isUnique(selectedCol, customerInfo);
        Dictionary<Integer, String> indexFinder = isUnique.getUniqueCombinationSortedFinder();
        Sorter sorter = new Sorter(isUnique.getOrderedList());
        List<int[]> sorted = sorter.getSortedList();
        for(int parse = 0; sorted.size() > parse; parse++){
            List<String> currAttributes = new ArrayList<>();
            currAttributes.add(indexFinder.get(sorted.get(parse)[0]));
            currAttributes.add(String.valueOf(sorted.get(parse)[1]));
            float Percentage = (float) sorted.get(parse)[1]*100/isUnique.getTotal();
            currAttributes.add(String.valueOf(Percentage));
            if( parse < max && parse>=min )
                pieChartView.add(currAttributes);
            else if (parse>max)
                break;
        }
    }

    private void setTableView() {
        isUnique isUnique = new isUnique(selectedCol, customerInfo);
        Dictionary<String, int[]> getUnique = isUnique.getUniqueCombination();
        int start = 0;
        for(Enumeration combination = getUnique.keys(); combination.hasMoreElements();)
        {
            List<String> currAttributes = new ArrayList<>();
            String attribute = (String) combination.nextElement();
            int[] tempAmount = getUnique.get(attribute);
            float Percentage = (float) tempAmount[1]*100/isUnique.getTotal();
            currAttributes.add(attribute);
            currAttributes.add(String.valueOf(tempAmount[1]));
            currAttributes.add(String.valueOf(Percentage));
            tableView.add(currAttributes);
        }
    }
}
