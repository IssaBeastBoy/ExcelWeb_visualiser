package com.example.dashboard;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.monitorjbl.xlsx.StreamingReader;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Ingest_CustomerInfo {
    private String excel_loc;
    private boolean throwErr = false;
    private String err_Message = "";
    private Dictionary<String, String> combinations = new Hashtable<>();
    private Dictionary<String, String> relationshipStatus = new Hashtable<>();
    private Map<Integer, List<String>> customerInfo;
    private Map<Integer, Dictionary<String, String>> isColsValues;
    private Dictionary<Integer, Integer> corruptedRows;
    private static Row.MissingCellPolicy xRow;

    public Ingest_CustomerInfo() {
        customerInfo = new HashMap<>();
        isColsValues = new HashMap<>();
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
        customerInfo = new HashMap<>();
        corruptedRows = new Hashtable<>();
    }

    public boolean isThrowErr() {
        return throwErr;
    }

    private void setThrowErr(boolean throwErr) {
        this.throwErr = throwErr;
    }

    public Map<Integer, Dictionary<String, String>> getIsColsValues() {
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

    public void parseSheet(){
        int rowCount = 1;
        String sheetName = "";
        int colCount = 0;
        int cellNumber = 0;
        try {
            FileInputStream input = new FileInputStream(new File(getExcel_loc()));
            Workbook workbook =  StreamingReader.builder()
                    .rowCacheSize(2000)
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
                    List<String> customerDetails = new ArrayList<>();
                    if (rowCount == 466709){
                        int num = 10;
                    }
                    cellNumber=1;
                    while (parseCell.hasNext()){
                        Cell cell = parseCell.next();
                        String cellValue =cell.getStringCellValue(); //dataFormatter.formatCellValue(cell.toString());
                        List<String> columns = customerInfo.get(-1);
                        List<String> chars = List.of(cellValue.split(""));
                        if(chars.contains(".")){
                            cellValue = setBend(cellValue);
                        }
                        if(rowCount == 1){
                            customerDetails.add(cellValue);
                            colCount++;
                        }
                        else{
                            if(cellValue.equals("-1") || cellValue.equals("Null")){
                                customerDetails.add("No_"+columns.get(cellNumber-1));
                                if(isColsValues.isEmpty()){
                                    Dictionary<String, String> storeUniqueColsItems = new Hashtable<>();
                                    storeUniqueColsItems.put("No_"+columns.get(cellNumber-1), columns.get(cellNumber-1));
                                    isColsValues.put(cellNumber,storeUniqueColsItems);
                                }
                                else{
                                    Dictionary<String, String> storeUniqueColsItems = isColsValues.get(cellNumber);
                                    if(storeUniqueColsItems == null){
                                        storeUniqueColsItems = new Hashtable<>();
                                        storeUniqueColsItems.put("No_"+columns.get(cellNumber-1), columns.get(cellNumber-1));
                                        isColsValues.put(cellNumber,storeUniqueColsItems);
                                    }
                                    else {
                                       if(storeUniqueColsItems.get("No_"+columns.get(cellNumber-1)) == null){
                                            storeUniqueColsItems.put("No_"+columns.get(cellNumber-1), columns.get(cellNumber-1));
                                            isColsValues.put(cellNumber,storeUniqueColsItems);
                                        }
                                    }

                                }
                            }
                            else {
                                customerDetails.add(cellValue);
                                if(isColsValues.isEmpty()){
                                    Dictionary<String, String> storeUniqueColsItems = new Hashtable<>();
                                    storeUniqueColsItems.put(cellValue, columns.get(cellNumber-1));
                                    isColsValues.put(cellNumber,storeUniqueColsItems);
                                }
                                else{
                                    Dictionary<String, String> storeUniqueColsItems = isColsValues.get(cellNumber);
                                    if(storeUniqueColsItems == null){
                                        storeUniqueColsItems = new Hashtable<>();
                                        storeUniqueColsItems.put(cellValue, columns.get(cellNumber-1));
                                        isColsValues.put(cellNumber,storeUniqueColsItems);
                                    }
                                    else {
                                        if(storeUniqueColsItems.get(cellValue)==null){
                                            storeUniqueColsItems.put(cellValue, columns.get(cellNumber-1));
                                            isColsValues.put(cellNumber,storeUniqueColsItems);
                                        }
                                    }

                                }
                            }
                        }
                        cellNumber++;
                    }
                    if (rowCount == 1)
                        customerInfo.put(-1,customerDetails);
                    else
                        customerInfo.put(rowCount-1,customerDetails);
                    rowCount++;
                }
            }
        }
        catch (Exception ex){
            setErr_Message(ex + "\n\t"+"Excel document at: "+ getExcel_loc()+ "\n\t" +"Sheet name: " + sheetName + "\n \t Row Number: " + rowCount + "\n \t Column Number: " + cellNumber + "\n \n Error message -> \n" + ex + "\n \n Error has occured, please check the given locations...");
        }
    }

    // public static void main(String[] args) {
    // Ingest_CustomerInfo customerInfo = new Ingest_CustomerInfo();
    // customerInfo.setExcel_loc("");
    // customerInfo.parseSheet();
    // isUnique testing = new isUnique("Affluent", customerInfo.getCustomerInfo());
    // testing.getUniqueCombination();
    // }C:\\Users\\f5462797\\Applications\\Grad_project\\Customer_Info.xlsx
}
