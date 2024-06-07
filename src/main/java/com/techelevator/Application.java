package com.techelevator;

import com.sun.tools.javac.Main;
import org.w3c.dom.ls.LSOutput;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Application {
	private static final String DISPLAY_VENDING_MACHINE_ITEMS = "Display Vending Machine Items";
	private static final String PURCHASE = "Purchase";
	private static final String EXIT = "Exit";
	private static final String HIDDEN_METHOD = "** Sales Log";
	private static final String FEED_MONEY = "Feed Money";
	private static final String SELECT_PRODUCT = "Select Product";
	private static final String FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] MAIN_MENU = {DISPLAY_VENDING_MACHINE_ITEMS,PURCHASE,EXIT,HIDDEN_METHOD};
	private static final String [] PURCHASE_MENU = {FEED_MONEY, SELECT_PRODUCT,FINISH_TRANSACTION};
	private String[] activeMenu = MAIN_MENU;
	private boolean running = false;

	public ItemCounter getIc() {
		return ic;
	}

	public Vend getVend() {
		return vend;
	}

	private ItemCounter ic;
	private Vend vend;

	public String getDISPLAY_VENDING_MACHINE_ITEMS() {
		return DISPLAY_VENDING_MACHINE_ITEMS;
	}

	public String getPURCHASE() {
		return PURCHASE;
	}

	public String getEXIT() {
		return EXIT;
	}

	public String getHIDDEN_METHOD() {
		return HIDDEN_METHOD;
	}

	public String getFEED_MONEY() {
		return FEED_MONEY;
	}

	public String getSELECT_PRODUCT() {
		return SELECT_PRODUCT;
	}

	public String getFINISH_TRANSACTION() {
		return FINISH_TRANSACTION;
	}

	public static void main(String[] args) {

		Application app = new Application();
		app.run();

	}
	public void run(){
		this.ic = new ItemCounter();
		this.vend = new Vend();
		Item purchaseItem = null;
		;

		Scanner vendingScanner = new Scanner(System.in);
		this.running=true;
		//stocklist
		vend.loadInventoryList(ic);
		//printInventoryList();
		//load physical inventory
		vend.loadInventory(ic);
		//checkInventory();


		//if (purchaseItem != null){
		//	System.out.println("You bought a " + purchaseItem.toString());
		//	purchaseItem = null;
		//}else{
		//	System.out.println("Sold out!");
		//}


		while (running){

			displayMenu();

			System.out.println("Please select an option");

			Integer userInput =0;

			try{
				userInput=Integer.parseInt(vendingScanner.nextLine());
			}catch (NumberFormatException nfe){
				userInput = 0;
			}
			String chosenInput = activeMenu[userInput-1];

			switch (chosenInput){
				case DISPLAY_VENDING_MACHINE_ITEMS:
					printInventoryList();
					//Vend inventory = new Vend();
					//inventory.loadInventoryList(this.ic);
					break;
				case PURCHASE:
					this.activeMenu = PURCHASE_MENU;
					break;
				case EXIT:
					this.running = false;
					break;
				case HIDDEN_METHOD:
					// return sales report
					break;
				case FEED_MONEY:
					System.out.println("How much would you like to deposit?");

					String moneyIn = vendingScanner.nextLine();
					int usersMoneyIn = Integer.parseInt(moneyIn);
					Vend feedMoney = new Vend();

					vend.feedMoney(usersMoneyIn);
					break;

				case SELECT_PRODUCT:
					//printInventoryList();
					checkInventory();
					System.out.println("Enter slot ID for desired item: ");
					String itemLocation = vendingScanner.nextLine();
					purchaseItem = ic.purchase(itemLocation);
					//purchaseItem = ic.purchase(itemLocation);

					ItemCounter dispense = new ItemCounter();
					dispense.purchase(itemLocation);
					//System.out.println("You purchased a "+ purchaseItem.toString());

					if (purchaseItem != null){
						System.out.println("You purchased a " + purchaseItem.toString());
						purchaseItem = null;
					}else{
						System.out.println("Sold out!");
					}
					break;
				case FINISH_TRANSACTION:

					int change = this.vend.getBalance();

					Vend getChange = new Vend();
					getChange.endTransaction(change);
					this.activeMenu = MAIN_MENU;
					break;
			}

		}

	}
	private void displayMenu() {
		for (int i = 0; i < activeMenu.length; i++) {
			String menuOptionNumber = (i + 1) + ") ";
			if (!activeMenu[i].startsWith("**")) {
				System.out.println(menuOptionNumber + activeMenu[i]);
			}
		} System.out.println("\n");
	}
	private void printInventoryList(){//TODO order the list
		for(Map.Entry<String, Item> stockItem : ic.stockList.entrySet()){
			System.out.println(stockItem.getKey() + ": " + stockItem.getValue().toString());
		}
	}
	private void checkInventory(){
		for(Map.Entry<String, List<Item>> slotItems : ic.inventory.entrySet()){
			String itemName = ic.stockList.get(slotItems.getKey()).getName();
			System.out.println("Slot (" + slotItems.getKey() + ") has " + slotItems.getValue().size() + " " + itemName);
		}
	}

}
