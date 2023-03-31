package com.example.mediccenter.models;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    int id, patient_id, order_id, catalog_id;
    String created_at, updated_at, price;
    List<Patient> patientList = new ArrayList<>();

    public Basket(int id, int patient_id, int order_id, int catalog_id, String created_at, String updated_at, String price, List<Patient> patientList) {
        this.id = id;
        this.patient_id = patient_id;
        this.order_id = order_id;
        this.catalog_id = catalog_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.price = price;
        this.patientList = patientList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getCatalog_id() {
        return catalog_id;
    }

    public void setCatalog_id(int catalog_id) {
        this.catalog_id = catalog_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<Patient> getPatientList() {
        return patientList;
    }

    public void setPatientList(List<Patient> patientList) {
        this.patientList = patientList;
    }
}

