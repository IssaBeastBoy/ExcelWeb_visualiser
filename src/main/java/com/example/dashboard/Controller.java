package com.example.dashboard;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

public class Controller {
    @FXML
    private TableView<combination_Table> tableChart;
    @FXML
    private TableColumn firstCol = new TableColumn("Pillars");
    @FXML
    private TableColumn secCol = new TableColumn("Products");
    @FXML
    private TableColumn thirdCol = new TableColumn("Amount of Customers");
    @FXML
    private PieChart pieChart;
    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private CategoryAxis barChartX = new CategoryAxis();
    @FXML
    private NumberAxis barChartY = new NumberAxis();
    @FXML
    private AnchorPane overlapCharts;
    @FXML
    private AnchorPane comparsionCharts;
    @FXML
    private TextField comparsionFileLoc;
    @FXML
    private RadioButton Insure;
    @FXML
    private RadioButton Connect;
    @FXML
    private RadioButton Invest;
    @FXML
    private RadioButton Fusion;
    @FXML
    private RadioButton Transact;
    @FXML
    private RadioButton Secured_Lend;
    @FXML
    private RadioButton Unsecured_Lend;
    @FXML
    private Button submitCompareFile;
    private Integer totalCustomers;
    @FXML
    private GridPane radioButtons;
    @FXML
    private RadioButton tableSelect;
    @FXML
    private RadioButton pieChartSelect;
    @FXML
    private RadioButton barGraphSelect;

    @FXML
    private AnchorPane cust_Views;
    @FXML
    private CheckBox off_id;
    @FXML
    private CheckBox _kpi_risks;
    @FXML
    private CheckBox prim_acc;
    @FXML
    private CheckBox relate;
    @FXML
    private CheckBox Educ;
    @FXML
    private CheckBox fun_pol;
    @FXML
    private CheckBox cust;
    @FXML
    private CheckBox inc_bnd;
    @FXML
    private CheckBox age_bnd;
    @FXML
    private CheckBox seg_mnt;

    @FXML
    private BarChart Is_Unique_Bar_One;
    @FXML
    private PieChart Is_Unique_Pie_One;
    @FXML
    private BarChart Is_Unique_Bar_Two;
    @FXML
    private PieChart Is_Unique_Pie_Two;

    @FXML
    private GridPane is_unique_grid_one;
    @FXML
    private GridPane is_unique_grid_twi;
    @FXML
    private RadioButton Is_Unique_Bar_One_R;
    @FXML
    private RadioButton Is_Unique_Pie_One_R;
    @FXML
    private RadioButton Is_Unique_Bar_Two_R;
    @FXML
    private RadioButton Is_Unique_Pie_Two_R;

    private Map<Integer, List<String>> customerInfo;
    private final ObservableList<Data> pieChartIsUnqueViewOne = FXCollections.observableArrayList();
    private final XYChart.Series<String, Integer> barGraphIsUnqueViewOne = new XYChart.Series();
    private final ObservableList<Data> pieChartIsUnqueViewTwo = FXCollections.observableArrayList();
    private final XYChart.Series<String, Integer> barGraphIsUnqueViewTwo = new XYChart.Series();

    private final ObservableList<combination_Table> tableValues = FXCollections.observableArrayList();
    private final ObservableList<Data> pieChartValues = FXCollections.observableArrayList();
    private final XYChart.Series<String, Integer> barGraphValues = new XYChart.Series();
    @FXML
    private String excel_loc = "";
    private List<String> combinationList;
    private List<Integer> customerNumberList;
    private List<int[]> offerSize;

    @FXML
    private TableView<IngestCustomers> Customer_details;
    private final ObservableList<IngestCustomers> customerValues = FXCollections.observableArrayList();
    @FXML
    private TableColumn Offer_ID = new TableColumn("Offer_ID");
    @FXML
    private TableColumn Offer_name = new TableColumn("Offer_name");
    @FXML
    private TableColumn KPI_risk = new TableColumn("KPI_risk");
    @FXML
    private TableColumn Segment = new TableColumn("Segment");
    @FXML
    private TableColumn Age_bend = new TableColumn("Age_bend");
    @FXML
    private TableColumn Income_bend = new TableColumn("Income_bend");
    @FXML
    private TableColumn Primary_acc = new TableColumn("Primary_acc");
    @FXML
    private TableColumn Relationship = new TableColumn("Relationship");
    @FXML
    private TableColumn Education = new TableColumn("Education");
    @FXML
    private TableColumn Funeral_pol = new TableColumn("Funeral_pol");
    @FXML
    private TableColumn Customers = new TableColumn("Customers");

