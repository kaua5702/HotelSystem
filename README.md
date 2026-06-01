# Hotel System 🏨

A hotel reservation system built in Java, practicing **Object-Oriented Programming (OOP)** and **database integration** with JDBC and MySQL.

---

## 📌 Features

**Room Management**
- Create rooms with ID, type, price, and status
- Occupy and release rooms
- Check availability

**Guest Management**
- Store guest information (name, CPF, cellphone, email)
- Display guest details

**Reservation Management**
- Create reservations linking a guest and a room
- Handle entry and departure dates with `LocalDate`
- Calculate total stay value based on number of days × room price
- Confirm and cancel reservations (updates room status)
- Display full reservation details

**Database Integration**
- Persist all data in a MySQL database via JDBC
- DAO pattern for `Guest`, `Room`, and `Reservation`
- Credentials managed securely via `db.properties`

---

## 🛠️ Technologies

- Java 17+
- MySQL 8+
- JDBC (mysql-connector-java 8.0.33)
- Maven
- `java.time.LocalDate` and `ChronoUnit` for date handling
- Object-Oriented Programming principles

---

## 📂 Project Structure

```
com.kauabiscotto.HotelSystem/
│
├── Main.java
├── Guest.java
├── Room.java
├── Reservation.java
│
├── factory/
│   └── ConnectionFactory.java
│
└── dao/
    ├── GuestDAO.java
    ├── RoomDAO.java
    └── ReservationDAO.java
```

---

## 🗄️ Database Setup

Run the following SQL script in MySQL Workbench (with your database selected):

```sql
CREATE TABLE rooms (
    id INT PRIMARY KEY,
    type VARCHAR(50),
    price DOUBLE,
    status VARCHAR(20)
);

CREATE TABLE guests (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    cpf VARCHAR(14),
    cellphone VARCHAR(20),
    email VARCHAR(100)
);

CREATE TABLE reservations (
    id INT PRIMARY KEY AUTO_INCREMENT,
    guest_id INT,
    room_id INT,
    entry_date DATE,
    departure_date DATE,
    total_value DOUBLE,
    FOREIGN KEY (guest_id) REFERENCES guests(id),
    FOREIGN KEY (room_id) REFERENCES rooms(id)
);
```

---

## ⚙️ Configuration

Create the file `src/main/resources/db.properties` with your database credentials:

```properties
db.url=jdbc:mysql://localhost:3306/your_database
db.user=your_user
db.password=your_password
```

> ⚠️ Add `src/main/resources/db.properties` to your `.gitignore` to avoid exposing credentials.

---

## ▶️ How to Run

1. Clone this repository:
```bash
git clone https://github.com/kaua5702/HotelSystem.git
```

2. Set up the database and configure `db.properties` as described above.

3. Run the project through your IDE (IntelliJ IDEA recommended) or via Maven:
```bash
mvn compile exec:java -Dexec.mainClass="com.kauabiscotto.HotelSystem.Main"
```

---

## 📊 Example Output

```
Guest salvo! ID gerado: 1
Room salvo! ID: 101
Reservation salva! ID gerado: 1
=== Reservation Details ===
Guest: John Doe
CPF: 123.456.789-00
Cellphone: 999999999
Email: john@email.com
Room ID: 101
Room Type: Suite
Room Status: available
Entry Date: 2026-04-22
Departure Date: 2026-04-27
Total Value: R$1000.0
===========================
```
