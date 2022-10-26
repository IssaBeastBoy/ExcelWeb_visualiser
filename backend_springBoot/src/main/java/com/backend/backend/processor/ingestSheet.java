package com.backend.backend.processor;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import org.apache.poi.ss.usermodel.*;
import com.monitorjbl.xlsx.StreamingReader;

public class ingestSheet {
    private String excel_loc="";
    private boolean throwErr = false;
    private String err_Message = "";
    private Dictionary<String, String> combinations = new Hashtable<>();
    private Dictionary<String, String> relationshipStatus = new Hashtable<>();
    private Dictionary<Integer, List<String>> customerInfo;
    private Dictionary<Integer, List<String>> isColsValues;
    private Dictionary<Integer, Dictionary<String, int[]>> colItemsCustomerCount;
    private Dictionary<Integer, Integer> corruptedRows;
    private static Row.MissingCellPolicy xRow;
    private List<String> customerDetails = new ArrayList<>();
    private int totalCustomerCount;
    private String filterOne;
    private String filterTwo;

    public ingestSheet( String filterOne, String filterTwo) {
        this.filterOne = filterOne;
        this.filterTwo = filterTwo;
        isColsValues = new Hashtable<>();
        totalCustomerCount = 0;
        colItemsCustomerCount = new Hashtable<>();
        combinations.put("IN", "Insure");
        combinations.put("SL", "SecuredLend");
        combinations.put("UL", "UnsecuredLend");
        combinations.put("CO", "Connect");
        combinations.put("FU", "Fusion");
        combinations.put("IV", "Invest");
        combinations.put("TR", "Transact");
        relationshipStatus.put("S", "Single");
        relationshipStatus.put("W", "Widow");
        relationshipStatus.put("M", "Married");
        relationshipStatus.put("D", "Divorced");
        relationshipStatus.put("U", "Unknown");
        customerInfo = new Hashtable<>();
        corruptedRows = new Hashtable<>();
    }

    public boolean isThrowErr() {
        return throwErr;
    }

    private void setThrowErr(boolean throwErr) {
        this.throwErr = throwErr;
    }

    public Dictionary<Integer,List<String>> getIsColsValues() {
        return isColsValues;
    }

    public String getExcel_loc() {
        return excel_loc;
    }

    public Dictionary<Integer, List<String>> getCustomerInfo() {
        return customerInfo;
    }

    public void setExcel_loc(String excel_loc) {
        this.excel_loc = excel_loc;
    }

    public String getErr_Message() {
        return err_Message;
    }

    private void setErr_Message(String err_Message) {
        this.err_Message = err_Message;
    }

    public List<String> getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerDetails(List<String> customerDetails) {
        this.customerDetails = customerDetails;
    }

    private String setBend(String input) {
        String[] bend = input.split("\\.");
        return bend[1].trim();
    }

    private String setOffers(String offerCodes) {
        String[] chars = offerCodes.split("");
        String offerNames = "";
        if (chars.length % 2 == 0) {
            int charCount = 0;
            String offer = "";
            if (chars.length >= 4) {
                for (int index = 0; index < chars.length; index++) {
                    if (charCount == 2) {
                        offerNames = combinations.get(offer) + ", " + offerNames;
                        charCount = 0;
                        offer = "";
                    }
                    offer += chars[index];
                    charCount++;
                }
                offerNames = offerNames + " " + combinations.get(offer);
            } else
                offerNames = combinations.get(offerCodes);
        } else
            throw new RuntimeException("Offer code does not exist");
        return offerNames;
    }

    public void parseColumns(){
            int rowCount = 1;
            String sheetName = "";
            int colCount = 0;
            int cellNumber = 0;
            try {
                FileInputStream input = new FileInputStream(new File(getExcel_loc()));
                Workbook workbook = StreamingReader.builder()
                        .rowCacheSize(3000)
                        .bufferSize(6000)
                        .open(input);

                //workbook.setMissingCellPolicy(xRow.CREATE_NULL_AS_BLANK);
                DataFormatter dataFormatter = new DataFormatter();
                Iterator<Sheet> sheets = workbook.sheetIterator();

                while (sheets.hasNext()) {
                    Sheet sh = sheets.next();
                    sheetName = sh.getSheetName();
                    Iterator<Row> parseRow = sh.iterator();
                    rowCount = 1;
                    while (parseRow.hasNext() && rowCount == 1) {
                        Row row = parseRow.next();
                        Iterator<Cell> parseCell = row.cellIterator();// .iterator();
                        cellNumber=1;
                        while (parseCell.hasNext()) {
                            Cell cell = parseCell.next();
                            String cellValue = cell.getStringCellValue(); //dataFormatter.formatCellValue(cell.toString());
                            if (rowCount == 1 && !cellValue.equals("Total_Customers")) {
                                customerDetails.add(cellValue);
                            }
                            else{
                                break;
                            }
                        }
                        rowCount++;
                    }
                }
            }
            catch (Exception ex){
                throwErr = true;
                setErr_Message(ex + "\n\t"+"Excel document at: "+ getExcel_loc()+ "\n\t" +"Sheet name: " + sheetName + "\n \t Row Number: " + rowCount + "\n \t Column Number: " + cellNumber + "\n \n Error message -> \n" + ex + "\n \n Error has occured, please check the given locations...");
            }
    }