    private String CustomerExcel = "";

    public String getCustomerExcel() {
        return CustomerExcel;
    }

    public void setCustomerExcel(String customerExcel) {
        CustomerExcel = customerExcel;
    }

    private String[] segmentChoices = {"Entry Wallet", "Entry Banking", "Middle", "Middle Market Premier", "Mass Affluent", "Affluent", "Wealthy"};

    @FXML
    private TextField Ingest;

    public void setExcel_loc(String excel_loc) {
        this.excel_loc = excel_loc;
    }

    public String getExcel_loc() {
        return this.excel_loc;
    }

    public void errBox(String message) {
        Alert errMessage = new Alert(Alert.AlertType.ERROR);
        errMessage.setContentText(message);
        errMessage.show();
    }

    @FXML
    private void drawCharts(ActionEvent event) {
        if (!this.excel_loc.isEmpty() && !Files.notExists(Path.of(this.excel_loc), new LinkOption[0])) {
            this.drawTable();
            this.drawPieChart();
            this.drawBarGraph();
            //this.comparsionCharts.setVisible(true);
        } else {
            String errMessage = "Path does not exist. Please check the given path or the excel spreadsheet.";
            this.errBox(errMessage);
        }

    }

    @FXML
    private void processExcel (ActionEvent event){
        Button display = (Button) event.getSource();
        if (!getCustomerExcel().isEmpty() && !Files.notExists(Path.of(getCustomerExcel()), new LinkOption[0])) {
            drawCustomerTable();
            this.overlapCharts.setVisible(true);
            this.Customer_details.setVisible(true);
            this.cust_Views.setVisible(true);
        } else {
            String errMessage = "Path does not exist. Please check the given path or the excel spreadsheet.";
            this.errBox(errMessage);
        }

    }

    @FXML
    private void checkBoxsVisibility(ActionEvent selected){
        CheckBox display = (CheckBox) selected.getSource();
        if (display.getId().equals("off_id")) {



        } else if (display.getId().equals("_kpi_risks")) {



        } else if (display.getId().equals("prim_acc")) {



        } else if (display.getId().equals("relate")) {



        } else if (display.getId().equals("Educ")) {



        } else if (display.getId().equals("fun_pol")) {


        } else if (display.getId().equals("cust")) {



        } else if (display.getId().equals("inc_bnd")) {



        } else if (display.getId().equals("age_bnd")) {



        } else if (display.getId().equals("seg_mnt")) {



        }



    }

    @FXML
    private void isUniqueChartOne(ActionEvent selected){
        RadioButton display = (RadioButton) selected.getSource();
        if (display.getId().equals("Is_Unique_Bar_One_R")){
            Is_Unique_Bar_One.setVisible(true);
            Is_Unique_Pie_One.setVisible(false);
        }
        else if(display.getId().equals("Is_Unique_Pie_One_R")){
            Is_Unique_Bar_One.setVisible(false);
            Is_Unique_Pie_One.setVisible(true);
        }
    }

    @FXML
    private void isUniqueChartTwo(ActionEvent selected){
        RadioButton display = (RadioButton) selected.getSource();
        if (display.getId().equals("Is_Unique_Bar_Two_R")){
            Is_Unique_Bar_Two.setVisible(true);
            Is_Unique_Pie_Two.setVisible(false);
        }
        else if(display.getId().equals("Is_Unique_Pie_Two_R")){
            Is_Unique_Bar_Two.setVisible(false);
            Is_Unique_Pie_Two.setVisible(true);
        }
    }

    //ADJUST PERCENTAGES
    private void drawBarIsUniqueVone(String segmentName){
        Is_Unique_Bar_One.getData().clear();
        isUnique unique = new isUnique(segmentName, customerInfo);
        Dictionary<String, Integer> combinationCount =  unique.getUniqueCombination();
        for(Enumeration combination = combinationCount.keys(); combination.hasMoreElements();)
        {
            String offer = (String) combination.nextElement();
            int percentage = (combinationCount.get(offer) * 100)/ combinationCount.get("Total");
            barGraphIsUnqueViewOne.getData().add(new javafx.scene.chart.XYChart.Data(offer, percentage));
        }
        Is_Unique_Bar_One.getData().add(barGraphIsUnqueViewOne);
    }

