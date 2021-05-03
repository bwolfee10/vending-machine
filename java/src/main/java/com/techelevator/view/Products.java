package com.techelevator.view;

import java.math.BigDecimal;

public class Products {
    private String slot;
    private String name;
    private BigDecimal price = new BigDecimal("0.00");
    private String type;
    private int quantity = 5;
    private boolean soldOut = false;
    private int totalAmountSold = 0;

    public Products(String slot, String name, BigDecimal price, String type) {
        this.slot = slot;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public boolean isSoldOut() {
        if (this.quantity == 0) {
            System.out.println("Item is sold out");
            soldOut = true;
        }
        return soldOut;
    }

    public int getTotalAmountSold() {
        return totalAmountSold;
    }

    //test that incrementer works
    public void incrementAmountSold() {
        this.totalAmountSold = this.totalAmountSold + 1;
    }

    public String dispenseMsg() {
        return null;
    }

    public String getSlot() {

        return slot;
    }

    public String getName() {

        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getType() {

        return type;
    }

    public int getQuantity() {

        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
