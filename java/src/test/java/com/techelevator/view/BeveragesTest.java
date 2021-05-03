package com.techelevator.view;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BeveragesTest {

    @Test
    public void expected_msg_from_Beverages_dispensed() {

        Beverages beverageTest = new Beverages("C1", "Cola", new BigDecimal("1.25"), "Drink");

        Assert.assertEquals("Glug Glug, Yum!", beverageTest.dispenseMsg());


    }


}