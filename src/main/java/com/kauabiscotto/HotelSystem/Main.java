package com.kauabiscotto.HotelSystem;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Create a guest
        Guest guest = new Guest("John Doe", "123456789", "999999999", "john@email.com");

        // Create a room
        Room room = new Room(1, "Suite", 200.0);

        // Create reservation with entry and departure dates
        LocalDate entryDate = LocalDate.of(2026, 4, 22);
        LocalDate departureDate = LocalDate.of(2026, 4, 27);

        Reservation reservation = new Reservation(guest, room, entryDate, departureDate);

        // Calculate stay value
        double value = reservation.stayValue();
        System.out.println("Stay value: $" + value);

        // Confirm reservation
        reservation.confirmReservation();

        // Display reservation info
        reservation.displayReservationInfo();

        // Cancel reservation
        reservation.cancelReservation();
        System.out.println("Room status after cancellation: " + room.getStatus());
    }
}