package com.techelevator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemCounter {
    public Map<String, Item> stockList;
    public Map<String, List<Item>> inventory;

    public  ItemCounter(){
        stockList = new HashMap<>();

        inventory = new HashMap<>();

    }
    public Item purchase(String slotLocation){
        Item result = null;
        if(this.inventory.get(slotLocation).size() > 0 ){
            result = this.inventory.get(slotLocation).remove(0);

        }
        return result;
    }
    //public Item dispense(String slotLocation){
    //    Item result = null;
//
    //    if (this.inventory.get(slotLocation))
    //        return result;
    //}
}
