package com.techelevator.view;


import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ItemInventory {

    private List<Products> listOfProducts = new ArrayList<Products>();
    private BigDecimal totalMoney = new BigDecimal("0.00");
    private BigDecimal totalDollarSales = new BigDecimal("0.00");
    private int[] changeArr = new int[3];
    boolean slotTrue = false;

    public boolean isSlotTrue() {
        return slotTrue;
    }

    public void addProduct(Products product) {
        this.listOfProducts.add(product);
    }

    public boolean moneyCheck(Double moneyHave, Double cost){
        if(moneyHave < cost) {
            return true;
        }
        return false;
    }
    public void itemSelection(String slotChoice) {
       // boolean slotTrue = false;
        //run through list of products, if slot choice matches, pass that
        //object off to a purchase method
        for (Products obj : listOfProducts) {
            //find the object
            //remove the object.price from this.totalmoney (withdraw)
            //update the object.quantity to decrement 1
            //where we will send the object to append the list

            if (obj.getSlot().equals(slotChoice)) {
                slotTrue = true;
                addToLog(obj);

//                if (obj.isSoldOut()) {
//                    //System.out.println("Item is sold out");
//                    return;
//                }

                double withdrawTest = Double.parseDouble(String.valueOf(obj.getPrice()));
                double totalMoneyTest = Double.parseDouble(String.valueOf(totalMoney));


                if (moneyCheck(totalMoneyTest,withdrawTest)) {
                    System.out.println("Insufficient Funds");
                    return;
                }
                withdraw(obj.getPrice());
                //each time and item is selected subtract one from stock

                obj.incrementAmountSold(); //increments by 1
                setTotalDollarSales(obj.getPrice());

                if(!obj.isSoldOut() && haveSufficientFunds(withdrawTest,totalMoneyTest)) {
                    withdraw(obj.getPrice());


                    obj.incrementAmountSold(); //increments by 1
                    setTotalDollarSales(obj.getPrice());

                    obj.setQuantity(obj.getQuantity() - 1);
                    System.out.println("Received " + obj.getName() + " for $" + obj.getPrice() +
                            ". You have $" + totalMoney + " remaining.");
                    System.out.println(obj.dispenseMsg());
                }
            }
        } if (!slotTrue) {
            System.out.println("Item does not exist");
        }
    }

    public boolean haveSufficientFunds(double withdrawAmt,double totalMoney){
        if(withdrawAmt > totalMoney){
            System.out.println("Insufficient Funds");
            return false;
        }
        else{
            return true;
        }
    }


    public void setTotalDollarSales(BigDecimal totalDollarSales) {
        this.totalDollarSales = this.totalDollarSales.add(totalDollarSales);
    }

    public void addToLog(String action, BigDecimal amount) {
        //action will be either "Feed money" or "Give change"
        //action is added to log when called
        File log = new File("Log.txt");
        PrintWriter writer = null;
        String feed = "FEED MONEY";
        BigDecimal postValue = new BigDecimal("0.00");
        //BigDecimal postval = this.totalMoney.subtract(obj.getPrice());
        if (action.equals(feed)) {
            postValue = this.totalMoney.add(amount);
        }

        LocalDateTime nowTime = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatted = nowTime.format(format);

        try {
            FileOutputStream outputStream = new FileOutputStream(log, true);
            writer = new PrintWriter(outputStream);
            writer.append(formatted + " " + action + " " +
                    amount + " " + postValue); //append statement here

            writer.println();
            writer.flush();
            writer.close();

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    public void addToLog(Products obj) {

        File log = new File("Log.txt");
        PrintWriter writer = null;
        String name = obj.getName();
        String slot = obj.getSlot();

        BigDecimal before = this.totalMoney;
        BigDecimal after = this.totalMoney.subtract(obj.getPrice());

        LocalDateTime nowTime = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatted = nowTime.format(format);

        try {
            FileOutputStream outputStream = new FileOutputStream(log, true);
            writer = new PrintWriter(outputStream);
            writer.append(formatted + " " + name + " "
                    + slot + " " + before + " " + after);

            writer.println();
            writer.flush();
            writer.close();

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }


    public void dispenseChange() {
        addToLog("GIVE CHANGE", this.totalMoney);
        BigDecimal changeAsBigDecimal = new BigDecimal(String.valueOf(this.totalMoney));
        double test2 = Double.parseDouble(String.valueOf(changeAsBigDecimal));
        test2 *= 100;

        int change = (int) Double.parseDouble(String.valueOf(test2));
        int quarters = change / 25;
        this.changeArr[0] = quarters;
        if (quarters > 0) {
            change = change % 25;
        }
        int dimes = change / 10;
        this.changeArr[1] = dimes;
        if (dimes > 0) {
            change = change % 10;
        }
        int nickels = change / 5;
        this.changeArr[2] = nickels;
        if (nickels > 0) {
            change = change % 5;
        }
        System.out.println("Change: " + quarters + " quarters "
                + dimes + " dimes " + nickels + " nickels ");

        this.totalMoney = BigDecimal.valueOf(0);

    }


    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    //added at "feedMoney" in menu
    public void deposit(BigDecimal value) {
        totalMoney = totalMoney.add(value);
    }

    //removed at purchase
    public void withdraw(BigDecimal value) {
        totalMoney = totalMoney.subtract(value);
    }

    //formatted string to print items in display --> menu
    public String printAllItems() {
        String print = "";
        for (Products obj : listOfProducts) {
            print += obj.getSlot() + " " + obj.getName() + " $"
                    + obj.getPrice() + " " + obj.getType() + " ";
            if (obj.isSoldOut()) {
                print += "SOLD OUT";
            } else {
                print += "(" +  obj.getQuantity() + ")";
            }
        print += "\n";
        }
        return print;
    }
    public int[] getChangeArr() {
        return changeArr;
    }

    public List<Products> getListOfProducts() {
        return listOfProducts;
    }

    public void runSalesReport(){

        try {
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            File file = new File(dateFormat.format(date) + "-SalesReport.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            String salesReportString = "";
            for (Products obj : listOfProducts) {
                salesReportString += obj.getName() + "|" + obj.getTotalAmountSold() + "\n";
            }
            salesReportString += "TOTAL $" + this.totalDollarSales;
            writer.append(salesReportString);
            //writer.flush();
            writer.close();
            System.out.println(salesReportString);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    //set to run at beginning of application, pulls info from csv and builds product items,
    public void initializeItems(String pathname) {

        File inputFile = new File(pathname);

        try (Scanner scanner = new Scanner(inputFile)) {

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                String[] newArr = line.split("\\|");

                String slot = newArr[0];
                String name = newArr[1];
                BigDecimal price = new BigDecimal(newArr[2]);
                String type = newArr[3];
                //creates a sub type based on last element in csv/array
                switch (type) {
                    case "Chip":
                        Products item = new Chips(slot, name, price, type);
                        addProduct(item);
                        break;
                    case "Candy":
                        Products item2 = new Candy(slot, name, price, type);
                        addProduct(item2);
                        break;
                    case "Drink":
                        Products item3 = new Beverages(slot, name, price, type);
                        addProduct(item3);
                        break;
                    case "Gum":
                        Products item4 = new Gum(slot, name, price, type);
                        addProduct(item4);
                        break;
                    default:
                        break;

                }

            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        File newFile = new File("Log.txt");
        try {
            //newFile.createNewFile();
            PrintWriter writer = new PrintWriter((newFile));
            writer.flush();
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
