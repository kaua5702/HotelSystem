package com.kauabiscotto.HotelSystem.dao;

import com.kauabiscotto.HotelSystem.Guest;
import com.kauabiscotto.HotelSystem.factory.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GuestDAO {

    public void save(Guest guest) throws Exception {

        String sql = "INSERT INTO guests(name, cpf, cellphone, email) VALUES(?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {

            conn = ConnectionFactory.createConnectionToMySQL();
            pstmt = conn.prepareStatement(sql,  Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, guest.getName());
            pstmt.setString(2, guest.getCpf());
            pstmt.setString(3, guest.getCellphone());
            pstmt.setString(4, guest.getEmail());

            pstmt.executeUpdate();

            ResultSet keys = pstmt.getGeneratedKeys();
            if (keys.next()) {
                guest.setId(keys.getInt(1));}

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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Guest findById(int id) throws Exception {
        String sql = "SELECT * FROM guests WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                Guest guest = new Guest(
                        rs.getString("name"),
                        rs.getString("cpf"),
                        rs.getString("cellphone"),
                        rs.getString("email")
                );
                guest.setId(rs.getInt("id"));
                return guest;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (pstmt != null) {
                    pstmt.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<Guest> findAll() throws SQLException {
        String sql = "SELECT * FROM guests";
        List<Guest> guests = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            conn = ConnectionFactory.createConnectionToMySQL();
            pstmt = conn.prepareStatement(sql);
            ResultSet rset = pstmt.executeQuery();

            while (rset.next()) {
                Guest guest = new Guest(
                        rs.getString("name"),
                        rs.getString("cpf"),
                        rs.getString("cellphone"),
                        rs.getString("email")
                );

                guest.setId(rs.getInt("id"));
                guests.add(guest);

            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (rs != null) {
                    rs.close();
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
        return guests;
    }

    public void update(Guest guest) throws Exception {

        String sql = "UPDATE guests SET name = ?, cpf = ?, cellphone = ?, email = ? WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, guest.getName());
            pstmt.setString(2, guest.getCpf());
            pstmt.setString(3, guest.getCellphone());
            pstmt.setString(4, guest.getEmail());
            pstmt.setInt(5, guest.getId());

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
        String sql = "DELETE FROM guests WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();

        }  finally {
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

}
