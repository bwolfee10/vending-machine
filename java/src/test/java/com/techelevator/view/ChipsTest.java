package com.techelevator.view;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ChipsTest {

@Test
    public void expected_msg_from_Chip_dispensed() {

    Chips chipsTest = new Chips("A1", "Potato Crisps", new BigDecimal("3.05"), "Chip");

    Assert.assertEquals("Crunch Crunch, Yum!", chipsTest.dispenseMsg());


}


}