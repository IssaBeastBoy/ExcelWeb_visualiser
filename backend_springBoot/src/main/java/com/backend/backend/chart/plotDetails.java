package com.backend.backend.chart;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class plotDetails {
    private List<String> filterOneOptions = new ArrayList<>();
    private List<String> filterTwoOptions = new ArrayList<>();
    private List<Integer> filter = new ArrayList<>();
    private Dictionary<String, Integer> items = new Hashtable<>();
    private List<String[]> itemDisplay = new ArrayList<>();
    private Boolean err;
    private String errMessage;

    public List<Integer> getFilter() {
        return filter;
    }

    public void setFilter(List<Integer> filter) {
        this.filter = filter;
    }

    public List<String[]> getItemDisplay() {
        return itemDisplay;
    }

    public void setItemDisplay(List<String[]> itemDisplay) {
        this.itemDisplay = itemDisplay;
    }

    public Boolean getErr() {
        return err;
    }

    public void setErr(Boolean err) {
        this.err = err;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public List<String> getFilterOneOptions() {
        return filterOneOptions;
    }

    public void setFilterOneOptions(List<String> filterOneOptions) {
        this.filterOneOptions = filterOneOptions;
    }

    public List<String> getFilterTwoOptions() {
        return filterTwoOptions;
    }

    public void setFilterTwoOptions(List<String> filterTwoOptions) {
        this.filterTwoOptions = filterTwoOptions;
    }

    public Dictionary<String, Integer> getItems() {
        return items;
    }

    public void setItems(Dictionary<String, Integer> items) {
        this.items = items;
    }
}
