package com.techelevator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemCounter {
    public Map<String, Item> stockList;
    public Map<String, List<Item>> inventory;
double balance;

    public double getBalance() {
        return balance;
    }

    public  ItemCounter(){
        stockList = new HashMap<>();

        inventory = new HashMap<>();

    }
    public Item purchase(String slotLocation){
        Item result = null;
        List<Item> items = inventory.get(slotLocation);
        if(items != null && items.size() >0 ){
            result = items.remove(0);
            inventory.put(slotLocation, items);
            Vend balanceUpdater = new Vend();
            double currentBalance = balanceUpdater.getDisplayBalance();
            currentBalance -= result.getPrice();
            balanceUpdater.setDisplayBalance(currentBalance);


            //balanceUpdater = result.getPrice();
            //int currentBalance = balanceUpdater.getBalance();
            //Item priceUpdater = new Item();
            //int cost = (int) priceUpdater.getPrice();
            //currentBalance = currentBalance - cost;

            //balanceUpdater.getDisplayBalance() = (double) currentBalance;



        }
        return result;
    }
public double moneyPay (double balance) {
        Item productPrice = new Item();
        this.balance-=productPrice.getPrice();

        return this.balance;
}


    //public Item dispense(String slotLocation){
    //    Item result = null;
//
    //    if (this.inventory.get(slotLocation))
    //        return result;
    //}
}
