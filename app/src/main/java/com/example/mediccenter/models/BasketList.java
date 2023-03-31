package com.example.mediccenter.models;

import java.util.ArrayList;
import java.util.List;

public class BasketList {
    List<Basket> basketList = new ArrayList<>();

    public BasketList(List<Basket> basketList) {
        this.basketList = basketList;
    }

    public List<Basket> getBasketList() {
        return basketList;
    }

    public void setBasketList(List<Basket> basketList) {
        this.basketList = basketList;
    }
}