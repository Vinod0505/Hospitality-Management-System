package com.hms.doa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hms.db.DatabaseConnector;
import com.hms.model.Room;

public class RoomDoa {
	Connection con = null;
	PreparedStatement pstmt=null;
	Statement stmt=null;

	public void addRoom(Room room) throws SQLException {
		String query = "INSERT INTO rooms (roomId,hotel_id, roomNumber, roomType, roomPrice, roomStatus) VALUES (?, ?, ?, ?, ?,?)";
		try {
			con = DatabaseConnector.getConnection();
			pstmt = con.prepareStatement(query); 
			pstmt.setInt(1, room.getRoomId());
			pstmt.setInt(2, room.getHotelId());
			pstmt.setInt(3, room.getRoomNumber());
			pstmt.setString(4, room.getRoomType());
			pstmt.setInt(5, room.getRoomPrice());
			pstmt.setString(6, room.getRoomStatus());
			pstmt.executeUpdate();
		}finally {
			// Ensure pstmt and con are closed
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}


	public Room getRoom(int roomId) throws SQLException {
		String query = "SELECT * FROM rooms WHERE roomId = ?";
		try {
			con = DatabaseConnector.getConnection();
			pstmt = con.prepareStatement(query); 
			pstmt.setInt(1, roomId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return new Room(rs.getInt("roomId"), rs.getInt("roomNumber"),
						rs.getString("roomType"), rs.getInt("roomPrice"), rs.getString("roomStatus"),rs.getInt("hotelId"));
			}
		}finally{
			// Ensure pstmt and con are closed
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return null;
	}

	public List<Room> getAllRooms() throws SQLException {
		String query = "SELECT * FROM rooms";
		List<Room> rooms = new ArrayList<>();
		try {
			con = DatabaseConnector.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				rooms.add(new Room(rs.getInt("roomId"), rs.getInt("roomNumber"), 
						rs.getString("roomType"), rs.getInt("roomPrice"), rs.getString("roomStatus"), rs.getInt("hotelId")));
			}
		}finally {
			// Ensure pstmt and con are closed
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return rooms;
	}

	public void updateRoom(Room room) throws SQLException {
		String query = "UPDATE rooms SET hotelId = ?, roomNumber = ?, roomType = ?, roomPrice = ?,"
				+ " roomStatus = ? WHERE roomId = ?";
		try {
			con = DatabaseConnector.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, room.getHotelId());
			pstmt.setInt(2, room.getRoomNumber());
			pstmt.setString(3, room.getRoomType());
			pstmt.setDouble(4, room.getRoomPrice());
			pstmt.setString(5, room.getRoomStatus());
			pstmt.setInt(6, room.getRoomId());
			pstmt.executeUpdate();
		}finally {
			// Ensure pstmt and con are closed
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}

	}

	public void deleteRoom(int roomId) throws SQLException {
		String query = "DELETE FROM rooms WHERE roomId = ?";
		con = DatabaseConnector.getConnection();
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, roomId);
			pstmt.executeUpdate();
		}finally {
			// Ensure pstmt and con are closed
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}
}