    //ADJUST PERCENTAGES
    private void drawBarIsUniqueVtwo(String segmentName){
        Is_Unique_Bar_Two.getData().clear();
        isUnique unique = new isUnique(segmentName, customerInfo);
        Dictionary<String, Integer> combinationCount =  unique.getUniqueCombination();
        for(Enumeration combination = combinationCount.keys(); combination.hasMoreElements();)
        {
            String offer = (String) combination.nextElement();
            int percentage = (combinationCount.get(offer) * 100)/ combinationCount.get("Total");
            barGraphIsUnqueViewTwo.getData().add(new javafx.scene.chart.XYChart.Data(offer, percentage));
        }
        Is_Unique_Bar_Two.getData().add(barGraphIsUnqueViewTwo);
    }
    //ADJUST PERCENTAGES
    private void pieChartIsUniqueVone(String segmentName){
        Is_Unique_Pie_One.getData().clear();
        isUnique unique = new isUnique(segmentName, customerInfo);
        Dictionary<String, Integer> combinationCount =  unique.getUniqueCombination();
        for(Enumeration combination = combinationCount.keys(); combination.hasMoreElements();)
        {
            String offer = (String) combination.nextElement();
            int percentage = (combinationCount.get(offer) * 100)/ combinationCount.get("Total");
            pieChartIsUnqueViewOne.add(new Data(offer, percentage));
        }
        Is_Unique_Pie_One.setData(pieChartIsUnqueViewOne);
    }
    //ADJUST PERCENTAGES
    private void pieChartIsUniqueVtwo(String segmentName){
        Is_Unique_Pie_Two.getData().clear();
        isUnique unique = new isUnique(segmentName, customerInfo);
        Dictionary<String, Integer> combinationCount =  unique.getUniqueCombination();
        for(Enumeration combination = combinationCount.keys(); combination.hasMoreElements();)
        {
            String offer = (String) combination.nextElement();
            int percentage = (combinationCount.get(offer) * 100)/ combinationCount.get("Total");
            pieChartIsUnqueViewTwo.add(new Data(offer, percentage));
        }
        Is_Unique_Pie_Two.setData(pieChartIsUnqueViewTwo);
    }

        @FXML
        private void Ingest (ActionEvent event){
            TextField loca = (TextField) event.getSource();
            this.setCustomerExcel(loca.getText());
        }

        @FXML
        private void Ingest (MouseEvent event){
            TextField loca = (TextField) event.getSource();
            this.setCustomerExcel(loca.getText());
        }

