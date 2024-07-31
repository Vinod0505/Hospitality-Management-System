package com.hms.model;

public class Room {

	private int roomId;
	private int roomNumber;
	private String roomType;
	private int roomPrice;
	private String roomStatus;
	private int hotelId;

	public Room() {}
	public Room(int roomId, int roomNumber, String roomType, int roomPrice, String roomStatus, int hotelId) {
		super();
		this.roomId = roomId;
		this.roomNumber = roomNumber;
		this.roomType = roomType;
		this.roomPrice = roomPrice;
		this.roomStatus = roomStatus;
		this.hotelId = hotelId;
	}


	public Room(int roomId2, int hotelId2, int roomNumber2, String roomType2, int roomPrice2, String roomStatus2) {
		this.roomId=roomId2;
		this.hotelId=hotelId2;
		this.roomNumber=roomNumber2;
		this.roomType=roomType2;
		this.roomPrice=roomPrice2;
		this.roomStatus=roomStatus2;
	}
	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public int getRoomPrice() {
		return roomPrice;
	}

	public void setRoomPrice(int roomPrice) {
		this.roomPrice = roomPrice;
	}

	public String getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(String roomStatus) {
		this.roomStatus = roomStatus;
	}

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	@Override
	public String toString() {
		return "Room [roomId=" + roomId + ", roomNumber=" + roomNumber + ", roomType=" + roomType + ", roomPrice="
				+ roomPrice + ", roomStatus=" + roomStatus + ", hotelId=" + hotelId + "]";
	}



}
