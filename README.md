# Hotel System 🏨

A simple hotel reservation system built in Java to practice Object-Oriented Programming (OOP) concepts such as **encapsulation**, **composition**, and **polymorphism**.

---

## 📌 Features

- **Room Management**  
- Create rooms with ID, type, price, and status.  
- Occupy and release rooms.  
- Check availability.  

- **Guest Management**  
- Store guest information (name, CPF, cellphone, email).  
- Display guest details.  

--

- **Reservation Management**  
- Create reservations linking a guest and a room.  
- Use `LocalDate` to handle entry and departure dates.  
- Calculate total stay value based on number of days × room price.  
- Confirm and cancel reservations (updates room status).  
- Display reservation details.  

---

## 🛠️ Technologies
- Java 17+  
- `java.time.LocalDate` and `ChronoUnit` for date handling  
- Object-Oriented Programming principles  

---

## 📂 Project Structure

com.kauabiscotto.HotelSystem/ │ 

├── Room.java          # Represents a hotel room 

├── Guest.java         # Represents a guest 

├── Reservation.java   # Represents a reservation 

└── Main.java



---

## ▶️ How to Run
1. Clone this repository or copy the files into your Java project.  
2. Compile the classes:  
   ```bash
   javac com/kauabiscotto/HotelSystem/*.java
   ```
3. Run the Main class:
```
java com.kauabiscotto.HotelSystem.Main
```


## 📊 Example Output

Stay value: $1000.0

=== Reservation Details ===

Guest: John Doe

CPF: 12345678900

Cellphone: 41999999999

Email: john@email.com

Room ID: 1

Room Type: Suite

Room Status: occupied

Entry Date: 2026-04-22

Departure Date: 2026-04-27

Total Value: R$1000.0

===========================

Room status after cancellation: available