        private void drawTable() {
            Ingest_Overlap inputExcel = new Ingest_Overlap();
            inputExcel.setExcel_loc(this.getExcel_loc());
            inputExcel.parseSheets();
            if (inputExcel.isError()) {
                this.errBox(inputExcel.getErrMessage());
            } else {
                this.tableChart.getItems().clear();
                Processor offerDetails = new Processor();
                List<int[]> position = offerDetails.offerPositions(inputExcel.getCombination());
                List<int[]> sort = offerDetails.sortOffers(position);
                this.combinationList = offerDetails.offerCombos(inputExcel.getCombination(), sort, inputExcel);
                this.customerNumberList = offerDetails.customerCount(inputExcel.getCombination(), sort);
                this.offerSize = offerDetails.getOfferCount();
                int count = 1;
                this.tableChart.setVisible(true);
                this.totalCustomers = 0;

                for (int index = 0; index < this.offerSize.size(); ++count) {
                    this.tableValues.add(new combination_Table(count + ". " + (String) this.combinationList.get(index), ((int[]) this.offerSize.get(index))[1], (Integer) this.customerNumberList.get(index)));
                    this.totalCustomers = this.totalCustomers + (Integer) this.customerNumberList.get(index);
                    ++index;
                }

                this.firstCol.setCellValueFactory(new PropertyValueFactory("combination"));
                this.secCol.setCellValueFactory(new PropertyValueFactory("combinationNumber"));
                this.thirdCol.setCellValueFactory(new PropertyValueFactory("customerCount"));
                this.tableChart.setItems(this.tableValues);
                this.overlapCharts.setVisible(true);
                this.radioButtons.setVisible(true);
                this.tableSelect.setSelected(true);
                this.pieChartSelect.setSelected(false);
                this.pieChart.setVisible(false);
                this.barGraphSelect.setSelected(false);
                this.barChart.setVisible(false);
            }

        }
    //ADJUST PERCENTAGES
        private void drawPieChart()  {
            this.pieChart.getData().clear();
            int combinationCount = ((int[]) this.offerSize.get(0))[1];
            int combinationSum = (Integer) this.customerNumberList.get(0);

            for (int index = 1; index < this.offerSize.size(); ++index) {
                int percentage;
                if (combinationCount != ((int[]) this.offerSize.get(index))[1]) {
                    percentage = combinationSum * 100 / this.totalCustomers;
                    this.pieChartValues.add(new Data(String.valueOf(combinationCount) + " Pillars: " + percentage + "%", (double) combinationSum));
                    combinationCount = ((int[]) this.offerSize.get(index))[1];
                    combinationSum = (Integer) this.customerNumberList.get(index);
                } else if (index + 1 == this.offerSize.size()) {
                    percentage = combinationSum * 100 / this.totalCustomers;
                    this.pieChartValues.add(new Data(String.valueOf(combinationCount) + " Pillar: " + percentage + "%", (double) combinationSum));
                } else {
                    combinationSum += (Integer) this.customerNumberList.get(index);
                }
            }

            this.pieChart.setData(this.pieChartValues);
            this.pieChart.setTitle("Pillar combination count");
            this.pieChart.setClockwise(true);
            this.pieChart.setLabelsVisible(true);
            this.pieChart.setStartAngle(180.0D);
        }
    //ADJUST PERCENTAGES
        private void drawBarGraph () {
            this.barChart.getData().clear();
            this.barChartX.setLabel("Pillars");
            this.barChartY.setLabel("Percentage(%)");
            this.barChart.setTitle("Pillars percentage distribution");
            int combinationCount = ((int[]) this.offerSize.get(0))[1];
            int combinationSum = (Integer) this.customerNumberList.get(0);

            for (int index = 1; index < this.offerSize.size(); ++index) {
                int percentage;
                if (combinationCount != ((int[]) this.offerSize.get(index))[1]) {
                    percentage = combinationSum * 100 / this.totalCustomers;
                    this.barGraphValues.getData().add(new javafx.scene.chart.XYChart.Data(String.valueOf(combinationCount) + " Pillars", percentage));
                    combinationCount = ((int[]) this.offerSize.get(index))[1];
                    combinationSum = (Integer) this.customerNumberList.get(index);
                } else if (index + 1 == this.offerSize.size()) {
                    percentage = combinationSum * 100 / this.totalCustomers;
                    this.barGraphValues.getData().add(new javafx.scene.chart.XYChart.Data(String.valueOf(combinationCount) + " Pillar", percentage));
                } else {
                    combinationSum += (Integer) this.customerNumberList.get(index);
                }
            }

            this.barChart.getData().add(this.barGraphValues);
        }

        private void drawCustomerTable(){
            Ingest_CustomerInfo processExcel = new Ingest_CustomerInfo();
            processExcel.setExcel_loc(getCustomerExcel());
            processExcel.parseSheet();
            if(processExcel.isThrowErr()){
                errBox(processExcel.getErr_Message());
            }
            else {
                customerInfo = processExcel.getCustomerInfo();
                Customer_details.getItems().clear();
                for (int index = 0; index< customerInfo.size(); index++){
                    int count = index + 1;
                    List<String> details = customerInfo.get(count);
                    customerValues.add(new IngestCustomers(details.get(0),details.get(1),details.get(2),details.get(3),details.get(4),details.get(5),details.get(6),details.get(7),details.get(8),details.get(9),details.get(10)));
                }
                if(processExcel.isThrowErr())
                    errBox(processExcel.getErr_Message());
                Offer_ID.setCellValueFactory(new PropertyValueFactory("Offer_ID"));
                Offer_name.setCellValueFactory(new PropertyValueFactory("Offer_Name"));
                KPI_risk.setCellValueFactory(new PropertyValueFactory("KPI_risk"));
                Segment.setCellValueFactory(new PropertyValueFactory("Segment"));
                Age_bend.setCellValueFactory(new PropertyValueFactory("Age_bend"));
                Income_bend.setCellValueFactory(new PropertyValueFactory("Income_bend"));
                Primary_acc.setCellValueFactory(new PropertyValueFactory("Primary_acc"));
                Relationship.setCellValueFactory(new PropertyValueFactory("Relationship"));
                Education.setCellValueFactory(new PropertyValueFactory("Eduction"));
                Funeral_pol.setCellValueFactory(new PropertyValueFactory("Funeral_pol"));
                Customers.setCellValueFactory(new PropertyValueFactory("Customers"));
                Customer_details.setItems(customerValues);
            }
        }

