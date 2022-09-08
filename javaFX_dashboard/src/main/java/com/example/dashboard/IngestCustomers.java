package com.example.dashboard;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class IngestCustomers {
    private final SimpleStringProperty Offer_ID;
    //private final SimpleStringProperty Offer_Name;
    private final SimpleStringProperty KPI_risk;
    private final SimpleStringProperty Segment;
    private final SimpleStringProperty Age_bend;
    private final SimpleStringProperty Income_bend;
    private final SimpleStringProperty Primary_acc;
    private final SimpleStringProperty Relationship;
    private final SimpleStringProperty Eduction;
    private final SimpleStringProperty Funeral_pol;
    private final SimpleIntegerProperty Customers;


    public IngestCustomers(String offer_id, String kpi_risk, String segment, String age_band, String income_band, String primary_acc, String relationship, String eduction, String funeral_pol, String customers) {
        Offer_ID = new SimpleStringProperty(offer_id);
        //Offer_Name = new SimpleStringProperty(offer_name);
        KPI_risk = new SimpleStringProperty(kpi_risk);
        this.Segment = new SimpleStringProperty(segment);
        this.Age_bend = new SimpleStringProperty(age_band);
        this.Income_bend = new SimpleStringProperty(income_band);
        this.Primary_acc = new SimpleStringProperty(primary_acc);
        this.Relationship = new SimpleStringProperty(relationship);
        this.Eduction = new SimpleStringProperty(eduction);
        this.Funeral_pol = new SimpleStringProperty(funeral_pol);
        this.Customers = new SimpleIntegerProperty(Integer.parseInt(customers));
    }

    public String getOffer_ID() {
        return Offer_ID.get();
    }

    public SimpleStringProperty offer_IDProperty() {
        return Offer_ID;
    }

    public void setOffer_ID(String offer_ID) {
        this.Offer_ID.set(offer_ID);
    }

//    public String getOffer_Name() {
//        return Offer_Name.get();
//    }
//
//    public SimpleStringProperty offer_NameProperty() {
//        return Offer_Name;
//    }
//
//    public void setOffer_Name(String offer_Name) {
//        this.Offer_Name.set(offer_Name);
//    }

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
        return Segment.get();
    }

    public SimpleStringProperty segmentProperty() {
        return Segment;
    }

    public void setSegment(String segment) {
        this.Segment.set(segment);
    }

    public String getAge_bend() {
        return Age_bend.get();
    }

    public SimpleStringProperty age_bendProperty() {
        return Age_bend;
    }

    public void setAge_bend(String age_bend) {
        this.Age_bend.set(age_bend);
    }

    public String getIncome_bend() {
        return Income_bend.get();
    }

    public SimpleStringProperty income_bendProperty() {
        return Income_bend;
    }

    public void setIncome_bend(String income_bend) {
        this.Income_bend.set(income_bend);
    }

    public String getPrimary_acc() {
        return Primary_acc.get();
    }

    public SimpleStringProperty primary_accProperty() {
        return Primary_acc;
    }

    public void setPrimary_acc(String primary_acc) {
        this.Primary_acc.set(primary_acc);
    }

    public String getRelationship() {
        return Relationship.get();
    }

    public SimpleStringProperty relationshipProperty() {
        return Relationship;
    }

    public void setRelationship(String relationship) {
        this.Relationship.set(relationship);
    }

    public String getEduction() {
        return Eduction.get();
    }

    public SimpleStringProperty eductionProperty() {
        return Eduction;
    }

    public void setEduction(String eduction) {
        this.Eduction.set(eduction);
    }

    public String getFuneral_pol() {
        return Funeral_pol.get();
    }

    public SimpleStringProperty funeral_polProperty() {
        return Funeral_pol;
    }

    public void setFuneral_pol(String funeral_pol) {
        this.Funeral_pol.set(funeral_pol);
    }

    public int getCustomers() {
        return Customers.get();
    }

    public SimpleIntegerProperty customersProperty() {
        return Customers;
    }

    public void setCustomers(int customers) {
        this.Customers.set(customers);
    }
}
