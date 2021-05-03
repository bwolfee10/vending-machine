package com.techelevator.view;

import java.math.BigDecimal;

public class Gum extends Products {

    private String message = "Chew Chew, Yum!";

    @Override
    public String dispenseMsg() {

        return this.message;
    }

    public Gum(String slot, String name, BigDecimal price, String type) {
        super(slot, name, price, type);
    }


}