        @FXML
        private void chartsChangeView (ActionEvent selected){
            RadioButton display = (RadioButton) selected.getSource();
            if (display.getId().equals("tableSelect")) {
                this.tableChart.setVisible(true);
                this.pieChart.setVisible(false);
                this.barChart.setVisible(false);
                this.tableSelect.setSelected(true);
                this.pieChartSelect.setSelected(false);
                this.barGraphSelect.setSelected(false);
            } else if (display.getId().equals("pieChart")) {
                this.tableChart.setVisible(false);
                this.pieChart.setVisible(true);
                this.barChart.setVisible(false);
                this.tableSelect.setSelected(false);
                this.pieChartSelect.setSelected(true);
                this.barGraphSelect.setSelected(false);
            } else if (display.getId().equals("barGraph")) {
                this.tableChart.setVisible(false);
                this.pieChart.setVisible(false);
                this.barChart.setVisible(true);
                this.tableSelect.setSelected(false);
                this.pieChartSelect.setSelected(false);
                this.barGraphSelect.setSelected(true);
            }

        }

        @FXML
        private void subsegmentSelect (ActionEvent selected){
            RadioButton selectedSubsegment = (RadioButton) selected.getSource();
            if (selectedSubsegment.getId().equals("Insure")) {
                this.Insure.setSelected(true);
                this.Invest.setSelected(false);
                this.Connect.setSelected(false);
                this.Transact.setSelected(false);
                this.Fusion.setSelected(false);
                this.Secured_Lend.setSelected(false);
                this.Unsecured_Lend.setSelected(false);
            } else if (selectedSubsegment.getId().equals("Invest")) {
                this.Insure.setSelected(false);
                this.Invest.setSelected(true);
                this.Connect.setSelected(false);
                this.Transact.setSelected(false);
                this.Fusion.setSelected(false);
                this.Secured_Lend.setSelected(false);
                this.Unsecured_Lend.setSelected(false);
            } else if (selectedSubsegment.getId().equals("Connect")) {
                this.Insure.setSelected(false);
                this.Invest.setSelected(false);
                this.Connect.setSelected(true);
                this.Transact.setSelected(false);
                this.Fusion.setSelected(false);
                this.Secured_Lend.setSelected(false);
                this.Unsecured_Lend.setSelected(false);
            } else if (selectedSubsegment.getId().equals("Transact")) {
                this.Insure.setSelected(false);
                this.Invest.setSelected(false);
                this.Connect.setSelected(false);
                this.Transact.setSelected(true);
                this.Fusion.setSelected(false);
                this.Secured_Lend.setSelected(false);
                this.Unsecured_Lend.setSelected(false);
            } else if (selectedSubsegment.getId().equals("Fusion")) {
                this.Insure.setSelected(false);
                this.Invest.setSelected(false);
                this.Connect.setSelected(false);
                this.Transact.setSelected(false);
                this.Fusion.setSelected(true);
                this.Secured_Lend.setSelected(false);
                this.Unsecured_Lend.setSelected(false);
            } else if (selectedSubsegment.getId().equals("Secured_Lend")) {
                this.Insure.setSelected(false);
                this.Invest.setSelected(false);
                this.Connect.setSelected(false);
                this.Transact.setSelected(false);
                this.Fusion.setSelected(false);
                this.Secured_Lend.setSelected(true);
                this.Unsecured_Lend.setSelected(false);
            } else if (selectedSubsegment.getId().equals("Unsecured_Lend")) {
                this.Insure.setSelected(false);
                this.Invest.setSelected(false);
                this.Connect.setSelected(false);
                this.Transact.setSelected(false);
                this.Fusion.setSelected(false);
                this.Secured_Lend.setSelected(false);
                this.Unsecured_Lend.setSelected(true);
            }

        }

        @FXML
        private void plotCompareCharts (ActionEvent chart){
        }

        @FXML
        private void Location (ActionEvent event){
            TextField loca = (TextField) event.getSource();
            this.setExcel_loc(loca.getText());
        }

        @FXML
        private void Location (MouseEvent event){
            TextField loca = (TextField) event.getSource();
            this.setExcel_loc(loca.getText());
        }


}