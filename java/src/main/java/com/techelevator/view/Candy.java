package com.techelevator.view;

import java.math.BigDecimal;

public class Candy extends Products{
    private String message = "Munch Munch, Yum!";

    @Override
    public String dispenseMsg(){

        return this.message;
    }

    public Candy(String slot, String name, BigDecimal price, String type) {
        super(slot, name, price, type);
    }


}
