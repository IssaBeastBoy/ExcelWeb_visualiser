package com.example.dashboard;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.List;

public class Controller {

    @FXML
    private TableView<Table> tableChart;
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
    private NumberAxis barChartY  = new NumberAxis();

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
    private RadioButton tableSelect;
    @FXML
    private RadioButton pieChartSelect;
    @FXML
    private RadioButton barGraphSelect;

    private final ObservableList<Table> tableValues = FXCollections.observableArrayList();
    private final ObservableList<PieChart.Data> pieChartValues = FXCollections.observableArrayList();
    private final  XYChart.Series<String,Integer> barGraphValues = new XYChart.Series<>();

    @FXML
    private String excel_loc = "";

    private List<String> combinationList;
    private List<Integer> customerNumberList;
    private List<int[]> offerSize;

    public void setExcel_loc(String excel_loc) {
        this.excel_loc = excel_loc;
    }

    public String getExcel_loc() {
        return excel_loc;
    }

    public void errBox(String message) {
        Alert errMessage = new Alert(Alert.AlertType.ERROR);
        errMessage.setContentText(message);
        errMessage.show();
    }

    @FXML
    private void drawCharts(ActionEvent event) {
        if (excel_loc.isEmpty() || Files.notExists(Path.of(this.excel_loc))) {
            String errMessage = "Path does not exist. Please check the given path or the excel spreadsheet.";
            errBox(errMessage);
        } else {

            drawTable();
            drawPieChart();
            drawBarGraph();
            comparsionCharts.setVisible(true);
        }
    }

    private void drawTable() {
        Ingest inputExcel = new Ingest();
        inputExcel.setExcel_loc(getExcel_loc());
        inputExcel.parseSheets();
        if (inputExcel.isError())
            errBox(inputExcel.getErrMessage());
        else {
            tableChart.getItems().clear();
            overlapCharts.setVisible(true);
            Processor offerDetails = new Processor();
            List<int[]> position = offerDetails.offerPositions(inputExcel.getCombination());
            List<int[]> sort = offerDetails.sortOffers(position);
            // Combinations combinations = new Combinations();
            combinationList = offerDetails.offerCombos(inputExcel.getCombination(), sort, inputExcel);
            customerNumberList = offerDetails.customerCount(inputExcel.getCombination(), sort);
            offerSize = offerDetails.getOfferCount();
            int count = 1;
            // overlaps.getColumns().clear();
            tableChart.setVisible(true);
            totalCustomers = 0;
            for (int index = 0; index < offerSize.size(); index++, count++) {
                // StringProperty combos = new SimpleStringProperty(combinationList.get(index));
                // IntegerProperty cusCount = new
                // SimpleIntegerProperty(customerNumberList.get(index));
                // IntegerProperty comBin = new SimpleIntegerProperty(offerSize.get(index)[1]);
                tableValues.add(new Table(count + ". " + combinationList.get(index), offerSize.get(index)[1],
                        customerNumberList.get(index)));
                totalCustomers += customerNumberList.get(index);
                // combinations.setIndex(index);
                // combinations.appendCombination(combos);
                // combinations.appendcustomerCount(cusCount);
                // combinations.appendcombinationNumber(comBin);
                // Number cusCount = new ();
                // Number comBin = new SimpleIntegerProperty(offerSize.get(index)[1]);
                // firstCol.setCellValueFactory(cellData ->
                // cellData.getValue().getCombination());
                // secCol.setCellValueFactory(cellData ->
                // cellData.getValue().getcustomerCount());
                // thirdCol.setCellValueFactory(cellData ->
                // cellData.getValue().getcombinationNumber());
            }

            firstCol.setCellValueFactory(new PropertyValueFactory<Table, String>("combination"));
            secCol.setCellValueFactory(new PropertyValueFactory<Table, Integer>("combinationNumber"));
            thirdCol.setCellValueFactory(new PropertyValueFactory<Table, Integer>("customerCount"));
            // overlaps.getColumns().addAll(firstCol,secCol,thirdCol);
            // activeSession.add(combinations);
            tableChart.setItems(tableValues);
            tableSelect.setSelected(true);
            pieChartSelect.setSelected(false);
            pieChart.setVisible(false);
            barGraphSelect.setSelected(false);
            barChart.setVisible(false);
        }
    }

    private void drawPieChart() {
        pieChart.getData().clear();
        int combinationCount = offerSize.get(0)[1];
        int combinationSum = customerNumberList.get(0);
        for (int index = 1; index < offerSize.size(); index++) {
            if (combinationCount != offerSize.get(index)[1]) {
                int percentage = combinationSum*100/totalCustomers;
                pieChartValues.add(new PieChart.Data((String.valueOf(combinationCount)+" Pillars: " + percentage +"%"), combinationSum));
                combinationCount = offerSize.get(index)[1];
                combinationSum = customerNumberList.get(index);
            }
            else if (index+1== offerSize.size()){
                int percentage = combinationSum*100/totalCustomers;
                pieChartValues.add(new PieChart.Data((String.valueOf(combinationCount)+" Pillar: " + percentage +"%"), combinationSum));
            }
            else {
                combinationSum += customerNumberList.get(index);
            }

        }
        pieChart.setData(pieChartValues);
        pieChart.setTitle("Pillar combination count");
        pieChart.setClockwise(true);
        pieChart.setLabelsVisible(true);
        pieChart.setStartAngle(180);
    }

