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
    private double balance = 0;
    private double changeNeeded = 0;
    private static final double NICKEL = .05;
    private static final double DIME = .10;
    private static final double QUARTER = .25;

    public String getINVENTORY_LIST() {
        return INVENTORY_LIST;
    }

    public int getMAX_SLOT_NUMBER() {
        return MAX_SLOT_NUMBER;
    }

    public double getBalance() {
        return balance;
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

    public void loadInventory(Vend vend) {
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
                        slotNumberList.add(new Item(data[0], data[1], Double.parseDouble(data[3]), data[4]));
                    }
                    result.put(slotNumber, slotNumberList);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e); //TODO throw error message update logs
        }
    }


    private void feedMoney(double balance) {
        balance = 0;
        Double userInput = 0.00;
        Scanner userMoney = new Scanner(String.valueOf(userInput));
        try {
            userInput = Double.parseDouble(userMoney.nextLine());
            balance += userInput;
            System.out.println(balance);
        } catch (NumberFormatException nfe) {
            userInput = 0.00;
        }
        private void endTransaction ( double balance){
            balance = changeNeeded;
            if (changeNeeded % QUARTER >= 0) {
                balance = balance - changeNeeded;
                if (changeNeeded % QUARTER < 1 && changeNeeded % QUARTER > 0) {
                    balance = balance - changeNeeded % QUARTER * QUARTER;
                }
            }
        }
    }
}


//TODO balance updates and change return
//public void callItem (Item newItem){

//}
//