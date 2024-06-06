package com.techelevator;

public class Candy extends Item{
    private String VENDING_MESSAGE_CANDY = "Munch Munch, Yum";

    public String getVENDING_MESSAGE_CANDY() {
        return VENDING_MESSAGE_CANDY;
    }

    public Candy(String slotLocation, String name, double price, String type) {
        super(slotLocation, name, price, type);
    }
}
