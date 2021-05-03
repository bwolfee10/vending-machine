package com.techelevator.view;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CandyTest {

    @Test
    public void expected_msg_from_Candy_dispensed(){

        Candy candyTest = new Candy("B1","Moonpie",new BigDecimal("1.05"), "Candy");

        Assert.assertEquals("Munch Munch, Yum!", candyTest.dispenseMsg());


    }

}