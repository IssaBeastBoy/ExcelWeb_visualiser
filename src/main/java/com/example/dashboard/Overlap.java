package com.example.dashboard;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xwpf.usermodel.*;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Overlap {

    private Path Path;
    private String docName;

    public String getName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public Path getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path loc = Paths.get(path);
        this.Path=loc;
    }

    public void createExcel(){
        LocalDateTime timeStamp = LocalDateTime.now();
        LocalDate date = LocalDate.now();
        Ingest pillars = new Ingest();
        pillars.setExcel_loc("C:\\Users\\f5462797\\Applications\\Grad_project\\Overlap_finder\\files\\Offer_Combinations.xlsx");
        pillars.parseSheets();
        Processor offerDetails = new Processor();
        List<int[]> position = offerDetails.offerPositions(pillars.getCombination());
        List<int[]> sort = offerDetails.sortOffers(position);
        List<String> offerNames = offerDetails.offerCombos(pillars.getCombination(), sort, pillars);
        List<Integer> customerCount = offerDetails.customerCount(pillars.getCombination(), sort);
        setPath("C:\\Users\\f5462797\\Applications\\Grad_project\\Overlap_finder\\overlaps\\");

        if(Files.notExists(getPath())){
            throw new RuntimeException("Path for Word document does not exist. Please check the given path.");
        }
        List<String> sheetNames = pillars.getSheetName();
        setDocName("Overlaps_" + date+".xls");
        String fileName = getPath().toString()+"\\"+getName();
        try (HSSFWorkbook  excel = new HSSFWorkbook()){
            OutputStream storeLoc = new FileOutputStream(fileName);

            for (int start = 0; start<sheetNames.size(); start++){
                HSSFSheet sheet = excel.createSheet(sheetNames.get(start));
                HSSFRow rowhead = sheet.createRow((short)1);
                HSSFRow row = sheet.createRow((short)0);
                row.createCell(0).setCellValue("Time Created - " + timeStamp);

                rowhead.createCell(0).setCellValue("Products");
                rowhead.createCell(1).setCellValue("Overlapfinder.Overlap Count");
                rowhead.createCell(2).setCellValue("Amount of Customers");

                List<int[]> offerSize = offerDetails.getOfferCount();
                for (int index = 0; index< offerNames.size(); index++){
                    row = sheet.createRow((short)(index+2));
                    row.createCell(0).setCellValue(offerNames.get(index));
                    int[] temp = offerSize.get(index);
                    row.createCell(1).setCellValue(String.valueOf(temp[1]));
                    row.createCell(2).setCellValue(customerCount.get(index).toString());
                }


            }

            // save it to .docx file
            try {
                FileOutputStream out = new FileOutputStream(fileName);
                excel.write(out);
                out.close();
                excel.close();
            }
            catch (Exception ex){
                System.out.println(ex.toString());
            }
        }
        catch (Exception ex){

        }
    }

    public void createDoc(){
        LocalDateTime timeStamp = LocalDateTime.now();
        LocalDate date = LocalDate.now();
        Ingest pillars = new Ingest();
        pillars.setExcel_loc("C:\\Users\\f5462797\\Applications\\Grad_project\\Overlap_finder\\files\\Offer_Combinations.xlsx");
        pillars.parseSheets();
        Processor offerDetails = new Processor();
        List<int[]> position = offerDetails.offerPositions(pillars.getCombination());
        List<int[]> sort = offerDetails.sortOffers(position);
        List<String> offerNames = offerDetails.offerCombos(pillars.getCombination(), sort, pillars);
        List<Integer> customerCount = offerDetails.customerCount(pillars.getCombination(), sort);
        setPath("C:\\Users\\f5462797\\Applications\\Grad_project\\Overlap_finder\\overlaps\\");

        if(Files.notExists(getPath())){
            throw new RuntimeException("Path for Word document does not exist. Please check the given path.");
        }

        List<String> sheetNames = pillars.getSheetName();
        setDocName("Overlaps_" + date+".docx");
        String fileName = getPath().toString()+"\\"+getName();
        try (XWPFDocument doc = new XWPFDocument()){

            for (int start = 0; start<sheetNames.size(); start++){
                XWPFParagraph p1 = doc.createParagraph();
                p1.setAlignment(ParagraphAlignment.CENTER);
                // set font
                XWPFRun r1 = p1.createRun();
                r1.setBold(true);
                r1.setItalic(true);
                r1.setFontSize(15);
                r1.setFontFamily("Arial");
                r1.setText(sheetNames.get(start));
                r1.addBreak();

                XWPFParagraph p2 = doc.createParagraph();
                p2.setAlignment(ParagraphAlignment.LEFT);
                r1 = p2.createRun();
                r1.setBold(true);
                r1.setItalic(true);
                r1.setFontSize(12);
                r1.setFontFamily("Arial");
                r1.setText("Time Created - " + timeStamp);
                r1.addBreak();

                XWPFParagraph p3 = doc.createParagraph();
                p3.setAlignment(ParagraphAlignment.LEFT);
                r1 = p3.createRun();
                r1.setFontSize(12);
                r1.setFontFamily("Arial");
                r1.setText("These are the overlapping products in descending order:");
                r1.addBreak();

                XWPFTable tab = doc.createTable();
                tab.setWidth("100%");
                XWPFTableRow row = tab.getRow(0);
                row.getCell(0).setText("Product Name(s)");
                row.addNewTableCell().setText("Products");
                row.addNewTableCell().setText("Amount of Customers");
                int count = 1;
                List<int[]> offerSize = offerDetails.getOfferCount();
                for (int index = 0; index< offerNames.size(); index++){
                    row = tab.createRow(); // Second Row
                    row.getCell(0).setText(count +". "+ offerNames.get(index));
                    int[] temp = offerSize.get(index);
                    row.getCell(1).setText(String.valueOf(temp[1]));
                    row.getCell(2).setText(customerCount.get(index).toString());
                    count++;
                }

                XWPFParagraph p4 = doc.createParagraph();
                p4.setAlignment(ParagraphAlignment.LEFT);
                r1 = p4.createRun();
                r1.addBreak();
                r1.setBold(true);
                r1.setItalic(true);
                r1.setFontSize(11);
                r1.setFontFamily("Arial");
                r1.setText("Total Customers - " + offerDetails.getTotalCustomer());
                r1.addBreak();

            }

            // save it to .docx file
            try {
                FileOutputStream out = new FileOutputStream(fileName);
                doc.write(out);
            }
            catch (Exception ex){
                System.out.println(ex.toString());
            }
        }
        catch (Exception ex){

        }

    }

//    public static void main(String[] args) {
//        Overlap write = new Overlap();
//        write.createExcel();
//    }

}
