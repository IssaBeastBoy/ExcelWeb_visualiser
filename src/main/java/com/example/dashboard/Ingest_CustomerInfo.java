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
    private  boolean throwErr = false;
    private String err_Message = "";
    private Dictionary<String, String> combinations = new Hashtable<>();
    private Dictionary<String, String> relationshipStatus = new Hashtable<>();
    private Map<Integer, List<String>> customerInfo;
    private Dictionary<Integer, Integer> corruptedRows;
    private static Row.MissingCellPolicy xRow;
    public Ingest_CustomerInfo(){
        combinations.put("IN","Insure");
        combinations.put("SL","SecuredLend");
        combinations.put("UL","UnsecuredLend");
        combinations.put("CO","Connect");
        combinations.put("FU","Fusion");
        combinations.put("IV","Invest");
        combinations.put("TR","Transact");
        relationshipStatus.put("S","Single");
        relationshipStatus.put("W", "Widow");
        relationshipStatus.put("M", "Married");
        relationshipStatus.put("D","Divorced");
        relationshipStatus.put("U","Unknown");
        customerInfo = new HashMap<>();
        corruptedRows = new Hashtable<>();
    }

    public boolean isThrowErr() {
        return throwErr;
    }

    private void setThrowErr(boolean throwErr) {
        this.throwErr = throwErr;
    }

    public String getExcel_loc() {

        return excel_loc;
    }

    public Map<Integer, List<String>> getCustomerInfo() {
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

    private String setBend(String input){
        String[] bend = input.split("\\.");
        return bend[1];
    }

    private String setOffers(String offerCodes){
        String[] chars  = offerCodes.split("");
        String offerNames = "";
        if(chars.length%2==0){
            int charCount = 0;
            String offer = "";
            if(chars.length>=4) {
                for (int index = 0; index < chars.length; index++) {
                    if (charCount == 2) {
                        offerNames = combinations.get(offer) + ", " + offerNames;
                        charCount = 0;
                        offer = "";
                    }
                    offer += chars[index];
                    charCount++;
                }
                offerNames = offerNames + " " +combinations.get(offer) ;
            }
            else
                offerNames = combinations.get(offerCodes);
        }
        else
            throw new RuntimeException("Offer code does not exist");
        return offerNames;
    }


    public void parseSheet(){
        int rowCount = 1;
        String sheetName = "";
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
                    cellNumber=1;
                    if(rowCount > 1){
                        while (parseCell.hasNext()){
                            Cell cell = parseCell.next();
                            String cellValue =cell.getStringCellValue(); //dataFormatter.formatCellValue(cell.toString());
                            if(cellNumber == 1){
                                if (cellValue.equals("-1")){
                                    customerDetails.add("No_offers");
                                    customerDetails.add("No_combinations");
                                }
                                else{
                                    customerDetails.add(cellValue);
                                    customerDetails.add(setOffers(cellValue));
                                }
                            }
                            else if(cellNumber == 2){
                                if (cellValue.equals("-1")){
                                    customerDetails.add("No_KPI_Risk");
                                }
                                else
                                    customerDetails.add(cellValue);
                            }
                            else if(cellNumber == 3){
                                if (cellValue.equals("-1")){
                                    customerDetails.add("No_segment");
                                }
                                else
                                    customerDetails.add(cellValue);
                            }
                            else if(cellNumber == 4){
                                if (cellValue.equals("-1")){
                                    customerDetails.add("No_ageBend");
                                }
                                else
                                    customerDetails.add(setBend(cellValue));
                            }
                            else if(cellNumber == 5){
                                if (cellValue.equals("-1")){
                                    customerDetails.add("No_incomeBend");
                                }
                                else
                                    customerDetails.add(setBend(cellValue));
                            }
                            else if(cellNumber == 6){
                                if (cellValue.equals("-1")){
                                    customerDetails.add("Has_account(s)");
                                }
                                else
                                    customerDetails.add(cellValue);
                            }
                            else if(cellNumber == 7){
                                if (cellValue.equals("-1")){
                                    customerDetails.add("No_relationshipStatus");
                                }
                                else
                                    customerDetails.add(relationshipStatus.get(cellValue));
                            }
                            else if(cellNumber == 8){
                                if (cellValue.equals("-1")){
                                    customerDetails.add("No_educationInfo");
                                }
                                else
                                    customerDetails.add(cellValue);
                            }
                            else if(cellNumber == 9){
                                if (cellValue.equals("-1")){
                                    customerDetails.add("No_funeralPolicy");
                                }
                                else
                                    customerDetails.add(cellValue);
                            }
                            else if(cellNumber == 10){
                                if (cellValue.equals("-1")){
                                    throw new RuntimeException("No customer count");
                                }
                                else
                                    customerDetails.add(cellValue);
                            }
                            cellNumber++;
                        }
                        customerInfo.put(rowCount-1,customerDetails);
//                        else{
//                            corruptedRows.add(rowCount);
//                            rowCount--;
//                        }

                    }
                    rowCount++;
                }
            }
        }
        catch (Exception ex){
            setErr_Message(ex + "\n\t"+"Excel document at: "+ getExcel_loc()+ "\n\t" +"Sheet name: " + sheetName + "\n \t Row Number: " + rowCount + "\n \t Column Number: " + cellNumber + "\n \n Error message -> \n" + ex + "\n \n Error has occured, please check the given locations...");
        }
    }

//    public static void main(String[] args) {
//        Ingest_CustomerInfo customerInfo = new Ingest_CustomerInfo();
//       customerInfo.setExcel_loc("C:\\Users\\f5462797\\Applications\\Grad_project\\Customer_Info.xlsx");
//        customerInfo.parseSheet();
//        isUnique testing = new isUnique("Affluent", customerInfo.getCustomerInfo());
//        testing.getUniqueCombination();
//    }
}
