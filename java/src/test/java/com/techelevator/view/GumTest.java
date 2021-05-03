package com.techelevator.view;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class GumTest {

    @Test
    public  void expected_msg_from_Gum_dispensed() {

        Gum gumTest = new Gum("D2", "Little", new BigDecimal("0.95"),"Gum");

        Assert.assertEquals("Chew Chew, Yum!", gumTest.dispenseMsg());
    }

}