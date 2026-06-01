package com.kauabiscotto.HotelSystem.dao;

import com.kauabiscotto.HotelSystem.Guest;
import com.kauabiscotto.HotelSystem.Reservation;
import com.kauabiscotto.HotelSystem.Room;
import com.kauabiscotto.HotelSystem.factory.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {

    public void save(Reservation reservation) throws Exception {

        String sql = "INSERT INTO reservations (guest_id, room_id, entry_date, departure_date, total_value) VALUES (?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, reservation.getGuest().getId());
            pstmt.setInt(2, reservation.getRoom().getId());
            pstmt.setDate(3, Date.valueOf(reservation.getEntryDate()));
            pstmt.setDate(4, Date.valueOf(reservation.getDepartureDate()));
            pstmt.setDouble(5, reservation.getTotalValue());

            pstmt.executeUpdate();

            ResultSet keys = pstmt.getGeneratedKeys();
            if (keys.next()) {
                reservation.setId(keys.getInt(1));
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Reservation findById(int id) throws Exception {

        String sql = "SELECT r.*, " +
                "g.name, g.cpf, g.cellphone, g.email, " +
                "rm.type, rm.price, rm.status " +
                "FROM reservations r " +
                "JOIN guests g ON r.guest_id = g.id " +
                "JOIN rooms rm ON r.room_id = rm.id " +
                "WHERE r.id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        try {

            conn = ConnectionFactory.createConnectionToMySQL();
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);
            rset = pstmt.executeQuery();

            if (rset.next()) {
                return buildReservation(rset);
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {

                if (rset != null) {
                    rset.close();
                }

                if (pstmt != null) {
                    pstmt.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<Reservation> findAll() throws Exception {

        String sql = "SELECT r.*, " +
                "g.name, g.cpf, g.cellphone, g.email, " +
                "rm.type, rm.price, rm.status " +
                "FROM reservations r " +
                "JOIN guests g ON r.guest_id = g.id " +
                "JOIN rooms rm ON r.room_id = rm.id";

        List<Reservation> reservations = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        try {

            conn = ConnectionFactory.createConnectionToMySQL();
            pstmt = conn.prepareStatement(sql);
            rset = pstmt.executeQuery();

            while (rset.next()) {
                reservations.add(buildReservation(rset));
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {

            try {

                if (rset != null) {
                    rset.close();
                }

                if (pstmt != null) {
                    pstmt.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return reservations;
    }

    public void update(Reservation reservation) throws Exception {

        String sql = "UPDATE reservations SET guest_id = ?, room_id = ?, entry_date = ?, departure_date = ?, total_value = ? WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, reservation.getGuest().getId());
            pstmt.setInt(2, reservation.getRoom().getId());
            pstmt.setDate(3, Date.valueOf(reservation.getEntryDate()));
            pstmt.setDate(4, Date.valueOf(reservation.getDepartureDate()));
            pstmt.setDouble(5, reservation.getTotalValue());
            pstmt.setInt(6, reservation.getId());

            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {

            try {

                if (pstmt != null) {
                    pstmt.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(int id) throws Exception {

        String sql = "DELETE FROM reservations WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {

            conn = ConnectionFactory.createConnectionToMySQL();
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {

            try {
                if (pstmt != null) {
                    pstmt.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Reservation buildReservation(ResultSet rset) throws SQLException {

        Guest guest = new Guest(
                rset.getString("name"),
                rset.getString("cpf"),
                rset.getString("cellphone"),
                rset.getString("email")
        );

        guest.setId(rset.getInt("guest_id"));

        Room room = new Room(
                rset.getInt("room_id"),
                rset.getString("type"),
                rset.getDouble("price")
        );

        room.setStatus(rset.getString("status"));

        Reservation reservation = new Reservation(
                guest,
                room,
                rset.getDate("entry_date").toLocalDate(),
                rset.getDate("departure_date").toLocalDate()
        );
        reservation.setId(rset.getInt("id"));

        return reservation;
    }
}