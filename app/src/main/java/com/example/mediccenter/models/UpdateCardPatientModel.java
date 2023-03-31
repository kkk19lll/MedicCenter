package com.example.mediccenter.models;

public class UpdateCardPatientModel {
    String firstname, lastname, middlename, bith, pol;

    public UpdateCardPatientModel(String firstname, String lastname, String middlename, String bith, String pol) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
        this.bith = bith;
        this.pol = pol;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getBith() {
        return bith;
    }

    public void setBith(String bith) {
        this.bith = bith;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }
}
