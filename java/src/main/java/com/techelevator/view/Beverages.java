package com.techelevator.view;

import java.math.BigDecimal;

public class Beverages extends Products{

    private String message = "Glug Glug, Yum!";

    @Override
    public String dispenseMsg(){

        return this.message;
    }

    public Beverages(String slot, String name, BigDecimal price, String type) {
        super(slot, name, price, type);
    }
}
