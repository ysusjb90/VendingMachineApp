package com.techelevator;

public class Chips extends Item{
    private final String VENDING_MESSAGE_CHIPS = "Crunch Crunch, Yum";

    public String getVENDING_MESSAGE_CHIPS() {
        return VENDING_MESSAGE_CHIPS;
    }

    public Chips(String slotLocation, String name, double price, String type) {
        super(slotLocation, name, price, type);
    }
}
