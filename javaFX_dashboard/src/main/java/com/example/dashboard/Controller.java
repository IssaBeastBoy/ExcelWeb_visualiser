package com.example.dashboard;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.*;

public class Controller {
    //region Description Login Page attributes
    @FXML
    private TextField fullName;
    @FXML
    private TextField email;
    @FXML
    PasswordField password;
    @FXML
    private Button login;

    @FXML
    private void LoginUser(ActionEvent event){

        System.out.println("Check!!!");
    }

//endregion

/**
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

    // Pivot table design
    @FXML
    private ChoiceBox verticalView;
    @FXML
    private ChoiceBox horizontalView;
    @FXML
    private TableView<PivotTable> PivotTable;
    @FXML
    private TableColumn Pivot_cols1;
    @FXML
    private TableColumn Pivot_cols2;
    @FXML
    private TableColumn Pivot_cols3;
    @FXML
    private TableColumn Pivot_cols4;
    @FXML
    private TableColumn Pivot_cols5;
    @FXML
    private TableColumn Pivot_cols6;
    @FXML
    private TableColumn Pivot_cols7;
    @FXML
    private TableColumn Pivot_cols8;
    @FXML
    private TableColumn Pivot_cols9;
    @FXML
    private TableColumn Pivot_cols10;
    @FXML
    private TableColumn Pivot_cols11;
    @FXML
    private TableColumn Pivot_cols12;
    @FXML
    private TableColumn Pivot_cols13;
    @FXML
    private TableColumn Pivot_cols14;
    @FXML
    private TableColumn Pivot_cols15;
    @FXML
    private TableColumn Pivot_cols16;
    @FXML
    private TableColumn Pivot_cols17;
    @FXML
    private TableColumn Pivot_cols18;
    @FXML
    private TableColumn Pivot_cols19;
    @FXML
    private TableColumn Pivot_cols20;
    @FXML
    private TableColumn Pivot_cols21;

    private final ObservableList<PivotTable> PivotTableValues = FXCollections.observableArrayList();
    private Dictionary<Integer, List<String>> isColsValues;
    private Dictionary<Integer, Dictionary<String, int[]>> colItemsCustomerCount;
    private List<String> columnsValues;
    private int totalCustomerCount;
    private DecimalFormat Decimal = new DecimalFormat("#.##");

    @FXML
    private void setPivotTableVertical(ActionEvent event) {
        if (verticalView.getValue() != null && horizontalView.getValue() != null) {
            String selected = verticalView.getValue().toString();
            int indexVertical = columnsValues.indexOf(selected) + 1;
            int indexHorizontal = columnsValues.indexOf(horizontalView.getValue().toString()) + 1;
            if (selected == horizontalView.getValue().toString()) {
                PivotTable.refresh();
                errBox("Can not use same columns in Pivot Table");
            } else {
                if (isColsValues.get(indexVertical) != null && isColsValues.get(indexHorizontal) != null) {
                    PivotTableSetup setUp = new PivotTableSetup(isColsValues.get(indexVertical),
                            isColsValues.get(indexHorizontal),
                            customerInfo, 1, 4, 6, 8);
                    Dictionary<String, int[]> combinations = setUp.getPivotValues();
                    List<String> verticalSorted = setUp.getSortedVertical();
                    List<String> horizontalSorted = setUp.getSortedHorzitonal();
                    // for (int index = 0; index < this.offerSize.size(); ++count) {
                    // this.tableValues.add(new combination_Table(count + ". " + (String)
                    // this.combinationList.get(index), ((int[]) this.offerSize.get(index))[1],
                    // (Integer) this.customerNumberList.get(index)));
                    // this.totalCustomers = this.totalCustomers + (Integer)
                    // this.customerNumberList.get(index);
                    // ++index;
                    // }
                    String name;
                    double col1 = 0.00;
                    double col2 = 0.00;
                    double col3 = 0.00;
                    double col4 = 0.00;
                    double col5 = 0.00;
                    double col6 = 0.00;
                    double col7 = 0.00;
                    double col8 = 0.00;
                    double col9 = 0.00;
                    double col10 = 0.00;
                    double col11 = 0.00;
                    double col12 = 0.00;
                    double col13 = 0.00;
                    double col14 = 0.00;
                    double col15 = 0.00;
                    double col16 = 0.00;
                    double col17 = 0.00;
                    double col18 = 0.00;
                    double col19 = 0.00;
                    double col20 = 0.00;
                    for (int parseHorzi = 0; parseHorzi < horizontalSorted.size(); parseHorzi++) {
                        for (int parseVert = 0; parseVert < verticalSorted.size(); parseVert++) {
                            if (parseVert == 0) {
                                String currCombo = horizontalSorted.get(parseHorzi) + ","
                                        + verticalSorted.get(parseVert);
                                col1 = Double.parseDouble(Decimal.format(
                                        (combinations.get(currCombo)[1] * 100.00) / setUp.getPivotTotalCount()));
                            } else if (parseVert == 1) {
                                String currCombo = horizontalSorted.get(parseHorzi) + ","
                                        + verticalSorted.get(parseVert);
                                col2 = Double.parseDouble(Decimal.format(
                                        (combinations.get(currCombo)[1] * 100.00) / setUp.getPivotTotalCount()));
                            } else if (parseVert == 2) {
                                String currCombo = horizontalSorted.get(parseHorzi) + ","
                                        + verticalSorted.get(parseVert);
                                col3 = Double.parseDouble(Decimal.format(
                                        (combinations.get(currCombo)[1] * 100.00) / setUp.getPivotTotalCount()));
                            } else if (parseVert == 3) {
                                String currCombo = horizontalSorted.get(parseHorzi) + ","
                                        + verticalSorted.get(parseVert);
                                col4 = Double.parseDouble(Decimal.format(
                                        (combinations.get(currCombo)[1] * 100.00) / setUp.getPivotTotalCount()));
                            } else if (parseVert == 4) {
                                String currCombo = horizontalSorted.get(parseHorzi) + ","
                                        + verticalSorted.get(parseVert);
                                col5 = Double.parseDouble(Decimal.format(
                                        (combinations.get(currCombo)[1] * 100.00) / setUp.getPivotTotalCount()));
                            } else if (parseVert == 5) {
                                String currCombo = horizontalSorted.get(parseHorzi) + ","
                                        + verticalSorted.get(parseVert);
                                col6 = Double.parseDouble(Decimal.format(
                                        (combinations.get(currCombo)[1] * 100.00) / setUp.getPivotTotalCount()));
                            } else if (parseVert == 6) {
                                String currCombo = horizontalSorted.get(parseHorzi) + ","
                                        + verticalSorted.get(parseVert);
                                col7 = Double.parseDouble(Decimal.format(
                                        (combinations.get(currCombo)[1] * 100.00) / setUp.getPivotTotalCount()));
                            } else if (parseVert == 7) {
                                String currCombo = horizontalSorted.get(parseHorzi) + ","
                                        + verticalSorted.get(parseVert);
                                col8 = Double.parseDouble(Decimal.format(
                                        (combinations.get(currCombo)[1] * 100.00) / setUp.getPivotTotalCount()));
                            } else if (parseVert == 8) {
                                String currCombo = horizontalSorted.get(parseHorzi) + ","
                                        + verticalSorted.get(parseVert);
                                col9 = Double.parseDouble(Decimal.format(
                                        (combinations.get(currCombo)[1] * 100.00) / setUp.getPivotTotalCount()));
                            } else if (parseVert == 9) {
                                String currCombo = horizontalSorted.get(parseHorzi) + ","
                                        + verticalSorted.get(parseVert);
                                col10 = Double.parseDouble(Decimal.format(
                                        (combinations.get(currCombo)[1] * 100.00) / setUp.getPivotTotalCount()));
                            } else if (parseVert == 10) {
                                String currCombo = horizontalSorted.get(parseHorzi) + ","
                                        + verticalSorted.get(parseVert);
                                col11 = Double.parseDouble(Decimal.format(
                                        (combinations.get(currCombo)[1] * 100.00) / setUp.getPivotTotalCount()));
                            } else if (parseVert == 11) {
                                String currCombo = horizontalSorted.get(parseHorzi) + ","
                                        + verticalSorted.get(parseVert);
                                col12 = Double.parseDouble(Decimal.format(
                                        (combinations.get(currCombo)[1] * 100.00) / setUp.getPivotTotalCount()));
                            } else if (parseVert == 12) {
                                String currCombo = horizontalSorted.get(parseHorzi) + ","
                                        + verticalSorted.get(parseVert);
                                col13 = Double.parseDouble(Decimal.format(
                                        (combinations.get(currCombo)[1] * 100.00) / setUp.getPivotTotalCount()));
                            } else if (parseVert == 13) {
                                String currCombo = horizontalSorted.get(parseHorzi) + ","
                                        + verticalSorted.get(parseVert);
                                col14 = Double.parseDouble(Decimal.format(
                                        (combinations.get(currCombo)[1] * 100.00) / setUp.getPivotTotalCount()));
                            } else if (parseVert == 14) {
                                String currCombo = horizontalSorted.get(parseHorzi) + ","
                                        + verticalSorted.get(parseVert);
                                col15 = Double.parseDouble(Decimal.format(
                                        (combinations.get(currCombo)[1] * 100.00) / setUp.getPivotTotalCount()));
                            } else if (parseVert == 15) {
                                String currCombo = horizontalSorted.get(parseHorzi) + ","
                                        + verticalSorted.get(parseVert);
                                col16 = Double.parseDouble(Decimal.format(
                                        (combinations.get(currCombo)[1] * 100.00) / setUp.getPivotTotalCount()));
                            } else if (parseVert == 16) {
                                String currCombo = horizontalSorted.get(parseHorzi) + ","
                                        + verticalSorted.get(parseVert);
                                col17 = Double.parseDouble(Decimal.format(
                                        (combinations.get(currCombo)[1] * 100.00) / setUp.getPivotTotalCount()));
                            } else if (parseVert == 17) {
                                String currCombo = horizontalSorted.get(parseHorzi) + ","
                                        + verticalSorted.get(parseVert);
                                col18 = Double.parseDouble(Decimal.format(
                                        (combinations.get(currCombo)[1] * 100.00) / setUp.getPivotTotalCount()));
                            } else if (parseVert == 18) {
                                String currCombo = horizontalSorted.get(parseHorzi) + ","
                                        + verticalSorted.get(parseVert);
                                col19 = Double.parseDouble(Decimal.format(
                                        (combinations.get(currCombo)[1] * 100.00) / setUp.getPivotTotalCount()));
                            } else if (parseVert == 19) {
                                String currCombo = horizontalSorted.get(parseHorzi) + ","
                                        + verticalSorted.get(parseVert);
                                col20 = Double.parseDouble(Decimal.format(
                                        (combinations.get(currCombo)[1] * 100.00) / setUp.getPivotTotalCount()));
                            }
                        }
                        if (horizontalSorted.size() == 2) {
                            PivotTableValues.add(new PivotTable(verticalSorted.get(parseHorzi), col1, col2));
                        } else if (verticalSorted.size() == 3) {
                            PivotTableValues
                                    .add(new PivotTable(horizontalSorted.get(parseHorzi), col1, col2, col3));
                        } else if (verticalSorted.size() == 4) {
                            PivotTableValues
                                    .add(new PivotTable(horizontalSorted.get(parseHorzi), col1, col2, col3,
                                            col4));
                        } else if (verticalSorted.size() == 5) {
                            PivotTableValues
                                    .add(new PivotTable(horizontalSorted.get(parseHorzi), col1, col2, col3,
                                            col4,
                                            col5));
                        } else if (verticalSorted.size() == 6) {
                            PivotTableValues.add(
                                    new PivotTable(horizontalSorted.get(parseHorzi), col1, col2, col3, col4,
                                            col5,
                                            col6));
                        } else if (verticalSorted.size() == 7) {
                            PivotTableValues
                                    .add(new PivotTable(horizontalSorted.get(parseHorzi), col1, col2, col3,
                                            col4,
                                            col5, col6, col7));
                        } else if (verticalSorted.size() == 8) {
                            PivotTableValues
                                    .add(new PivotTable(horizontalSorted.get(parseHorzi), col1, col2, col3,
                                            col4,
                                            col5, col6, col7, col8));
                        } else if (verticalSorted.size() == 9) {
                            PivotTableValues
                                    .add(new PivotTable(horizontalSorted.get(parseHorzi), col1, col2, col3,
                                            col4,
                                            col5, col6, col7, col8, col9));
                        } else if (verticalSorted.size() == 10) {
                            PivotTableValues
                                    .add(new PivotTable(horizontalSorted.get(parseHorzi), col1, col2, col3,
                                            col4,
                                            col5, col6, col7, col8, col9, col10));
                        } else if (verticalSorted.size() == 11) {
                            PivotTableValues
                                    .add(new PivotTable(horizontalSorted.get(parseHorzi), col1, col2, col3,
                                            col4,
                                            col5, col6, col7, col8, col9, col10, col11));
                        } else if (verticalSorted.size() == 12) {
                            PivotTableValues
                                    .add(new PivotTable(horizontalSorted.get(parseHorzi), col1, col2, col3,
                                            col4,
                                            col5, col6, col7, col8, col9, col10, col11, col12));
                        } else if (verticalSorted.size() == 13) {
                            PivotTableValues
                                    .add(new PivotTable(horizontalSorted.get(parseHorzi), col1, col2, col3,
                                            col4,
                                            col5, col6, col7, col8, col9, col10, col11, col12, col13));
                        } else if (verticalSorted.size() == 14) {
                            PivotTableValues
                                    .add(new PivotTable(horizontalSorted.get(parseHorzi), col1, col2, col3,
                                            col4,
                                            col5, col6, col7, col8, col9, col10, col11, col12, col13, col14));
                        } else if (verticalSorted.size() == 15) {
                            PivotTableValues
                                    .add(new PivotTable(horizontalSorted.get(parseHorzi), col1, col2, col3,
                                            col4,
                                            col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15));
                        } else if (verticalSorted.size() == 16) {
                            PivotTableValues.add(new PivotTable(horizontalSorted.get(parseHorzi), col1, col2,
                                    col3,
                                    col4,
                                    col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16));
                        } else if (verticalSorted.size() == 17) {
                            PivotTableValues.add(
                                    new PivotTable(horizontalSorted.get(parseHorzi), col1, col2, col3, col4,
                                            col5,
                                            col6,
                                            col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17));
                        } else if (verticalSorted.size() == 18) {
                            PivotTableValues.add(new PivotTable(horizontalSorted.get(parseHorzi), col1, col2,
                                    col3,
                                    col4,
                                    col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16,
                                    col17, col18));
                        } else if (verticalSorted.size() == 19) {
                            PivotTableValues.add(new PivotTable(horizontalSorted.get(parseHorzi), col1, col2,
                                    col3,
                                    col4,
                                    col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16,
                                    col17, col18, col19));
                        } else if (verticalSorted.size() == 20) {
                            PivotTableValues.add(new PivotTable(horizontalSorted.get(parseHorzi), col1, col2,
                                    col3,
                                    col4,
                                    col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16,
                                    col17, col18, col19, col20));
                        }

                    }
                    // this.tableChart.setItems(this.tableValues);
                    setPivotTableCols(verticalSorted.size(), verticalSorted);
                    PivotTable.setItems(PivotTableValues);
                }
            }
        }
    }

    @FXML
    private void setPivotTableHorizontal(ActionEvent event) {
        if (event.toString().equals("Select..."))
            System.out.println("Horizontal Change");
    }

    private void setPivotTableCols(int colNumber, List<String> horizontalValues) {
        for (int parse = 0; parse < horizontalValues.size() && parse <= colNumber; parse++) {
            if (parse == 0) {
                Pivot_cols2.setText(horizontalValues.get(parse));
                Pivot_cols2.setCellValueFactory(new PropertyValueFactory("col2"));
            } else if (parse == 1) {
                Pivot_cols3.setText(horizontalValues.get(parse));
                Pivot_cols3.setCellValueFactory(new PropertyValueFactory("col3"));
            } else if (parse == 2) {
                Pivot_cols4.setText(horizontalValues.get(parse));
                Pivot_cols4.setCellValueFactory(new PropertyValueFactory("col4"));
            } else if (parse == 3) {
                Pivot_cols5.setText(horizontalValues.get(parse));
                Pivot_cols5.setCellValueFactory(new PropertyValueFactory("col5"));
            } else if (parse == 4) {
                Pivot_cols6.setText(horizontalValues.get(parse));
                Pivot_cols6.setCellValueFactory(new PropertyValueFactory("col6"));
            } else if (parse == 5) {
                Pivot_cols7.setText(horizontalValues.get(parse));
                Pivot_cols7.setCellValueFactory(new PropertyValueFactory("col7"));
            } else if (parse == 6) {
                Pivot_cols8.setText(horizontalValues.get(parse));
                Pivot_cols8.setCellValueFactory(new PropertyValueFactory("col8"));
            } else if (parse == 7) {
                Pivot_cols9.setText(horizontalValues.get(parse));
                Pivot_cols9.setCellValueFactory(new PropertyValueFactory("col9"));
            } else if (parse == 8) {
                Pivot_cols10.setText(horizontalValues.get(parse));
                Pivot_cols10.setCellValueFactory(new PropertyValueFactory("col10"));
            } else if (parse == 9) {
                Pivot_cols11.setText(horizontalValues.get(parse));
                Pivot_cols11.setCellValueFactory(new PropertyValueFactory("col11"));
            } else if (parse == 10) {
                Pivot_cols12.setText(horizontalValues.get(parse));
                Pivot_cols12.setCellValueFactory(new PropertyValueFactory("col12"));
            } else if (parse == 11) {
                Pivot_cols13.setText(horizontalValues.get(parse));
                Pivot_cols13.setCellValueFactory(new PropertyValueFactory("col13"));
            } else if (parse == 12) {
                Pivot_cols14.setText(horizontalValues.get(parse));
                Pivot_cols14.setCellValueFactory(new PropertyValueFactory("col14"));
            } else if (parse == 13) {
                Pivot_cols15.setText(horizontalValues.get(parse));
                Pivot_cols2.setCellValueFactory(new PropertyValueFactory("col15"));
            } else if (parse == 14) {
                Pivot_cols16.setText(horizontalValues.get(parse));
                Pivot_cols16.setCellValueFactory(new PropertyValueFactory("col16"));
            } else if (parse == 15) {
                Pivot_cols17.setText(horizontalValues.get(parse));
                Pivot_cols17.setCellValueFactory(new PropertyValueFactory("col17"));
            } else if (parse == 16) {
                Pivot_cols18.setText(horizontalValues.get(parse));
                Pivot_cols18.setCellValueFactory(new PropertyValueFactory("col18"));
            } else if (parse == 17) {
                Pivot_cols19.setText(horizontalValues.get(parse));
                Pivot_cols19.setCellValueFactory(new PropertyValueFactory("col19"));
            } else if (parse == 18) {
                Pivot_cols20.setText(horizontalValues.get(parse));
                Pivot_cols20.setCellValueFactory(new PropertyValueFactory("col20"));
            } else if (parse == 19) {
                Pivot_cols21.setText(horizontalValues.get(parse));
                Pivot_cols21.setCellValueFactory(new PropertyValueFactory("col21"));
            }
        }
        Pivot_cols1.setCellValueFactory(new PropertyValueFactory("col1"));
    }

    @FXML
    private void processExcel(ActionEvent event) {
        Button display = (Button) event.getSource();
        if (!getCustomerExcel().isEmpty() && !Files.notExists(Path.of(getCustomerExcel()), new LinkOption[0])) {
            Ingest_CustomerInfo processExcel = new Ingest_CustomerInfo();
            processExcel.setExcel_loc(getCustomerExcel());
            processExcel.parseSheet();
            if (processExcel.isThrowErr()) {
                errBox(processExcel.getErr_Message());
            } else {
                columnsValues = processExcel.getCustomerInfo().get(-1);
                verticalView.setValue("Select...");
                horizontalView.setValue("Select...");
                verticalView.getItems()
                        .addAll(FXCollections.observableArrayList(processExcel.getCustomerInfo().get(-1)));
                horizontalView.getItems()
                        .addAll(FXCollections.observableArrayList(processExcel.getCustomerInfo().get(-1)));
                customerInfo = processExcel.getCustomerInfo();
                isColsValues = processExcel.getIsColsValues();
                totalCustomerCount = processExcel.getTotalCustomerCount();
            }
            // drawCustomerTable();
            // this.overlapCharts.setVisible(true);
            // this.Customer_details.setVisible(true);
            // this.cust_Views.setVisible(true);
        } else {
            String errMessage = "Path does not exist. Please check the given path or the excel spreadsheet.";
            this.errBox(errMessage);
        }

    }
    // End pivot table design

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
    private Label enterSegment;

    // private final String[] segmentChoices = {"Entry Wallet", "Entry Banking",
    // "Middle", "Middle Market Premier", "Mass Affluent", "Affluent", "Wealthy"};

    @FXML
    private ChoiceBox segmentOptVone;// = new ChoiceBox(FXCollections.observableArrayList(segmentChoices));
    @FXML
    private Button segmentNameVone;
    @FXML
    private ChoiceBox segmentOptVtwo;// = new ChoiceBox(FXCollections.observableArrayList(segmentChoices));;
    @FXML
    private Button segmentNameVtwo;

    @FXML
    private ChoiceBox segmentBoundVoneShow;// = new ChoiceBox(FXCollections.observableArrayList(segmentChoices));
    @FXML
    private Button segmentApplyVone;
    @FXML
    private ChoiceBox segmentBoundVtwoShow;// = new ChoiceBox(FXCollections.observableArrayList(segmentChoices));;
    @FXML
    private Button segmentApplyVtwo;
    @FXML
    private ChoiceBox segmentOfferVoneRemove;// = new ChoiceBox(FXCollections.observableArrayList(segmentChoices));
    @FXML
    private Button segmentRemoveVone;
    @FXML
    private ChoiceBox segmentOfferVtwoRemove;// = new ChoiceBox(FXCollections.observableArrayList(segmentChoices));;
    @FXML
    private Button segmentRemoveVtwo;

    @FXML
    private BarChart Is_Unique_Bar_One;
    @FXML
    private CategoryAxis CompareBarOne_isUniqueX = new CategoryAxis();
    @FXML
    private NumberAxis CompareBarOne_isUniqueY = new NumberAxis();
    @FXML
    private PieChart Is_Unique_Pie_One;
    @FXML
    private BarChart Is_Unique_Bar_Two;
    @FXML
    private CategoryAxis CompareBarTwo_isUniqueX = new CategoryAxis();
    @FXML
    private NumberAxis CompareBarTwo_isUniqueY = new NumberAxis();
    @FXML
    private PieChart Is_Unique_Pie_Two;
    @FXML
    private GridPane is_unique_grid_one;
    @FXML
    private GridPane is_unique_grid_two;
    @FXML
    private RadioButton Is_Unique_Bar_One_R;
    @FXML
    private RadioButton Is_Unique_Pie_One_R;
    @FXML
    private RadioButton Is_Unique_Bar_Two_R;
    @FXML
    private RadioButton Is_Unique_Pie_Two_R;

    private Dictionary<Integer, List<String>> customerInfo;
    private final ObservableList<Data> pieChartIsUnqueViewOne = FXCollections.observableArrayList();
    private final XYChart.Series<String, Integer> barGraphIsUnqueViewOne = new XYChart.Series();
    private final ObservableList<Data> pieChartIsUnqueViewTwo = FXCollections.observableArrayList();
    private final XYChart.Series<String, Integer> barGraphIsUnqueViewTwo = new XYChart.Series();

    private final ObservableList<combination_Table> tableValues = FXCollections.observableArrayList();
    private final ObservableList<Data> pieChartValues = FXCollections.observableArrayList();
    private final XYChart.Series<String, Integer> barGraphValues = new XYChart.Series();

    private int uniqueOfferCount;
    private int boundVone = 0;
    private String excludeSelectedSegmentVone = "";
    private String selectedSegmentVone = "";
    private int boundVtwo = 0;
    private String excludeSelectedSegmentVtwo = "";
    private String selectedSegmentVtwo = "";
    private Dictionary<Integer, String> combinationCount;
    private List<int[]> sortedList;
    private int totalSelectedOffer = 0;

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
    // @FXML
    // private TableColumn Offer_name = new TableColumn("Offer_name");
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
            // this.comparsionCharts.setVisible(true);
        } else {
            String errMessage = "Path does not exist. Please check the given path or the excel spreadsheet.";
            this.errBox(errMessage);
        }

    }

    @FXML
    private void checkBoxsVisibility(ActionEvent selected) {
        CheckBox display = (CheckBox) selected.getSource();
        if (display.getId().equals("off_id")) {
            if (display.isSelected()) {
                this.off_id.setVisible(true);
                this.seg_mnt.setVisible(true);
                this._kpi_risks.setVisible(false);
                this.prim_acc.setVisible(false);
                this.relate.setVisible(false);
                this.Educ.setVisible(false);
                this.fun_pol.setVisible(false);
                this.cust.setVisible(false);
                this.inc_bnd.setVisible(false);
                this.age_bnd.setVisible(false);
            } else {
                this.seg_mnt.setSelected(false);
                this._kpi_risks.setVisible(true);
                this.prim_acc.setVisible(true);
                this.relate.setVisible(true);
                this.Educ.setVisible(true);
                this.fun_pol.setVisible(true);
                this.cust.setVisible(true);
                this.inc_bnd.setVisible(true);
                this.age_bnd.setVisible(true);
                this.segmentOptVone.setVisible(false);
                this.segmentNameVone.setVisible(false);
                this.segmentOptVtwo.setVisible(false);
                this.segmentNameVtwo.setVisible(false);
                this.enterSegment.setVisible(false);
                Is_Unique_Pie_One.setVisible(false);
                Is_Unique_Bar_One.setVisible(false);
                is_unique_grid_one.setVisible(false);
                Is_Unique_Bar_One_R.setSelected(false);
                Is_Unique_Pie_One_R.setSelected(false);
                Is_Unique_Pie_Two.setVisible(false);
                Is_Unique_Bar_Two.setVisible(false);
                is_unique_grid_two.setVisible(false);
                Is_Unique_Bar_Two_R.setSelected(false);
                Is_Unique_Pie_Two_R.setSelected(false);
            }

        } else if (display.getId().equals("_kpi_risks")) {

        } else if (display.getId().equals("prim_acc")) {

        } else if (display.getId().equals("relate")) {

        } else if (display.getId().equals("Educ")) {

        } else if (display.getId().equals("fun_pol")) {

        } else if (display.getId().equals("cust")) {

        } else if (display.getId().equals("inc_bnd")) {

        } else if (display.getId().equals("age_bnd")) {

        } else if (display.getId().equals("seg_mnt")) {
            if (seg_mnt.isSelected()) {
                ChoiceBoxSetter<Integer, List<String>> segmentOptions = new ChoiceBoxSetter<Integer, List<String>>(
                        customerInfo);
                this.off_id.setVisible(true);
                this.seg_mnt.setVisible(true);
                if (segmentOptVone.getItems().isEmpty()) {
                    segmentOptions.setDynamicArrayColumn(3);
                    segmentOptVone.getItems()
                            .addAll(FXCollections.observableArrayList(segmentOptions.getDynamicArray()));
                }
                segmentOptVone.setValue("None");
                if (segmentOptVtwo.getItems().isEmpty()) {
                    segmentOptions.setDynamicArrayColumn(3);
                    segmentOptVtwo.getItems()
                            .addAll(FXCollections.observableArrayList(segmentOptions.getDynamicArray()));
                }
                segmentOptVtwo.setValue("None");
                this.segmentOptVone.setVisible(true);
                this.segmentNameVone.setVisible(true);
                this.segmentOptVtwo.setVisible(true);
                this.segmentNameVtwo.setVisible(true);
                this.enterSegment.setVisible(true);
            } else {
                this.segmentOptVone.setVisible(false);
                this.segmentNameVone.setVisible(false);
                this.segmentOptVtwo.setVisible(false);
                this.segmentNameVtwo.setVisible(false);
                this.enterSegment.setVisible(false);
                Is_Unique_Pie_One.setVisible(false);
                Is_Unique_Bar_One.setVisible(false);
                is_unique_grid_one.setVisible(false);
                Is_Unique_Bar_One_R.setSelected(false);
                Is_Unique_Pie_One_R.setSelected(false);
                Is_Unique_Pie_Two.setVisible(false);
                Is_Unique_Bar_Two.setVisible(false);
                is_unique_grid_two.setVisible(false);
                Is_Unique_Bar_Two_R.setSelected(false);
                Is_Unique_Pie_Two_R.setSelected(false);
            }
        }
    }

    @FXML
    private void isUniqueChartOne(ActionEvent selected) {
        RadioButton display = (RadioButton) selected.getSource();
        if (display.getId().equals("Is_Unique_Bar_One_R")) {
            Is_Unique_Bar_One.setVisible(true);
            Is_Unique_Pie_One.setVisible(false);
            Is_Unique_Bar_One_R.setSelected(true);
            Is_Unique_Pie_One_R.setSelected(false);
        } else if (display.getId().equals("Is_Unique_Pie_One_R")) {
            Is_Unique_Bar_One.setVisible(false);
            Is_Unique_Pie_One.setVisible(true);
            Is_Unique_Bar_One_R.setSelected(false);
            Is_Unique_Pie_One_R.setSelected(true);
        }
    }

    @FXML
    private void isUniqueChartTwo(ActionEvent selected) {
        RadioButton display = (RadioButton) selected.getSource();
        if (display.getId().equals("Is_Unique_Bar_Two_R")) {
            Is_Unique_Bar_Two.setVisible(true);
            Is_Unique_Pie_Two.setVisible(false);
            Is_Unique_Bar_Two_R.setSelected(true);
            Is_Unique_Pie_Two_R.setSelected(false);
        } else if (display.getId().equals("Is_Unique_Pie_Two_R")) {
            Is_Unique_Bar_Two.setVisible(false);
            Is_Unique_Pie_Two.setVisible(true);
            Is_Unique_Bar_Two_R.setSelected(false);
            Is_Unique_Pie_Two_R.setSelected(true);
        }
    }

    @FXML
    private void segmentApplyVone(ActionEvent event) {
        String selected = (String) segmentBoundVoneShow.getValue();
        if (selected.equals("Show all")) {
            boundVone = uniqueOfferCount;
            drawBarIsUniqueVone(selectedSegmentVone);
        } else {
            String[] getBound = selected.split(" ");
            boundVone = Integer.parseInt(getBound[1]);
            drawBarIsUniqueVone(selectedSegmentVone);
        }

    }

    @FXML
    private void segmentRemoveVone(ActionEvent event) {
        String selected = (String) segmentOfferVoneRemove.getValue();
        excludeSelectedSegmentVone = selected;
        drawBarIsUniqueVone(selectedSegmentVone);
    }

    @FXML
    private void segmentViewOne(ActionEvent selected) {
        String selectedSegment = (String) segmentOptVone.getValue();
        isUnique unique = new isUnique(selectedSegment, customerInfo);
        combinationCount = unique.getUniqueCombinationIndex();
        Sorter sorter = new Sorter(unique.getOrderedList());
        sortedList = sorter.getSortedList();
        totalSelectedOffer = unique.getTotal();
        uniqueOfferCount = sortedList.size();
        drawBarIsUniqueVone(selectedSegment);
        pieChartIsUniqueVone(selectedSegment);
        ChoiceBoxSetter<Integer, String> applySegmentoptions = new ChoiceBoxSetter<Integer, String>(combinationCount);
        if (segmentBoundVoneShow.getItems().isEmpty()) {
            applySegmentoptions.setDynamicArraySegmentBoundApplyVone();
            segmentBoundVoneShow.getItems()
                    .addAll(FXCollections.observableArrayList(applySegmentoptions.getDynamicArray()));
        }
        segmentBoundVoneShow.setValue("Show all");
        if (segmentOfferVoneRemove.getItems().isEmpty()) {
            applySegmentoptions.setDynamicArraySegmentOfferRemoveVone();
            segmentOfferVoneRemove.getItems()
                    .addAll(FXCollections.observableArrayList(applySegmentoptions.getDynamicArray()));
        }
        segmentOfferVoneRemove.setValue("None");
        Is_Unique_Pie_One.setVisible(false);
        Is_Unique_Bar_One.setVisible(true);
        is_unique_grid_one.setVisible(true);
        Is_Unique_Bar_One_R.setSelected(true);
        Is_Unique_Pie_One_R.setSelected(false);
    }

    @FXML
    private void segmentViewTwo(ActionEvent selected) {
        String selectedSegment = (String) segmentOptVtwo.getValue();
        drawBarIsUniqueVtwo(selectedSegment);
        pieChartIsUniqueVtwo(selectedSegment);
        Is_Unique_Pie_Two.setVisible(false);
        Is_Unique_Bar_Two.setVisible(true);
        is_unique_grid_two.setVisible(true);
        Is_Unique_Bar_Two_R.setSelected(true);
        Is_Unique_Pie_Two_R.setSelected(false);
    }

    // ADJUST PERCENTAGES
    private void drawBarIsUniqueVone(String segmentName) {
        Is_Unique_Bar_One.setAnimated(false);
        Is_Unique_Bar_One.getData().clear();
        barGraphIsUnqueViewOne.getData().clear();
        Is_Unique_Bar_One.setAnimated(true);
        if (boundVone != 0) {
            for (int index = sortedList.size() - 1; index >= sortedList.size() - boundVone; index--) {
                float offerPercentage = (float) sortedList.get(index)[1] * 100 / totalSelectedOffer;
                if (!combinationCount.get(sortedList.get(index)[0]).equals(excludeSelectedSegmentVone))
                    barGraphIsUnqueViewOne.getData().add(new javafx.scene.chart.XYChart.Data(
                            combinationCount.get(sortedList.get(index)[0]), offerPercentage));
            }
        } else {
            for (int index = sortedList.size() - 1; index > 0; index--) {
                float offerPercentage = (float) sortedList.get(index)[1] * 100 / totalSelectedOffer;
                if (!combinationCount.get(sortedList.get(index)[0]).equals(excludeSelectedSegmentVone))
                    barGraphIsUnqueViewOne.getData().add(new javafx.scene.chart.XYChart.Data(
                            combinationCount.get(sortedList.get(index)[0]), offerPercentage));
            }
        }
        Is_Unique_Bar_One.getData().add(barGraphIsUnqueViewOne);
        CompareBarOne_isUniqueX.setLabel("Offer Combination");
        CompareBarTwo_isUniqueX.setAnimated(false);
        CompareBarOne_isUniqueY.setLabel("Percentage(%)");
    }

    // ADJUST PERCENTAGES
    private void drawBarIsUniqueVtwo(String segmentName) {
        Is_Unique_Bar_Two.setAnimated(false);
        Is_Unique_Bar_Two.getData().clear();
        barGraphIsUnqueViewTwo.getData().clear();
        Is_Unique_Bar_Two.setAnimated(true);
        barGraphIsUnqueViewTwo.getData().clear();
        isUnique unique = new isUnique(segmentName, customerInfo);
        // Dictionary<String, Integer> combinationCount = unique.getUniqueCombination();
        // for(Enumeration combination = combinationCount.keys();
        // combination.hasMoreElements();)
        // {
        // String offer = (String) combination.nextElement();
        // if (!offer.equals("Total")) {
        // int percentage = (combinationCount.get(offer) * 100) /
        // combinationCount.get("Total");
        // barGraphIsUnqueViewTwo.getData().add(new
        // javafx.scene.chart.XYChart.Data(offer, percentage));
        // }
        // }
        Is_Unique_Bar_Two.getData().add(barGraphIsUnqueViewTwo);
        CompareBarTwo_isUniqueX.setLabel("Offer Combination");
        CompareBarTwo_isUniqueX.setAnimated(false);
        CompareBarTwo_isUniqueY.setLabel("Percentage(%)");
    }

    // ADJUST PERCENTAGES
    private void pieChartIsUniqueVone(String segmentName) {
        Is_Unique_Pie_One.getData().clear();
        pieChartIsUnqueViewOne.clear();
        isUnique unique = new isUnique(segmentName, customerInfo);
        // Dictionary<String, Integer> combinationCount = unique.getUniqueCombination();
        // for(Enumeration combination = combinationCount.keys();
        // combination.hasMoreElements();)
        // {
        // String offer = (String) combination.nextElement();
        // if (!offer.equals("Total")) {
        // int percentage = (combinationCount.get(offer) * 100) /
        // combinationCount.get("Total");
        // pieChartIsUnqueViewOne.add(new Data(offer, percentage));
        // }
        // }
        Is_Unique_Pie_One.setData(pieChartIsUnqueViewOne);
        Is_Unique_Pie_One.setLabelsVisible(true);
        Is_Unique_Pie_One.setClockwise(true);
    }

    // ADJUST PERCENTAGES
    private void pieChartIsUniqueVtwo(String segmentName) {
        Is_Unique_Pie_Two.getData().clear();
        pieChartIsUnqueViewTwo.clear();
        isUnique unique = new isUnique(segmentName, customerInfo);
        // Dictionary<String, Integer> combinationCount = unique.getUniqueCombination();
        // for(Enumeration combination = combinationCount.keys();
        // combination.hasMoreElements();)
        // {
        // String offer = (String) combination.nextElement();
        // if (!offer.equals("Total")) {
        // int percentage = (combinationCount.get(offer) * 100) /
        // combinationCount.get("Total");
        // pieChartIsUnqueViewTwo.add(new Data(offer, percentage));
        // }
        // }
        Is_Unique_Pie_Two.setData(pieChartIsUnqueViewTwo);
        Is_Unique_Pie_Two.setLabelsVisible(true);
        Is_Unique_Pie_Two.setClockwise(true);
    }

    @FXML
    private void Ingest(ActionEvent event) {
        TextField loca = (TextField) event.getSource();
        this.setCustomerExcel(loca.getText());
    }

    @FXML
    private void Ingest(MouseEvent event) {
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
                this.tableValues.add(new combination_Table(count + ". " + (String) this.combinationList.get(index),
                        ((int[]) this.offerSize.get(index))[1], (Integer) this.customerNumberList.get(index)));
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

    // ADJUST PERCENTAGES
    private void drawPieChart() {
        this.pieChart.getData().clear();
        int combinationCount = ((int[]) this.offerSize.get(0))[1];
        int combinationSum = (Integer) this.customerNumberList.get(0);

        for (int index = 1; index < this.offerSize.size(); ++index) {
            int percentage;
            if (combinationCount != ((int[]) this.offerSize.get(index))[1]) {
                percentage = combinationSum * 100 / this.totalCustomers;
                this.pieChartValues.add(new Data(String.valueOf(combinationCount) + " Pillars: " + percentage + "%",
                        (double) combinationSum));
                combinationCount = ((int[]) this.offerSize.get(index))[1];
                combinationSum = (Integer) this.customerNumberList.get(index);
            } else if (index + 1 == this.offerSize.size()) {
                percentage = combinationSum * 100 / this.totalCustomers;
                this.pieChartValues.add(new Data(String.valueOf(combinationCount) + " Pillar: " + percentage + "%",
                        (double) combinationSum));
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

    // ADJUST PERCENTAGES
    private void drawBarGraph() {
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
                this.barGraphValues.getData().add(
                        new javafx.scene.chart.XYChart.Data(String.valueOf(combinationCount) + " Pillars", percentage));
                combinationCount = ((int[]) this.offerSize.get(index))[1];
                combinationSum = (Integer) this.customerNumberList.get(index);
            } else if (index + 1 == this.offerSize.size()) {
                percentage = combinationSum * 100 / this.totalCustomers;
                this.barGraphValues.getData().add(
                        new javafx.scene.chart.XYChart.Data(String.valueOf(combinationCount) + " Pillar", percentage));
            } else {
                combinationSum += (Integer) this.customerNumberList.get(index);
            }
        }

        this.barChart.getData().add(this.barGraphValues);
    }

    private void drawCustomerTable() {
        Ingest_CustomerInfo processExcel = new Ingest_CustomerInfo();
        processExcel.setExcel_loc(getCustomerExcel());
        processExcel.parseSheet();

        if (processExcel.isThrowErr()) {
            errBox(processExcel.getErr_Message());
        } else {
            customerInfo = processExcel.getCustomerInfo();
            Customer_details.getItems().clear();
            for (int index = 0; index < customerInfo.size(); index++) {
                int count = index + 1;
                List<String> details = customerInfo.get(count);
                customerValues.add(new IngestCustomers(details.get(0), details.get(1), details.get(2), details.get(3),
                        details.get(4), details.get(5), details.get(6), details.get(7), details.get(8),
                        details.get(9)));
            }
            if (processExcel.isThrowErr())
                errBox(processExcel.getErr_Message());
            Offer_ID.setCellValueFactory(new PropertyValueFactory("Offer_ID"));
            // Offer_name.setCellValueFactory(new PropertyValueFactory("Offer_Name"));
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
    private void chartsChangeView(ActionEvent selected) {
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
    private void subsegmentSelect(ActionEvent selected) {
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
    private void plotCompareCharts(ActionEvent chart) {
    }

    @FXML
    private void Location(ActionEvent event) {
        TextField loca = (TextField) event.getSource();
        this.setExcel_loc(loca.getText());
    }

    @FXML
    private void Location(MouseEvent event) {
        TextField loca = (TextField) event.getSource();
        this.setExcel_loc(loca.getText());
    }
**/
}