    private void drawBarGraph(){
        barChart.getData().clear();
        barChartX.setLabel("Pillars");
        barChartY.setLabel("Percentage(%)");
        barChart.setTitle("Pillars percentage distribution");
        int combinationCount = offerSize.get(0)[1];
        int combinationSum = customerNumberList.get(0);
        for (int index = 1; index < offerSize.size(); index++) {
            if (combinationCount != offerSize.get(index)[1]) {
                int percentage = combinationSum * 100 / totalCustomers;
                barGraphValues.getData().add(new XYChart.Data((String.valueOf(combinationCount) + " Pillars"),percentage));
                combinationCount = offerSize.get(index)[1];
                combinationSum = customerNumberList.get(index);
            } else if (index + 1 == offerSize.size()) {
                int percentage = combinationSum * 100 / totalCustomers;
                barGraphValues.getData().add(new XYChart.Data((String.valueOf(combinationCount) + " Pillar"),percentage));
            } else {
                combinationSum += customerNumberList.get(index);
            }
        }
        barChart.getData().add(barGraphValues);
    }

    @FXML
    private void chartsChangeView(ActionEvent selected) {
        RadioButton display = (RadioButton) selected.getSource();
        if (display.getId().equals("tableSelect")) {
            tableChart.setVisible(true);
            pieChart.setVisible(false);
            barChart.setVisible(false);
            tableSelect.setSelected(true);
            pieChartSelect.setSelected(false);
            barGraphSelect.setSelected(false);
        } else if (display.getId().equals("pieChart")) {
            tableChart.setVisible(false);
            pieChart.setVisible(true);
            barChart.setVisible(false);
            tableSelect.setSelected(false);
            pieChartSelect.setSelected(true);
            barGraphSelect.setSelected(false);
        } else if (display.getId().equals("barGraph")) {
            tableChart.setVisible(false);
            pieChart.setVisible(false);
            barChart.setVisible(true);
            tableSelect.setSelected(false);
            pieChartSelect.setSelected(false);
            barGraphSelect.setSelected(true);
        }
    }

    @FXML
    private void subsegmentSelect(ActionEvent selected){
        RadioButton selectedSubsegment = (RadioButton) selected.getSource();
        if (selectedSubsegment.getId().equals("Insure")){
            Insure.setSelected(true);
            Invest.setSelected(false);
            Connect.setSelected(false);
            Transact.setSelected(false);
            Fusion.setSelected(false);
            Secured_Lend.setSelected(false);
            Unsecured_Lend.setSelected(false);
        }
        else if (selectedSubsegment.getId().equals("Invest")){
            Insure.setSelected(false);
            Invest.setSelected(true);
            Connect.setSelected(false);
            Transact.setSelected(false);
            Fusion.setSelected(false);
            Secured_Lend.setSelected(false);
            Unsecured_Lend.setSelected(false);
        }
        else if (selectedSubsegment.getId().equals("Connect")){
            Insure.setSelected(false);
            Invest.setSelected(false);
            Connect.setSelected(true);
            Transact.setSelected(false);
            Fusion.setSelected(false);
            Secured_Lend.setSelected(false);
            Unsecured_Lend.setSelected(false);
        }
        else if (selectedSubsegment.getId().equals("Transact")){
            Insure.setSelected(false);
            Invest.setSelected(false);
            Connect.setSelected(false);
            Transact.setSelected(true);
            Fusion.setSelected(false);
            Secured_Lend.setSelected(false);
            Unsecured_Lend.setSelected(false);
        }
        else if (selectedSubsegment.getId().equals("Fusion")){
            Insure.setSelected(false);
            Invest.setSelected(false);
            Connect.setSelected(false);
            Transact.setSelected(false);
            Fusion.setSelected(true);
            Secured_Lend.setSelected(false);
            Unsecured_Lend.setSelected(false);
        }
        else if (selectedSubsegment.getId().equals("Secured_Lend")){
            Insure.setSelected(false);
            Invest.setSelected(false);
            Connect.setSelected(false);
            Transact.setSelected(false);
            Fusion.setSelected(false);
            Secured_Lend.setSelected(true);
            Unsecured_Lend.setSelected(false);
        }
        else if (selectedSubsegment.getId().equals("Unsecured_Lend")){
            Insure.setSelected(false);
            Invest.setSelected(false);
            Connect.setSelected(false);
            Transact.setSelected(false);
            Fusion.setSelected(false);
            Secured_Lend.setSelected(false);
            Unsecured_Lend.setSelected(true);
        }
    }

    @FXML
    private void  plotCompareCharts(ActionEvent chart){

    }

    @FXML
    private void Location(ActionEvent event) {
        TextField loca = (TextField) event.getSource();
        setExcel_loc(loca.getText());
    }

    @FXML
    private void Location(MouseEvent event) {
        TextField loca = (TextField) event.getSource();
        setExcel_loc(loca.getText());
    }

}