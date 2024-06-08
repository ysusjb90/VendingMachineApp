package com.techelevator;

import com.sun.tools.javac.Main;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
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
			Logger transaction= new Logger();

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
					double usersMoneyIn = Double.parseDouble(moneyIn);
					vend.feedMoney(usersMoneyIn);
					System.out.println(vend.getDisplayBalance());
					String eventMoney = "FEED MONEY: $" + usersMoneyIn +" $" + vend.getDisplayBalance();
					transaction.logEvent(eventMoney);
					break;

				case SELECT_PRODUCT:
					//printInventoryList();
					checkInventory();
					System.out.println("Enter slot ID for desired item: ");

					try {
						String itemLocation = vendingScanner.nextLine().toUpperCase();

						Item purchaseItem = ic.purchase(itemLocation);

						ItemCounter dispense = new ItemCounter();
						dispense.purchase(itemLocation);
						//TODO make try catch loop for other wrong inputs


						if (purchaseItem != null) {
							if (vend.getDisplayBalance() > purchaseItem.getPrice()) {
								System.out.println("You purchased " + purchaseItem.getName() +
										" for $" + purchaseItem.getPrice());

								double newBalance = vend.getDisplayBalance() - purchaseItem.getPrice();
								vend.setDisplayBalance(newBalance);
								System.out.println("Your updated balance is: $" + vend.getDisplayBalance());


								String itemType = ic.stockList.get(itemLocation).getType();
								switch (itemType) {
									case "Chip":
										System.out.println("Crunch Crunch, Yum");
										break;
									case "Gum":
										System.out.println("Chew Chew, Yum");
										break;
									case "Drink":
										System.out.println("Glug Glug, Yum");
										break;
									case "Candy":
										System.out.println("Munch Munch, Yum");
										break;

								}
								String eventPurchase = purchaseItem.getName() + " " + purchaseItem.getSlotLocation() + " "
										+ " $" + purchaseItem.getPrice() + " $" + vend.getDisplayBalance();
								transaction.logEvent(eventPurchase);
							} else {
								System.out.println("Not enough money, please feed more money.");
							}
						} else {

							System.out.println("Sold out!");
						}
						this.activeMenu = PURCHASE_MENU;
					}
					catch (Exception e){
						System.out.println("Invalid entry. Try another slot");
						this.activeMenu=PURCHASE_MENU;
						continue;}
					break;

				case FINISH_TRANSACTION:

					double change = this.vend.getDisplayBalance();
					double endBalance = 0.00;

					//Vend getChange = new Vend();
					vend.endTransaction(change);

					this.activeMenu = MAIN_MENU;
					String eventGiveChange = "GIVE CHANGE: $" + (double) change + " $" + endBalance;
					transaction.logEvent(eventGiveChange);
					this.vend.setDisplayBalance(endBalance);
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
