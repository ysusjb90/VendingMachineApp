package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Vend {
    //Item
    //Update Log
    //update Balance method
    //print out method for messages
    //reload method
    //money in
    //money out
    private final String INVENTORY_LIST = "vendingmachine.csv";
    private final int MAX_SLOT_NUMBER = 5;
    private double balance;
    private double displayBalance;
    private static final int NICKEL = 5;
    private static final int DIME = 10;
    private static final int QUARTER = 25;

    public String getINVENTORY_LIST() {
        return INVENTORY_LIST;
    }

    public void setDisplayBalance(double displayBalance) {
        this.displayBalance = displayBalance;
    }

    public int getMAX_SLOT_NUMBER() {
        return MAX_SLOT_NUMBER;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public double getDisplayBalance() {
        return displayBalance;
    }

    public Vend() {
        Scanner fileScanner = new Scanner(INVENTORY_LIST);



    }

    public void loadInventoryList(ItemCounter ic) {
        Map<String, Item> result = new HashMap<>();
        try {
            File dataFile = new File(INVENTORY_LIST);
            try (Scanner fileScanner = new Scanner(dataFile)) {
                while (fileScanner.hasNext()) {
                    String[] data = fileScanner.nextLine().split("\\|");
                    String slotNumber = data[0];
                    Item newItem = new Item(data[0], data[1], Double.parseDouble(data[2]), data[3]);
                    result.put(slotNumber, newItem);
                }
            }
            ic.stockList = result;
        } catch (FileNotFoundException fnf) {
            System.out.println(fnf.getCause());//TODO pass in an error message / throw?


        }
    }

    public void loadInventory(ItemCounter ic) {
        Map<String, List<Item>> result = new HashMap<>();
        List<Item> slotNumberList;
        try {
            File dataFile = new File(INVENTORY_LIST);
            try (Scanner fileScanner = new Scanner(dataFile)) {
                while (fileScanner.hasNext()) {
                    String[] data = fileScanner.nextLine().split("\\|");
                    String slotNumber = data[0];
                    int count = MAX_SLOT_NUMBER;
                    slotNumberList = new ArrayList<>();
                    for (int i = 0; i < count; i++) {
                        slotNumberList.add(new Item(data[0], data[1], Double.parseDouble(data[2]), data[3]));
                    }
                    result.put(slotNumber, slotNumberList);
                }
            } ic.inventory = result;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e); //TODO throw error message update logs
        }
    }


    public double feedMoney(double balance) {

        displayBalance += balance;

        return displayBalance;

    }
    public double endTransaction (double balance){
        if(balance> 0){
            balance = balance * 100;
        int change = (int) balance;
        int numOfQuarters = change / QUARTER;
        change = change % QUARTER;
        int numOfDimes = change / DIME;
        change = change % DIME;
        int numOfNickels = change / NICKEL;
        balance=change;
        //balance= (balance-(numOfQuarters*QUARTER)-(numOfDimes*DIME)-(numOfNickels*NICKEL))/100;

            System.out.println("Returns: " + numOfQuarters + " Quarters, " + numOfDimes + " Dimes, and " + numOfNickels + " Nickels.");
            System.out.println("balance = " + balance );
        }
        return balance;

    }

}


//TODO balance updates and change return
//public void callItem (Item newItem){

//}
//
