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
    public void loadInventory(Vend vend){
            Map<String, List<Item>> result = new HashMap<>();
            List<Item> slotNumberList;
            try{
                File dataFile = new File(INVENTORY_LIST);
                try(Scanner fileScanner = new Scanner(dataFile)){
                    while(fileScanner.hasNext()){
                        String[] data = fileScanner.nextLine().split("\\|");
                        String slotNumber = data[0];
                        int count = MAX_SLOT_NUMBER;
                        slotNumberList = new ArrayList<>();
                        for(int i = 0; i < count; i++){
                            slotNumberList.add(new Item(data[0], data[1], Double.parseDouble(data[3]), data[4]));
                        }result.put(slotNumber, slotNumberList);
                    }
                }
            }catch (FileNotFoundException e) {
                throw new RuntimeException(e); //TODO throw error message update logs
            }
        }

} //TODO balance updates and change return
//public void callItem (Item newItem){

//}
//
