package com.techelevator.view;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ItemInventoryTest {

    @Test
    public void test_deposit() {
        ItemInventory obj = new ItemInventory();
        BigDecimal oneDollar = new BigDecimal("1.00");
        obj.deposit(oneDollar);
        BigDecimal expected = new BigDecimal("1.00");
        Assert.assertEquals(expected, obj.getTotalMoney());
    }

    @Test
    public void test_withdraw() {
        ItemInventory obj = new ItemInventory();
        BigDecimal withdrawAmount = new BigDecimal("5.00");
        obj.deposit(BigDecimal.valueOf(10.00));
        obj.withdraw(withdrawAmount);
        BigDecimal expectedAfterWithdraw = new BigDecimal("5.00");
        Assert.assertEquals(expectedAfterWithdraw, obj.getTotalMoney());
    }

    @Test
    public void test_quantity_is_subtracted_from_itemSelection() {
        ItemInventory inventoryObj = new ItemInventory();
        Products obj = new Products("A1", "snickers",
                new BigDecimal("1.00"), "candy");

        inventoryObj.deposit(new BigDecimal("1.00"));
        inventoryObj.addProduct(obj);
        inventoryObj.itemSelection("A1");
        int actualQuantity = obj.getQuantity();
        int expectedQuantity = 4;
        Assert.assertEquals(expectedQuantity, actualQuantity);
    }

    @Test
    public void change_test() {
        //assert arrayequals
        ItemInventory obj = new ItemInventory();
        obj.deposit(new BigDecimal(".40"));
        int[] expectedArr = {1, 1, 1};
        obj.dispenseChange();
        int[] actualArr = obj.getChangeArr();
        Assert.assertArrayEquals(expectedArr, actualArr);
    }

    @Test
    public void test_print_all_items() {

        ItemInventory inventoryObj = new ItemInventory();
        Products gum = new Gum("D2", "Little League Chew",
                new BigDecimal("0.95"), "Gum");

        inventoryObj.addProduct(gum);

        String expectedTest = "D2" + " " + "Little League Chew" + " " +
                "$0.95" + " " + "Gum" + " " + "(5)\n";
        String actualTest = inventoryObj.printAllItems();

        Assert.assertEquals(expectedTest, actualTest);


    }

    @Test
    public void test_print_sold_out() {

        ItemInventory inventoryObj = new ItemInventory();
        Products gum = new Gum("D2", "Little League Chew",
                new BigDecimal("0.95"), "Gum");

        inventoryObj.addProduct(gum);
        gum.setQuantity(0);

        String expectedTest = "D2" + " " + "Little League Chew" + " " +
                "$0.95" + " " + "Gum" + " " + "SOLD OUT\n";
        String actualTest = inventoryObj.printAllItems();

        Assert.assertEquals(expectedTest, actualTest);


    }

    //test if item is sold out in item selection


    @Test
    public void test_initialization_of_vending_machine() {
        ItemInventory testObj = new ItemInventory();
        testObj.initializeItems("testvendingmachine.csv");
        boolean expected = true;
        boolean actual = testObj.getListOfProducts().size() == 4;
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void sales_report_created_test() {
        ItemInventory obj = new ItemInventory();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        File tempfile = new File(dateFormat.format(date) + "-SalesReport.txt");
        obj.runSalesReport();
        Assert.assertTrue(tempfile.exists());
    }

    @Test

    public void test_if_slot_choice_exists() {
        ItemInventory test = new ItemInventory();
        test.itemSelection("F7");

        boolean expected = false;
        boolean actual = test.isSlotTrue();

        Assert.assertEquals(expected, actual);

    }


    @Test
    public void test_if_insufficient_funds() {

        ItemInventory test = new ItemInventory();
        Products gum = new Gum("D2", "Little League Chew",
                new BigDecimal("0.95"), "Gum");


        Double test2 = Double.parseDouble(String.valueOf(gum.getPrice()));
        Double test3 = Double.parseDouble(String.valueOf(test.getTotalMoney()));
        Assert.assertTrue(test.moneyCheck(test3, test2));
    }

@Test
        public void have_sufficient_funds_test () {
            ItemInventory testObj = new ItemInventory();
            Assert.assertTrue(testObj.haveSufficientFunds(5, 10));
            Assert.assertFalse(testObj.haveSufficientFunds(10, 5));

        }

    }
