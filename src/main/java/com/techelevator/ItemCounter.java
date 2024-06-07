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
        List<Item> items = inventory.get(slotLocation);
        if(items != null && items.size() >0 ){
            result = items.remove(0);
            inventory.put(slotLocation, items);
            //System.out.println(); TODO get the vend message

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
