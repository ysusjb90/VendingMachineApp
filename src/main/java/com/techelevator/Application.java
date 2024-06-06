package com.techelevator;

import com.sun.tools.javac.Main;

import java.util.Scanner;

public class Application {
	private static final String DISPLAY_VENDING_MACHINE_ITEMS = "(1) Display Vending Machine Items";
	private static final String PURCHASE = "(2) Purchase";
	private static final String EXIT = "(3) Exit";
	private static final String HIDDEN_METHOD = "** Sales Log";
	private static final String FEED_MONEY = "(1) Feed Money";
	private static final String SELECT_PRODUCT = "(2) Select Product";
	private static final String FINISH_TRANSACTION = "(3) Finish Transaction";
	private static final String[] MAIN_MENU = {DISPLAY_VENDING_MACHINE_ITEMS,PURCHASE,EXIT,HIDDEN_METHOD};
	private static final String [] PURCHASE_MENU = {FEED_MONEY, SELECT_PRODUCT,FINISH_TRANSACTION};
	private boolean running = false;

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
		Scanner vendingScanner = new Scanner(System.in);
		this.running=true;
		while (running){

			System.out.println("MAIN MENU");
			System.out.println(MAIN_MENU);
			Integer userInput =0;

			try{
				userInput=Integer.parseInt(vendingScanner.nextLine());
			}catch (NumberFormatException nfe){
				userInput = 0;
			}
			String chosenInput = activeMenu[userInput-1];

		}

	}
}
