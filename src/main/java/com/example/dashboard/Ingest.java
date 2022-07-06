package com.example.dashboard;

import javafx.scene.chart.AreaChart;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class Ingest {

    private String Excel_loc;
    private Dictionary<String,String[]> offers;
    private boolean offerCodes;
    private boolean offerNames;
    private String offerCombo;
    private Dictionary<String[], Integer> Combination;
    private boolean cellEmpty;
    private  List<String> sheetName = new ArrayList<>();
    private boolean error = false;
    private String ErrMessage;
    private int rowNumber;
    private int cellNumber;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getErrMessage() {
        return ErrMessage;
    }

    public void setErrMessage(String errMessage) {
        ErrMessage = errMessage;
    }

    public String getExcel_loc() {
        return Excel_loc;
    }

    public void setExcel_loc(String excel_loc) {
        Excel_loc = excel_loc;
    }

    public List<String> getSheetName() {
        return this.sheetName;
    }

    public Dictionary<String, String[]> getOffers() {
        return this.offers;
    }

    public Dictionary<String[], Integer> getCombination() {
        return this.Combination;
    }

    public void transformer(String offerCSV, int Customers){
        String[] chars = offerCSV.split("");
        String[] codes = this.offers.get("Codes");
        if (chars.length<2 || chars.length%2 != 0){
            throw new RuntimeException("In correct offer format");
        }
        else if (chars.length == 2){
            if(Arrays.asList(codes).contains(offerCSV)){
                String[] singleCombo = {offerCSV};
                Combination.put(singleCombo, Customers);
            }
            else
                throw new RuntimeException("Offer code not found");
        }
        else{
            int offer_codeLenght = 0;
            String offerCode = "";
            String[] storeOfferNames = new String[chars.length/2];
            int index = 0;
            for (int count = 0 ; index< storeOfferNames.length; count++){
                if(offer_codeLenght == 2){
                    if(Arrays.asList(codes).contains(offerCode))
                        storeOfferNames[index] = offerCode;
                    else
                        throw new RuntimeException("Offer code not found");
                    if (count==chars.length)
                        break;
                    offerCode = "" + chars[count];
                    offer_codeLenght=1;
                    index++;
                }
                else{
                    offerCode = offerCode + chars[count];
                    offer_codeLenght++;
                }
                this.Combination.put(storeOfferNames,Customers);
            }
        }
    }

    public void parseSheets(){
        int rowCount = 1;
        String sheetName = "";
        int cellNumber = 0;
        try {
            FileInputStream input = new FileInputStream(new File(Excel_loc));
            Workbook workbook = new XSSFWorkbook(input);
            DataFormatter dataFormatter = new DataFormatter();
            Iterator<Sheet> sheets = workbook.sheetIterator();
            while (sheets.hasNext()){
                Sheet sh = sheets.next();
                sheetName = sh.getSheetName();
                this.offers = new Hashtable<>();
                this.sheetName.add(sheetName);
                this.Combination = new Hashtable<>();
                Iterator<Row> parseSheet = sh.iterator();
                rowCount = 1;
                this.offerNames = false;
                this.offerCodes = false;
                while (parseSheet.hasNext()){
                    Row row = parseSheet.next();
                    Iterator<Cell> parseCell = row.iterator();
                    cellEmpty = true;
                    cellNumber = 1;
                    this.rowNumber = rowCount;
                    while (parseCell.hasNext()){
                        this.cellNumber = cellNumber;
                        Cell cell = parseCell.next();
                        String cellValue = dataFormatter.formatCellValue(cell);
                        if (cellValue.contains("TR code") && rowCount == 1 && cellNumber == 1)
                            this.offerCodes = true;
                        else if (cellValue.contains("TR Name") && rowCount == 1 && cellNumber == 2 )
                            this.offerNames = true;
                        else if (this.offerCodes && rowCount == 2 && cellNumber == 1)
                            offers.put("Codes", cellValue.split(","));
                        else if (this.offerNames && rowCount == 2 && cellNumber == 2)
                            offers.put("Names", cellValue.split(","));
                        else if(cellValue != "" && rowCount>=4 && cellNumber == 1) {
                            offerCombo = cellValue;
                            cellEmpty = false;
                        }
                        else if (!cellEmpty && rowCount>=4 && cellNumber == 2){
                            transformer(offerCombo, Integer.parseInt(cellValue));
                        }
                        cellNumber++;
                    }
                    rowCount++;
                }
            }
            setError(false);
        }
        catch (Exception ex){
            setError(true);
            this.ErrMessage = "Sheet name: " + this.sheetName + "\n \t Row Number: " + this.rowNumber + "\n \t Column Number: " + this.cellNumber + "\n \n Error message -> \n" + ex + "\n \n Error has occured, please check the given locations...";
        }
    }


//   public static void main(String[] args) {
//       Overlapfinder.Ingest files = new Overlapfinder.Ingest();
//       files.setExcel_loc("C:\\Users\\f5462797\\Applications\\Grad_project\\Overlap_finder\\files\\Offer_Combinations.xlsx");
//       files.parseSheets();
//   }

}
