package com.techelevator.view;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class ProductsTest {

    @Test
    public void test_quantity_is_accurate() {
        Products obj = new Products("a1", "snickers",
                new BigDecimal("1.00"), "candy");
        int expectedQuantity = 5;
        int actualQuantity = obj.getQuantity();
        Assert.assertEquals(expectedQuantity, actualQuantity);
    }

    @Test
    public void test_is_sold_out() {
        Products obj = new Products("a1", "snickers",
                new BigDecimal("1.00"), "candy");
        obj.setQuantity(0);
        boolean expectedSoldOut = true;
        boolean actualSoldOut = obj.isSoldOut();
        Assert.assertEquals(expectedSoldOut, actualSoldOut);

        obj.setQuantity(2);
        boolean expectedSoldOut2 = false;
        boolean actualSoldOut2 = !obj.isSoldOut();
        Assert.assertEquals(expectedSoldOut2, actualSoldOut2);
    }

    @Test
    public void get_type_of_product() {
        Products testType = new Products("D2", "Little",
                new BigDecimal("0.95"), "Gum");

        String expectedType = "Gum";
        String actualType = testType.getType();
        Assert.assertEquals(expectedType, actualType);
    }

    @Test
    public void get_msg_from_type() {

        Products obj = new Products("a1", "snickers",
                new BigDecimal("1.00"), "candy");
        String expectedMsg = null;
        String actualMsg = obj.dispenseMsg();
        Assert.assertEquals(expectedMsg, actualMsg);
    }

    @Test
    public void total_amount_sold_test(){
        Products obj = new Products("a1","testchip",new BigDecimal("1.00"),"Chip");
        obj.incrementAmountSold();
        Assert.assertEquals(1,obj.getTotalAmountSold());
    }

}