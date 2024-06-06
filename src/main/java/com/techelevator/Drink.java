package com.techelevator;

public class Drink extends Item{
private String VENDING_MESSAGE_DRINK = "Glug Glug, Yum";

    public String getVENDING_MESSAGE_DRINK() {
        return VENDING_MESSAGE_DRINK;
    }

    public Drink(String slotLocation, String name, double price, String type) {
        super(slotLocation, name, price, type);
    }

}
