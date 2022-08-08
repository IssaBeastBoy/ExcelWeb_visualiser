package com.example.dashboard;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class PivotTable {
    private final SimpleStringProperty col1;
    private final SimpleDoubleProperty col2;
    private final SimpleDoubleProperty col3;
    private final SimpleDoubleProperty col4;
    private final SimpleDoubleProperty col5;
    private final SimpleDoubleProperty col6;
    private final SimpleDoubleProperty col7;
    private final SimpleDoubleProperty col8;
    private final SimpleDoubleProperty col9;
    private final SimpleDoubleProperty col10;

    public String getCol1() {
        return col1.get();
    }

    public SimpleStringProperty col1Property() {
        return col1;
    }

    public void setCol1(String col1) {
        this.col1.set(col1);
    }

    public Double getCol2() {
        return col2.get();
    }

    public SimpleDoubleProperty col2Property() {
        return col2;
    }

    public void setCol2(Double col2) {
        this.col2.set(col2);
    }

    public Double getCol3() {
        return col3.get();
    }

    public SimpleDoubleProperty col3Property() {
        return col3;
    }

    public void setCol3(Double col3) {
        this.col3.set(col3);
    }

    public Double getCol4() {
        return col4.get();
    }

    public SimpleDoubleProperty col4Property() {
        return col4;
    }

    public void setCol4(Double col4) {
        this.col4.set(col4);
    }

    public Double getCol5() {
        return col5.get();
    }

    public SimpleDoubleProperty col5Property() {
        return col5;
    }

    public void setCol5(Double col5) {
        this.col5.set(col5);
    }

    public Double getCol6() {
        return col6.get();
    }

    public SimpleDoubleProperty col6Property() {
        return col6;
    }

    public void setCol6(Double col6) {
        this.col6.set(col6);
    }

    public Double getCol7() {
        return col7.get();
    }

    public SimpleDoubleProperty col7Property() {
        return col7;
    }

    public void setCol7(Double col7) {
        this.col7.set(col7);
    }

    public Double getCol8() {
        return col8.get();
    }

    public SimpleDoubleProperty col8Property() {
        return col8;
    }

    public void setCol8(Double col8) {
        this.col8.set(col8);
    }

    public Double getCol9() {
        return col9.get();
    }

    public SimpleDoubleProperty col9Property() {
        return col9;
    }

    public void setCol9(Double col9) {
        this.col9.set(col9);
    }

    public Double getCol10() {
        return col10.get();
    }

    public SimpleDoubleProperty col10Property() {
        return col10;
    }

    public void setCol10(Double col10) {
        this.col10.set(col10);
    }

    public Double getCol11() {
        return col11.get();
    }

    public SimpleDoubleProperty col11Property() {
        return col11;
    }

    public void setCol11(Double col11) {
        this.col11.set(col11);
    }

    public Double getCol12() {
        return col12.get();
    }

    public SimpleDoubleProperty col12Property() {
        return col12;
    }

    public void setCol12(Double col12) {
        this.col12.set(col12);
    }

    public Double getCol13() {
        return col13.get();
    }

    public SimpleDoubleProperty col13Property() {
        return col13;
    }

    public void setCol13(Double col13) {
        this.col13.set(col13);
    }

    public Double getCol14() {
        return col14.get();
    }

    public SimpleDoubleProperty col14Property() {
        return col14;
    }

    public void setCol14(Double col14) {
        this.col14.set(col14);
    }

    public Double getCol15() {
        return col15.get();
    }

    public SimpleDoubleProperty col15Property() {
        return col15;
    }

    public void setCol15(Double col15) {
        this.col15.set(col15);
    }

    public Double getCol16() {
        return col16.get();
    }

    public SimpleDoubleProperty col16Property() {
        return col16;
    }

    public void setCol16(Double col16) {
        this.col16.set(col16);
    }

    public Double getCol17() {
        return col17.get();
    }

    public SimpleDoubleProperty col17Property() {
        return col17;
    }

    public void setCol17(Double col17) {
        this.col17.set(col17);
    }

    public Double getCol18() {
        return col18.get();
    }

    public SimpleDoubleProperty col18Property() {
        return col18;
    }

    public void setCol18(Double col18) {
        this.col18.set(col18);
    }

    public Double getCol19() {
        return col19.get();
    }

    public SimpleDoubleProperty col19Property() {
        return col19;
    }

    public void setCol19(Double col19) {
        this.col19.set(col19);
    }

    public Double getCol20() {
        return col20.get();
    }

    public SimpleDoubleProperty col20Property() {
        return col20;
    }

    public void setCol20(Double col20) {
        this.col20.set(col20);
    }

    public Double getCol21() {
        return col21.get();
    }

    public SimpleDoubleProperty col21Property() {
        return col21;
    }

    public void setCol21(Double col21) {
        this.col21.set(col21);
    }

    private final SimpleDoubleProperty col11;
    private final SimpleDoubleProperty col12;
    private final SimpleDoubleProperty col13;
    private final SimpleDoubleProperty col14;
    private final SimpleDoubleProperty col15;
    private final SimpleDoubleProperty col16;
    private final SimpleDoubleProperty col17;
    private final SimpleDoubleProperty col18;
    private final SimpleDoubleProperty col19;
    private final SimpleDoubleProperty col20;
    private final SimpleDoubleProperty col21;

    public PivotTable(String col1, Double col2, Double col3) {
        this.col1 = new SimpleStringProperty(col1);
        this.col2 = new SimpleDoubleProperty(col2);
        this.col3 = new SimpleDoubleProperty(col3);
        this.col4 = new SimpleDoubleProperty();
        this.col5 = new SimpleDoubleProperty();
        this.col6 = new SimpleDoubleProperty();
        this.col7 = new SimpleDoubleProperty();
        this.col8 = new SimpleDoubleProperty();
        this.col9 = new SimpleDoubleProperty();
        this.col10 = new SimpleDoubleProperty();
        this.col11 = new SimpleDoubleProperty();
        this.col12 = new SimpleDoubleProperty();
        this.col13 = new SimpleDoubleProperty();
        this.col14 = new SimpleDoubleProperty();
        this.col15 = new SimpleDoubleProperty();
        this.col16 = new SimpleDoubleProperty();
        this.col17 = new SimpleDoubleProperty();
        this.col18 = new SimpleDoubleProperty();
        this.col19 = new SimpleDoubleProperty();
        this.col20 = new SimpleDoubleProperty();
        this.col21 = new SimpleDoubleProperty();
    }

    public PivotTable(String col1, Double col2, Double col3, Double col4) {
        this.col1 = new SimpleStringProperty(col1);
        this.col2 = new SimpleDoubleProperty(col2);
        this.col3 = new SimpleDoubleProperty(col3);
        this.col4 = new SimpleDoubleProperty(col4);
        this.col5 = new SimpleDoubleProperty();
        this.col6 = new SimpleDoubleProperty();
        this.col7 = new SimpleDoubleProperty();
        this.col8 = new SimpleDoubleProperty();
        this.col9 = new SimpleDoubleProperty();
        this.col10 = new SimpleDoubleProperty();
        this.col11 = new SimpleDoubleProperty();
        this.col12 = new SimpleDoubleProperty();
        this.col13 = new SimpleDoubleProperty();
        this.col14 = new SimpleDoubleProperty();
        this.col15 = new SimpleDoubleProperty();
        this.col16 = new SimpleDoubleProperty();
        this.col17 = new SimpleDoubleProperty();
        this.col18 = new SimpleDoubleProperty();
        this.col19 = new SimpleDoubleProperty();
        this.col20 = new SimpleDoubleProperty();
        this.col21 = new SimpleDoubleProperty();
    }

    public PivotTable(String col1, Double col2, Double col3, Double col4, Double col5) {
        this.col1 = new SimpleStringProperty(col1);
        this.col2 = new SimpleDoubleProperty(col2);
        this.col3 = new SimpleDoubleProperty(col3);
        this.col4 = new SimpleDoubleProperty(col4);
        this.col5 = new SimpleDoubleProperty(col5);
        this.col6 = new SimpleDoubleProperty();
        this.col7 = new SimpleDoubleProperty();
        this.col8 = new SimpleDoubleProperty();
        this.col9 = new SimpleDoubleProperty();
        this.col10 = new SimpleDoubleProperty();
        this.col11 = new SimpleDoubleProperty();
        this.col12 = new SimpleDoubleProperty();
        this.col13 = new SimpleDoubleProperty();
        this.col14 = new SimpleDoubleProperty();
        this.col15 = new SimpleDoubleProperty();
        this.col16 = new SimpleDoubleProperty();
        this.col17 = new SimpleDoubleProperty();
        this.col18 = new SimpleDoubleProperty();
        this.col19 = new SimpleDoubleProperty();
        this.col20 = new SimpleDoubleProperty();
        this.col21 = new SimpleDoubleProperty();
    }

    public PivotTable(String col1, Double col2, Double col3, Double col4, Double col5, Double col6) {
        this.col1 = new SimpleStringProperty(col1);
        this.col2 = new SimpleDoubleProperty(col2);
        this.col3 = new SimpleDoubleProperty(col3);
        this.col4 = new SimpleDoubleProperty(col4);
        this.col5 = new SimpleDoubleProperty(col5);
        this.col6 = new SimpleDoubleProperty(col6);
        this.col7 = new SimpleDoubleProperty();
        this.col8 = new SimpleDoubleProperty();
        this.col9 = new SimpleDoubleProperty();
        this.col10 = new SimpleDoubleProperty();
        this.col11 = new SimpleDoubleProperty();
        this.col12 = new SimpleDoubleProperty();
        this.col13 = new SimpleDoubleProperty();
        this.col14 = new SimpleDoubleProperty();
        this.col15 = new SimpleDoubleProperty();
        this.col16 = new SimpleDoubleProperty();
        this.col17 = new SimpleDoubleProperty();
        this.col18 = new SimpleDoubleProperty();
        this.col19 = new SimpleDoubleProperty();
        this.col20 = new SimpleDoubleProperty();
        this.col21 = new SimpleDoubleProperty();
    }

    public PivotTable(String col1, Double col2, Double col3, Double col4, Double col5, Double col6, Double col7) {
        this.col1 = new SimpleStringProperty(col1);
        this.col2 = new SimpleDoubleProperty(col2);
        this.col3 = new SimpleDoubleProperty(col3);
        this.col4 = new SimpleDoubleProperty(col4);
        this.col5 = new SimpleDoubleProperty(col5);
        this.col6 = new SimpleDoubleProperty(col6);
        this.col7 = new SimpleDoubleProperty(col7);
        this.col8 = new SimpleDoubleProperty();
        this.col9 = new SimpleDoubleProperty();
        this.col10 = new SimpleDoubleProperty();
        this.col11 = new SimpleDoubleProperty();
        this.col12 = new SimpleDoubleProperty();
        this.col13 = new SimpleDoubleProperty();
        this.col14 = new SimpleDoubleProperty();
        this.col15 = new SimpleDoubleProperty();
        this.col16 = new SimpleDoubleProperty();
        this.col17 = new SimpleDoubleProperty();
        this.col18 = new SimpleDoubleProperty();
        this.col19 = new SimpleDoubleProperty();
        this.col20 = new SimpleDoubleProperty();
        this.col21 = new SimpleDoubleProperty();
    }

    public PivotTable(String col1, Double col2, Double col3, Double col4, Double col5, Double col6, Double col7,
            Double col8) {
        this.col1 = new SimpleStringProperty(col1);
        this.col2 = new SimpleDoubleProperty(col2);
        this.col3 = new SimpleDoubleProperty(col3);
        this.col4 = new SimpleDoubleProperty(col4);
        this.col5 = new SimpleDoubleProperty(col5);
        this.col6 = new SimpleDoubleProperty(col6);
        this.col7 = new SimpleDoubleProperty(col7);
        this.col8 = new SimpleDoubleProperty(col8);
        this.col9 = new SimpleDoubleProperty();
        this.col10 = new SimpleDoubleProperty();
        this.col11 = new SimpleDoubleProperty();
        this.col12 = new SimpleDoubleProperty();
        this.col13 = new SimpleDoubleProperty();
        this.col14 = new SimpleDoubleProperty();
        this.col15 = new SimpleDoubleProperty();
        this.col16 = new SimpleDoubleProperty();
        this.col17 = new SimpleDoubleProperty();
        this.col18 = new SimpleDoubleProperty();
        this.col19 = new SimpleDoubleProperty();
        this.col20 = new SimpleDoubleProperty();
        this.col21 = new SimpleDoubleProperty();
    }

    public PivotTable(String col1, Double col2, Double col3, Double col4, Double col5, Double col6, Double col7,
            Double col8,
            Double col9) {
        this.col1 = new SimpleStringProperty(col1);
        this.col2 = new SimpleDoubleProperty(col2);
        this.col3 = new SimpleDoubleProperty(col3);
        this.col4 = new SimpleDoubleProperty(col4);
        this.col5 = new SimpleDoubleProperty(col5);
        this.col6 = new SimpleDoubleProperty(col6);
        this.col7 = new SimpleDoubleProperty(col7);
        this.col8 = new SimpleDoubleProperty(col8);
        this.col9 = new SimpleDoubleProperty(col9);
        this.col10 = new SimpleDoubleProperty();
        this.col11 = new SimpleDoubleProperty();
        this.col12 = new SimpleDoubleProperty();
        this.col13 = new SimpleDoubleProperty();
        this.col14 = new SimpleDoubleProperty();
        this.col15 = new SimpleDoubleProperty();
        this.col16 = new SimpleDoubleProperty();
        this.col17 = new SimpleDoubleProperty();
        this.col18 = new SimpleDoubleProperty();
        this.col19 = new SimpleDoubleProperty();
        this.col20 = new SimpleDoubleProperty();
        this.col21 = new SimpleDoubleProperty();
    }

    public PivotTable(String col1, Double col2, Double col3, Double col4, Double col5, Double col6, Double col7,
            Double col8,
            Double col9, Double col10) {
        this.col1 = new SimpleStringProperty(col1);
        this.col2 = new SimpleDoubleProperty(col2);
        this.col3 = new SimpleDoubleProperty(col3);
        this.col4 = new SimpleDoubleProperty(col4);
        this.col5 = new SimpleDoubleProperty(col5);
        this.col6 = new SimpleDoubleProperty(col6);
        this.col7 = new SimpleDoubleProperty(col7);
        this.col8 = new SimpleDoubleProperty(col8);
        this.col9 = new SimpleDoubleProperty(col9);
        this.col10 = new SimpleDoubleProperty(col10);
        this.col11 = new SimpleDoubleProperty();
        this.col12 = new SimpleDoubleProperty();
        this.col13 = new SimpleDoubleProperty();
        this.col14 = new SimpleDoubleProperty();
        this.col15 = new SimpleDoubleProperty();
        this.col16 = new SimpleDoubleProperty();
        this.col17 = new SimpleDoubleProperty();
        this.col18 = new SimpleDoubleProperty();
        this.col19 = new SimpleDoubleProperty();
        this.col20 = new SimpleDoubleProperty();
        this.col21 = new SimpleDoubleProperty();
    }

    public PivotTable(String col1, Double col2, Double col3, Double col4, Double col5, Double col6, Double col7,
            Double col8,
            Double col9, Double col10, Double col11) {
        this.col1 = new SimpleStringProperty(col1);
        this.col2 = new SimpleDoubleProperty(col2);
        this.col3 = new SimpleDoubleProperty(col3);
        this.col4 = new SimpleDoubleProperty(col4);
        this.col5 = new SimpleDoubleProperty(col5);
        this.col6 = new SimpleDoubleProperty(col6);
        this.col7 = new SimpleDoubleProperty(col7);
        this.col8 = new SimpleDoubleProperty(col8);
        this.col9 = new SimpleDoubleProperty(col9);
        this.col10 = new SimpleDoubleProperty(col10);
        this.col11 = new SimpleDoubleProperty(col11);
        this.col12 = new SimpleDoubleProperty();
        this.col13 = new SimpleDoubleProperty();
        this.col14 = new SimpleDoubleProperty();
        this.col15 = new SimpleDoubleProperty();
        this.col16 = new SimpleDoubleProperty();
        this.col17 = new SimpleDoubleProperty();
        this.col18 = new SimpleDoubleProperty();
        this.col19 = new SimpleDoubleProperty();
        this.col20 = new SimpleDoubleProperty();
        this.col21 = new SimpleDoubleProperty();
    }

    public PivotTable(String col1, Double col2, Double col3, Double col4, Double col5, Double col6, Double col7,
            Double col8,
            Double col9, Double col10, Double col11, Double col12) {
        this.col1 = new SimpleStringProperty(col1);
        this.col2 = new SimpleDoubleProperty(col2);
        this.col3 = new SimpleDoubleProperty(col3);
        this.col4 = new SimpleDoubleProperty(col4);
        this.col5 = new SimpleDoubleProperty(col5);
        this.col6 = new SimpleDoubleProperty(col6);
        this.col7 = new SimpleDoubleProperty(col7);
        this.col8 = new SimpleDoubleProperty(col8);
        this.col9 = new SimpleDoubleProperty(col9);
        this.col10 = new SimpleDoubleProperty(col10);
        this.col11 = new SimpleDoubleProperty(col11);
        this.col12 = new SimpleDoubleProperty(col12);
        this.col13 = new SimpleDoubleProperty();
        this.col14 = new SimpleDoubleProperty();
        this.col15 = new SimpleDoubleProperty();
        this.col16 = new SimpleDoubleProperty();
        this.col17 = new SimpleDoubleProperty();
        this.col18 = new SimpleDoubleProperty();
        this.col19 = new SimpleDoubleProperty();
        this.col20 = new SimpleDoubleProperty();
        this.col21 = new SimpleDoubleProperty();
    }

    public PivotTable(String col1, Double col2, Double col3, Double col4, Double col5, Double col6, Double col7,
            Double col8,
            Double col9, Double col10, Double col11, Double col12, Double col13) {
        this.col1 = new SimpleStringProperty(col1);
        this.col2 = new SimpleDoubleProperty(col2);
        this.col3 = new SimpleDoubleProperty(col3);
        this.col4 = new SimpleDoubleProperty(col4);
        this.col5 = new SimpleDoubleProperty(col5);
        this.col6 = new SimpleDoubleProperty(col6);
        this.col7 = new SimpleDoubleProperty(col7);
        this.col8 = new SimpleDoubleProperty(col8);
        this.col9 = new SimpleDoubleProperty(col9);
        this.col10 = new SimpleDoubleProperty(col10);
        this.col11 = new SimpleDoubleProperty(col11);
        this.col12 = new SimpleDoubleProperty(col12);
        this.col13 = new SimpleDoubleProperty(col13);
        this.col14 = new SimpleDoubleProperty();
        this.col15 = new SimpleDoubleProperty();
        this.col16 = new SimpleDoubleProperty();
        this.col17 = new SimpleDoubleProperty();
        this.col18 = new SimpleDoubleProperty();
        this.col19 = new SimpleDoubleProperty();
        this.col20 = new SimpleDoubleProperty();
        this.col21 = new SimpleDoubleProperty();
    }

    public PivotTable(String col1, Double col2, Double col3, Double col4, Double col5, Double col6, Double col7,
            Double col8,
            Double col9, Double col10, Double col11, Double col12, Double col13, Double col14) {
        this.col1 = new SimpleStringProperty(col1);
        this.col2 = new SimpleDoubleProperty(col2);
        this.col3 = new SimpleDoubleProperty(col3);
        this.col4 = new SimpleDoubleProperty(col4);
        this.col5 = new SimpleDoubleProperty(col5);
        this.col6 = new SimpleDoubleProperty(col6);
        this.col7 = new SimpleDoubleProperty(col7);
        this.col8 = new SimpleDoubleProperty(col8);
        this.col9 = new SimpleDoubleProperty(col9);
        this.col10 = new SimpleDoubleProperty(col10);
        this.col11 = new SimpleDoubleProperty(col11);
        this.col12 = new SimpleDoubleProperty(col12);
        this.col13 = new SimpleDoubleProperty(col13);
        this.col14 = new SimpleDoubleProperty(col14);
        this.col15 = new SimpleDoubleProperty();
        this.col16 = new SimpleDoubleProperty();
        this.col17 = new SimpleDoubleProperty();
        this.col18 = new SimpleDoubleProperty();
        this.col19 = new SimpleDoubleProperty();
        this.col20 = new SimpleDoubleProperty();
        this.col21 = new SimpleDoubleProperty();
    }

    public PivotTable(String col1, Double col2, Double col3, Double col4, Double col5, Double col6, Double col7,
            Double col8,
            Double col9, Double col10, Double col11, Double col12, Double col13, Double col14, Double col15) {
        this.col1 = new SimpleStringProperty(col1);
        this.col2 = new SimpleDoubleProperty(col2);
        this.col3 = new SimpleDoubleProperty(col3);
        this.col4 = new SimpleDoubleProperty(col4);
        this.col5 = new SimpleDoubleProperty(col5);
        this.col6 = new SimpleDoubleProperty(col6);
        this.col7 = new SimpleDoubleProperty(col7);
        this.col8 = new SimpleDoubleProperty(col8);
        this.col9 = new SimpleDoubleProperty(col9);
        this.col10 = new SimpleDoubleProperty(col10);
        this.col11 = new SimpleDoubleProperty(col11);
        this.col12 = new SimpleDoubleProperty(col12);
        this.col13 = new SimpleDoubleProperty(col13);
        this.col14 = new SimpleDoubleProperty(col14);
        this.col15 = new SimpleDoubleProperty(col15);
        this.col16 = new SimpleDoubleProperty();
        this.col17 = new SimpleDoubleProperty();
        this.col18 = new SimpleDoubleProperty();
        this.col19 = new SimpleDoubleProperty();
        this.col20 = new SimpleDoubleProperty();
        this.col21 = new SimpleDoubleProperty();
    }

    public PivotTable(String col1, Double col2, Double col3, Double col4, Double col5, Double col6, Double col7,
            Double col8,
            Double col9, Double col10, Double col11, Double col12, Double col13, Double col14, Double col15,
            Double col16) {
        this.col1 = new SimpleStringProperty(col1);
        this.col2 = new SimpleDoubleProperty(col2);
        this.col3 = new SimpleDoubleProperty(col3);
        this.col4 = new SimpleDoubleProperty(col4);
        this.col5 = new SimpleDoubleProperty(col5);
        this.col6 = new SimpleDoubleProperty(col6);
        this.col7 = new SimpleDoubleProperty(col7);
        this.col8 = new SimpleDoubleProperty(col8);
        this.col9 = new SimpleDoubleProperty(col9);
        this.col10 = new SimpleDoubleProperty(col10);
        this.col11 = new SimpleDoubleProperty(col11);
        this.col12 = new SimpleDoubleProperty(col12);
        this.col13 = new SimpleDoubleProperty(col13);
        this.col14 = new SimpleDoubleProperty(col14);
        this.col15 = new SimpleDoubleProperty(col15);
        this.col16 = new SimpleDoubleProperty(col16);
        this.col17 = new SimpleDoubleProperty();
        this.col18 = new SimpleDoubleProperty();
        this.col19 = new SimpleDoubleProperty();
        this.col20 = new SimpleDoubleProperty();
        this.col21 = new SimpleDoubleProperty();
    }

    public PivotTable(String col1, Double col2, Double col3, Double col4, Double col5, Double col6, Double col7,
            Double col8,
            Double col9, Double col10, Double col11, Double col12, Double col13, Double col14, Double col15,
            Double col16,
            Double col17) {
        this.col1 = new SimpleStringProperty(col1);
        this.col2 = new SimpleDoubleProperty(col2);
        this.col3 = new SimpleDoubleProperty(col3);
        this.col4 = new SimpleDoubleProperty(col4);
        this.col5 = new SimpleDoubleProperty(col5);
        this.col6 = new SimpleDoubleProperty(col6);
        this.col7 = new SimpleDoubleProperty(col7);
        this.col8 = new SimpleDoubleProperty(col8);
        this.col9 = new SimpleDoubleProperty(col9);
        this.col10 = new SimpleDoubleProperty(col10);
        this.col11 = new SimpleDoubleProperty(col11);
        this.col12 = new SimpleDoubleProperty(col12);
        this.col13 = new SimpleDoubleProperty(col13);
        this.col14 = new SimpleDoubleProperty(col14);
        this.col15 = new SimpleDoubleProperty(col15);
        this.col16 = new SimpleDoubleProperty(col16);
        this.col17 = new SimpleDoubleProperty(col17);
        this.col18 = new SimpleDoubleProperty();
        this.col19 = new SimpleDoubleProperty();
        this.col20 = new SimpleDoubleProperty();
        this.col21 = new SimpleDoubleProperty();
    }

    public PivotTable(String col1, Double col2, Double col3, Double col4, Double col5, Double col6, Double col7,
            Double col8,
            Double col9, Double col10, Double col11, Double col12, Double col13, Double col14, Double col15,
            Double col16, Double col17, Double col18) {
        this.col1 = new SimpleStringProperty(col1);
        this.col2 = new SimpleDoubleProperty(col2);
        this.col3 = new SimpleDoubleProperty(col3);
        this.col4 = new SimpleDoubleProperty(col4);
        this.col5 = new SimpleDoubleProperty(col5);
        this.col6 = new SimpleDoubleProperty(col6);
        this.col7 = new SimpleDoubleProperty(col7);
        this.col8 = new SimpleDoubleProperty(col8);
        this.col9 = new SimpleDoubleProperty(col9);
        this.col10 = new SimpleDoubleProperty(col10);
        this.col11 = new SimpleDoubleProperty(col11);
        this.col12 = new SimpleDoubleProperty(col12);
        this.col13 = new SimpleDoubleProperty(col13);
        this.col14 = new SimpleDoubleProperty(col14);
        this.col15 = new SimpleDoubleProperty(col15);
        this.col16 = new SimpleDoubleProperty(col16);
        this.col17 = new SimpleDoubleProperty(col17);
        this.col18 = new SimpleDoubleProperty(col18);
        this.col19 = new SimpleDoubleProperty();
        this.col20 = new SimpleDoubleProperty();
        this.col21 = new SimpleDoubleProperty();
    }

    public PivotTable(String col1, Double col2, Double col3, Double col4, Double col5, Double col6, Double col7,
            Double col8,
            Double col9, Double col10, Double col11, Double col12, Double col13, Double col14, Double col15,
            Double col16, Double col17, Double col18, Double col19) {
        this.col1 = new SimpleStringProperty(col1);
        this.col2 = new SimpleDoubleProperty(col2);
        this.col3 = new SimpleDoubleProperty(col3);
        this.col4 = new SimpleDoubleProperty(col4);
        this.col5 = new SimpleDoubleProperty(col5);
        this.col6 = new SimpleDoubleProperty(col6);
        this.col7 = new SimpleDoubleProperty(col7);
        this.col8 = new SimpleDoubleProperty(col8);
        this.col9 = new SimpleDoubleProperty(col9);
        this.col10 = new SimpleDoubleProperty(col10);
        this.col11 = new SimpleDoubleProperty(col11);
        this.col12 = new SimpleDoubleProperty(col12);
        this.col13 = new SimpleDoubleProperty(col13);
        this.col14 = new SimpleDoubleProperty(col14);
        this.col15 = new SimpleDoubleProperty(col15);
        this.col16 = new SimpleDoubleProperty(col16);
        this.col17 = new SimpleDoubleProperty(col17);
        this.col18 = new SimpleDoubleProperty(col18);
        this.col19 = new SimpleDoubleProperty(col19);
        this.col20 = new SimpleDoubleProperty();
        this.col21 = new SimpleDoubleProperty();
    }

    public PivotTable(String col1, Double col2, Double col3, Double col4, Double col5, Double col6, Double col7,
            Double col8,
            Double col9, Double col10, Double col11, Double col12, Double col13, Double col14, Double col15,
            Double col16, Double col17, Double col18, Double col19, Double col20) {
        this.col1 = new SimpleStringProperty(col1);
        this.col2 = new SimpleDoubleProperty(col2);
        this.col3 = new SimpleDoubleProperty(col3);
        this.col4 = new SimpleDoubleProperty(col4);
        this.col5 = new SimpleDoubleProperty(col5);
        this.col6 = new SimpleDoubleProperty(col6);
        this.col7 = new SimpleDoubleProperty(col7);
        this.col8 = new SimpleDoubleProperty(col8);
        this.col9 = new SimpleDoubleProperty(col9);
        this.col10 = new SimpleDoubleProperty(col10);
        this.col11 = new SimpleDoubleProperty(col11);
        this.col12 = new SimpleDoubleProperty(col12);
        this.col13 = new SimpleDoubleProperty(col13);
        this.col14 = new SimpleDoubleProperty(col14);
        this.col15 = new SimpleDoubleProperty(col15);
        this.col16 = new SimpleDoubleProperty(col16);
        this.col17 = new SimpleDoubleProperty(col17);
        this.col18 = new SimpleDoubleProperty(col18);
        this.col19 = new SimpleDoubleProperty(col19);
        this.col20 = new SimpleDoubleProperty(col20);
        this.col21 = new SimpleDoubleProperty();
    }

    public PivotTable(String col1, Double col2, Double col3, Double col4, Double col5, Double col6, Double col7,
            Double col8,
            Double col9, Double col10, Double col11, Double col12, Double col13, Double col14, Double col15,
            Double col16,
            Double col17, Double col18, Double col19, Double col20, Double col21) {
        this.col1 = new SimpleStringProperty(col1);
        this.col2 = new SimpleDoubleProperty(col2);
        this.col3 = new SimpleDoubleProperty(col3);
        this.col4 = new SimpleDoubleProperty(col4);
        this.col5 = new SimpleDoubleProperty(col5);
        this.col6 = new SimpleDoubleProperty(col6);
        this.col7 = new SimpleDoubleProperty(col7);
        this.col8 = new SimpleDoubleProperty(col8);
        this.col9 = new SimpleDoubleProperty(col9);
        this.col10 = new SimpleDoubleProperty(col10);
        this.col11 = new SimpleDoubleProperty(col11);
        this.col12 = new SimpleDoubleProperty(col12);
        this.col13 = new SimpleDoubleProperty(col13);
        this.col14 = new SimpleDoubleProperty(col14);
        this.col15 = new SimpleDoubleProperty(col15);
        this.col16 = new SimpleDoubleProperty(col16);
        this.col17 = new SimpleDoubleProperty(col17);
        this.col18 = new SimpleDoubleProperty(col18);
        this.col19 = new SimpleDoubleProperty(col19);
        this.col20 = new SimpleDoubleProperty(col20);
        this.col21 = new SimpleDoubleProperty(col21);
    }

}
