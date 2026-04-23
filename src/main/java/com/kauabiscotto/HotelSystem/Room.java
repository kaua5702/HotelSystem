package com.kauabiscotto.HotelSystem;

import javax.swing.plaf.PanelUI;

public class Room {

    private int id;
    private String type;
    private double price;
    private String status;


    public Room(int id, String type, double price) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.status = "available";
    }


    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }


    public boolean checkAvailability() {
        if (status.equals("available")) {
            return true;
        } else {
            return false;
        }
    }

    public double calculateDailyPrice() {
        double dailyPrice = price;
        return dailyPrice;
    }

    public void occupyRoom() {
        status = "occupied";
    }

    public void releaseRoom() {
        status = "available";
    }
}
