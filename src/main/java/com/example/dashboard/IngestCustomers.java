package com.example.dashboard;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class IngestCustomers {
    private final SimpleStringProperty offer_ID;
    private final SimpleStringProperty offer_Name;
    private final SimpleStringProperty KPI_risk;
    private final SimpleStringProperty segment;
    private final SimpleStringProperty age_band;
    private final SimpleStringProperty income_band;
    private final SimpleStringProperty primary_acc;
    private final SimpleStringProperty relationahip;
    private final SimpleStringProperty eductions;
    private final SimpleStringProperty funeral_pol;
    private final SimpleIntegerProperty customers;


    public IngestCustomers(String offer_id, String offer_name, String kpi_risk, String segment, String age_band, String income_band, String primary_acc, String relationahip, String eductions, String funeral_pol, String customers) {
        offer_ID = new SimpleStringProperty(offer_id);
        offer_Name = new SimpleStringProperty(offer_name);
        KPI_risk = new SimpleStringProperty(kpi_risk);
        this.segment = new SimpleStringProperty(segment);
        this.age_band = new SimpleStringProperty(age_band);
        this.income_band = new SimpleStringProperty(income_band);
        this.primary_acc = new SimpleStringProperty(primary_acc);
        this.relationahip = new SimpleStringProperty(relationahip);
        this.eductions = new SimpleStringProperty(eductions);
        this.funeral_pol = new SimpleStringProperty(funeral_pol);
        this.customers = new SimpleIntegerProperty(Integer.parseInt(customers));
    }

    public String getOffer_ID() {
        return offer_ID.get();
    }

    public SimpleStringProperty offer_IDProperty() {
        return offer_ID;
    }

    public void setOffer_ID(String offer_ID) {
        this.offer_ID.set(offer_ID);
    }

    public String getOffer_Name() {
        return offer_Name.get();
    }

    public SimpleStringProperty offer_NameProperty() {
        return offer_Name;
    }

    public void setOffer_Name(String offer_Name) {
        this.offer_Name.set(offer_Name);
    }

    public String getKPI_risk() {
        return KPI_risk.get();
    }

    public SimpleStringProperty KPI_riskProperty() {
        return KPI_risk;
    }

    public void setKPI_risk(String KPI_risk) {
        this.KPI_risk.set(KPI_risk);
    }

    public String getSegment() {
        return segment.get();
    }

    public SimpleStringProperty segmentProperty() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment.set(segment);
    }

    public String getAge_band() {
        return age_band.get();
    }

    public SimpleStringProperty age_bandProperty() {
        return age_band;
    }

    public void setAge_band(String age_band) {
        this.age_band.set(age_band);
    }

    public String getIncome_band() {
        return income_band.get();
    }

    public SimpleStringProperty income_bandProperty() {
        return income_band;
    }

    public void setIncome_band(String income_band) {
        this.income_band.set(income_band);
    }

    public String getPrimary_acc() {
        return primary_acc.get();
    }

    public SimpleStringProperty primary_accProperty() {
        return primary_acc;
    }

    public void setPrimary_acc(String primary_acc) {
        this.primary_acc.set(primary_acc);
    }

    public String getRelationahip() {
        return relationahip.get();
    }

    public SimpleStringProperty relationahipProperty() {
        return relationahip;
    }

    public void setRelationahip(String relationahip) {
        this.relationahip.set(relationahip);
    }

    public String getEductions() {
        return eductions.get();
    }

    public SimpleStringProperty eductionsProperty() {
        return eductions;
    }

    public void setEductions(String eductions) {
        this.eductions.set(eductions);
    }

    public String getFuneral_pol() {
        return funeral_pol.get();
    }

    public SimpleStringProperty funeral_polProperty() {
        return funeral_pol;
    }

    public void setFuneral_pol(String funeral_pol) {
        this.funeral_pol.set(funeral_pol);
    }

    public int getCustomers() {
        return customers.get();
    }

    public SimpleIntegerProperty customersProperty() {
        return customers;
    }

    public void setCustomers(int customers) {
        this.customers.set(customers);
    }
}
