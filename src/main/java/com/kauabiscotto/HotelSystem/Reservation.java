package com.kauabiscotto.HotelSystem;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation {

    private Guest guest;
    private Room room;
    private LocalDate entryDate;
    private LocalDate departureDate;
    private double totalValue;


    public Reservation(Guest guest, Room room, LocalDate entryDate, LocalDate departureDate) {
        this.guest = guest;
        this.room = room;
        this.entryDate = entryDate;
        this.departureDate = departureDate;
    }

    public Guest getGuest() {
        return guest;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public double getTotalValue() {
        return totalValue;
    }


    public double stayValue() {
        long days = ChronoUnit.DAYS.between(entryDate, departureDate);
        totalValue = days * room.getPrice();
        return totalValue;
    }

    public void confirmReservation() {
        room.occupyRoom();
    }

    public void cancelReservation() {
        room.releaseRoom();
    }

    public void displayReservationInfo() {
        System.out.println("=== Reservation Details ===");
        System.out.println("Guest: " + guest.getName());
        System.out.println("CPF: " + guest.getCpf());
        System.out.println("Cellphone: " + guest.getCellphone());
        System.out.println("Email: " + guest.getEmail());
        System.out.println("Room ID: " + room.getId());
        System.out.println("Room Type: " + room.getType());
        System.out.println("Room Status: " + room.getStatus());
        System.out.println("Entry Date: " + entryDate);
        System.out.println("Departure Date: " + departureDate);
        System.out.println("Total Value: R$" + totalValue);
        System.out.println("===========================");
    }
}
