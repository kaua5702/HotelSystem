package com.kauabiscotto.HotelSystem.dao;

import com.kauabiscotto.HotelSystem.Room;
import com.kauabiscotto.HotelSystem.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {

    public void save(Room room) throws Exception {

        String sql = "INSERT INTO rooms (id, type, price, status) VALUES (?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {

            conn = ConnectionFactory.createConnectionToMySQL();
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, room.getId());
            pstmt.setString(2, room.getType());
            pstmt.setDouble(3, room.getPrice());
            pstmt.setString(4, room.getStatus());

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

    public Room findById(int id) throws Exception {

        String sql = "SELECT * FROM rooms WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rset = pstmt.executeQuery();

            if (rset.next()) {
                Room room = new Room(
                        rset.getInt("id"),
                        rset.getString("type"),
                        rset.getDouble("price")
                );

                room.setStatus(rset.getString("status"));
                return room;
            }
        }  catch (Exception e) {
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

            }  catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<Room> findAll() throws Exception {

        String sql = "SELECT * FROM rooms";
        List<Room> rooms = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        try {

            conn = ConnectionFactory.createConnectionToMySQL();
            pstmt = conn.prepareStatement(sql);
            rset = pstmt.executeQuery();

            while (rset.next()) {
                Room room = new Room(
                        rset.getInt("id"),
                        rset.getString("type"),
                        rset.getDouble("price")
                );
                room.setStatus(rset.getString("status"));
                rooms.add(room);
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
        return rooms;
    }

    public void update(Room room) throws Exception {

        String sql = "UPDATE rooms SET type = ?, price = ?, status = ? WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, room.getType());
            pstmt.setDouble(2, room.getPrice());
            pstmt.setString(3, room.getStatus());
            pstmt.setInt(4, room.getId());

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

        String sql = "DELETE FROM rooms WHERE id = ?";

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

            }  catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
