package com.techelevator;

import com.techelevator.view.Menu;
import com.techelevator.view.ItemInventory;

import java.math.BigDecimal;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String EXIT_MAIN_MENU = "Exit";
	private static final String HIDDEN_MENU_OPTION = "Sales Report";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, EXIT_MAIN_MENU,
														HIDDEN_MENU_OPTION};

	private static final String[] PURCHASE_MENU_OPTIONS = {"Feed Money" , "Select Product", "Finish Transaction", "Back"};
	private static final String[] MONEY_OPTIONS = {"$1", "$2", "$5", "$10", "Back"};



	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	ItemInventory itemInventoryItems = new ItemInventory();

	public void run() {
		itemInventoryItems.initializeItems("vendingmachine.csv");
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			//System.out.println("The choice selected from the 1st level is: " + choice);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				System.out.print(itemInventoryItems.printAllItems());
				// display vending machine items
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
				handlePurchaseOptions();

			} else if (choice.equals(EXIT_MAIN_MENU)) {
				System.exit(0);

			} //hidden options
			else if(choice.equals(HIDDEN_MENU_OPTION)){
				System.out.println("SALES-REPORT::");
				itemInventoryItems.runSalesReport();
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
	public void handlePurchaseOptions() {
		System.out.println("Total Money: $" + itemInventoryItems.getTotalMoney());
		boolean stay = true;

		while(stay) {

			String choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

			if(choice.equals("Feed Money")) {
				System.out.println("Feeding Money...");
				handleFeedMoney();
				System.out.println("Current Money Provided: $" + itemInventoryItems.getTotalMoney());
			} else if(choice.equals("Select Product")) {
				System.out.println("Selecting Products...");
				//pass in a method to select an object and do stuff with it
				System.out.print(itemInventoryItems.printAllItems());
				System.out.print("> ");
				Scanner scanner = new Scanner(System.in);
				String input = scanner.nextLine();
				//pass input to method in vending'
				itemInventoryItems.itemSelection(input.toUpperCase());

			} else if(choice.equals("Finish Transaction")) {
				itemInventoryItems.dispenseChange();
				stay = false;
			} else if (choice.equals("Back")){
				stay = false;
			}

		}
	}


	public void handleFeedMoney() {
		boolean stay = true;
		String action = "FEED MONEY";
		while(stay) {
			String choice = (String) menu.getChoiceFromOptions(MONEY_OPTIONS);
			if(choice.equals("$1")) {
				BigDecimal value = new BigDecimal("1.00");
				itemInventoryItems.addToLog(action,value);
				itemInventoryItems.deposit(value);
			} else if (choice.equals("$2")) {
				BigDecimal value = new BigDecimal("2.00");
				itemInventoryItems.addToLog(action,value);
				itemInventoryItems.deposit(value);
			}else if (choice.equals("$5")) {
				BigDecimal value = new BigDecimal("5.00");
				itemInventoryItems.addToLog(action,value);
				itemInventoryItems.deposit(value);
			}else if(choice.equals("$10")) {
				BigDecimal value = new BigDecimal("10.00");
				itemInventoryItems.addToLog(action,value);
				itemInventoryItems.deposit(value);
			}else if(choice.equals("Back")) {
				stay = false;
			}
		}
	}
}