    public void parseSheet(){
        int rowCount = 1;
        String sheetName = "";
        int colCount = 0;
        int cellNumber = 0;
        try {
            FileInputStream input = new FileInputStream(new File(getExcel_loc()));
            Workbook workbook =  StreamingReader.builder()
                    .rowCacheSize(3000)
                    .bufferSize(6000)
                    .open(input);

            //workbook.setMissingCellPolicy(xRow.CREATE_NULL_AS_BLANK);
            DataFormatter dataFormatter = new DataFormatter();
            Iterator<Sheet> sheets = workbook.sheetIterator();

            while (sheets.hasNext()){
                Sheet sh = sheets.next();
                sheetName = sh.getSheetName();
                Iterator<Row> parseRow = sh.iterator();
                rowCount = 1;
                while (parseRow.hasNext()){
                    Row row = parseRow.next();
                    Iterator<Cell> parseCell = row.cellIterator();// .iterator();
                    //Cell cells = row.getCell(1)
                    cellNumber=1;
                    List<String> columns = new ArrayList<>();
                    while (parseCell.hasNext()){
                        Cell cell = parseCell.next();
                        String cellValue =cell.getStringCellValue(); //dataFormatter.formatCellValue(cell.toString());
                        List<String> chars = List.of(cellValue.split(""));
                        if(chars.contains(".") && chars.size() > 1){
                            cellValue = setBend(cellValue);
                        }
                        if(rowCount == 1){
                            customerDetails.add(cellValue);
                            colCount++;
                        }
                        else {
                            if(cellValue.equals(".") || cellValue.equals("Null")){
                                columns.add("No_"+customerDetails.get(cellNumber-1));
                                if(isColsValues.isEmpty()){
                                    List<String> storeUniqueColsItems = new ArrayList<>();
                                    storeUniqueColsItems.add("No_"+customerDetails.get(cellNumber-1));
                                    isColsValues.put(cellNumber,storeUniqueColsItems);
                                }
                                else{
                                    List<String> storeUniqueColsItems = isColsValues.get(cellNumber);
                                    if(storeUniqueColsItems == null){
                                        storeUniqueColsItems = new ArrayList<>();
                                        storeUniqueColsItems.add("No_"+customerDetails.get(cellNumber-1));
                                        isColsValues.put(cellNumber,storeUniqueColsItems);
                                    }
                                    else {
                                        if(!storeUniqueColsItems.contains("No_"+customerDetails.get(cellNumber-1))){
                                            storeUniqueColsItems.add("No_"+customerDetails.get(cellNumber-1));
                                            isColsValues.put(cellNumber,storeUniqueColsItems);
                                        }
                                    }

                                }
                            }
                            else {
                                columns.add(cellValue);
                                if(isColsValues.isEmpty()){
                                    List<String> storeUniqueColsItems = new ArrayList<>();
                                    storeUniqueColsItems.add(cellValue);
                                    isColsValues.put(cellNumber,storeUniqueColsItems);
                                }
                                else{
                                    List<String> storeUniqueColsItems = isColsValues.get(cellNumber);
                                    if(storeUniqueColsItems == null){
                                        storeUniqueColsItems = new ArrayList<>();
                                        storeUniqueColsItems.add(cellValue);
                                        isColsValues.put(cellNumber,storeUniqueColsItems);
                                    }
                                    else {
                                        if(!storeUniqueColsItems.contains(cellValue)){
                                            if(cellValue.equals(filterOne) || cellValue.equals(filterTwo)){
                                                storeUniqueColsItems.add(0,cellValue);
                                            }
                                            else
                                                storeUniqueColsItems.add(cellValue);
                                            isColsValues.put(cellNumber,storeUniqueColsItems);
                                        }
                                    }

                                }
                            }
                        }
                        cellNumber++;
                    }
                    if (rowCount > 1) {
                        customerInfo.put(rowCount - 1, columns);
                        totalCustomerCount = totalCustomerCount + Integer.parseInt(columns.get(columns.size()-1));
                        for (int index = 0; index<columns.size(); index++){
                            if (index+1 != columns.size()){
                                Dictionary<String, int[]> tempCustomerCount = colItemsCustomerCount.get(index+1);
                                if(tempCustomerCount == null){
                                    tempCustomerCount = new Hashtable<>();
                                    int[] tmp = { isColsValues.get(index+1).indexOf(columns.get(index)), Integer.parseInt(columns.get(columns.size()-1))};
                                    tempCustomerCount.put(columns.get(index), tmp);
                                    colItemsCustomerCount.put(index+1, tempCustomerCount);
                                }
                                else if(tempCustomerCount.get(columns.get(index))==null){
                                    int[] tmp = { isColsValues.get(index+1).indexOf(columns.get(index)), Integer.parseInt(columns.get(columns.size()-1))};
                                    tempCustomerCount.put(columns.get(index), tmp);
                                    colItemsCustomerCount.put(index+1, tempCustomerCount);
                                }
                                else if(tempCustomerCount.get(columns.get(index))!=null){
                                    int[] tempCount = tempCustomerCount.get(columns.get(index));
                                    tempCount[1] = tempCount[1] + Integer.parseInt(columns.get(columns.size()-1));
                                    colItemsCustomerCount.put(index+1, tempCustomerCount);
                                }
                            }
                        }
                    }
                    else
                        customerInfo.put(-1,customerDetails);
                    rowCount++;
                }
            }
        workbook.close();
        }
        catch (Exception ex){
            throwErr = true;
            setErr_Message(ex + "\n\t"+"Excel document at: "+ getExcel_loc()+ "\n\t" +"Sheet name: " + sheetName + "\n \t Row Number: " + rowCount + "\n \t Column Number: " + cellNumber + "\n \n Error message -> \n" + ex + "\n \n Error has occured, please check the given locations...");
        }
    }
}
