package com.techelevator;

public class Gum extends Item{
    private String VENDING_MESSAGE_GUM = "Chew Chew, Yum";

    public String getVENDING_MESSAGE_GUM() {
        return VENDING_MESSAGE_GUM;
    }

    public Gum(String slotLocation, String name, int price, String type) {
        super(slotLocation, name, price, type);
    }
}
