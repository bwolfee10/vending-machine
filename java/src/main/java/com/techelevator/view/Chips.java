package com.techelevator.view;

import java.math.BigDecimal;

public class Chips extends Products{

    private String message = "Crunch Crunch, Yum!";

    @Override
    public String dispenseMsg(){

        return this.message;
    }

    public Chips(String slot, String name, BigDecimal price, String type) {
        super(slot, name, price, type);
    }


}
