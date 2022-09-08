package com.backend.backend.processor;

import java.util.ArrayList;
import java.util.List;

public class Sorter {

    private List<int[]> sortedList;

    public Sorter(List<int[]> ordered){
        sortedList = ordered;
    }

    public List<int[]> getSortedList() {
        insertionSort();
        List<int[]> temp = new ArrayList<>();
        for(int parse = sortedList.size()-1; parse >=0; parse--){
            temp.add(sortedList.get(parse));
        }
        return temp;
    }

    private void insertionSort() {
        int n = sortedList.size();
        for (int j = 1; j < n; j++) {
            int key = sortedList.get(j)[1];
            //sortedList.remove(j);
            int i = j - 1;
            while ((i > -1) && (sortedList.get(i)[1] > key)) {
                sortedList.set(i+1, sortedList.get(i));
                //sortedList[i + 1] = sortedList[i];
                i--;
            }
            int[] tempArry = {j, key};
            sortedList.set(i+1, tempArry);
            //sortedList[i + 1] = key;
        }
    }
}
