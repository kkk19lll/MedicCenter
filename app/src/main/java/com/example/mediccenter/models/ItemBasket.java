package com.example.mediccenter.models;

public class ItemBasket {
    String textViewNameBasket, textViewPriceBasket, textViewCountPatient;

    public ItemBasket(String textViewNameBasket, String textViewPriceBasket, String textViewCountPatient) {
        this.textViewNameBasket = textViewNameBasket;
        this.textViewPriceBasket = textViewPriceBasket;
        this.textViewCountPatient = textViewCountPatient;
    }

    public String getTextViewNameBasket() {
        return textViewNameBasket;
    }

    public void setTextViewNameBasket(String textViewNameBasket) {
        this.textViewNameBasket = textViewNameBasket;
    }

    public String getTextViewPriceBasket() {
        return textViewPriceBasket;
    }

    public void setTextViewPriceBasket(String textViewPriceBasket) {
        this.textViewPriceBasket = textViewPriceBasket;
    }

    public String getTextViewCountPatient() {
        return textViewCountPatient;
    }

    public void setTextViewCountPatient(String textViewCountPatient) {
        this.textViewCountPatient = textViewCountPatient;
    }
}
