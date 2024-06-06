package com.techelevator;

import com.sun.tools.javac.Main;
import org.w3c.dom.ls.LSOutput;

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
		//load physical inventory
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
					Vend inventory = new Vend();
					inventory.loadInventoryList(this.ic);
					break;
				case PURCHASE:
					this.activeMenu = PURCHASE_MENU;
					break;
				case EXIT:
					this.running = false;
				case HIDDEN_METHOD:
					// return sales report
					break;
				case FEED_MONEY:
					break;
				case SELECT_PRODUCT:
					Vend inventoryList = new Vend();
					inventoryList.loadInventory(this.vend);
					break;
				case FINISH_TRANSACTION:
					// return change
					// balance = 0
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

}