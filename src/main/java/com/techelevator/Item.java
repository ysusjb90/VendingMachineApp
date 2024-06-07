package com.techelevator;

public class Item {
    private String slotLocation;
    private String name;
    private double price;
    private String type;
    private String message;

    public String getSlotLocation() {
        return slotLocation;
    }

    public void setSlotLocation(String slotLocation) {
        this.slotLocation = slotLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Item(String slotLocation, String name, double price, String type) {
        this.slotLocation = slotLocation;
        this.name = name;
        this.price = price;
        this.type = type;
    }
    @Override
    public String toString(){
        return "Item{" +
                "slotLocation='" + slotLocation + '\'' +
                ", name='" + name + '\'' +
                ", price= '$" + price + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
