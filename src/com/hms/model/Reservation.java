package com.hms.model;

import java.time.LocalDateTime;

public class Reservation {

	private int reservationId;
	private int guestId;
	private int roomId;
	private LocalDateTime checkInDate;
	private LocalDateTime checkOutDate;


	public Reservation(int reservationId, int guestId, int roomId, LocalDateTime checkInDate,
			LocalDateTime checkOutDate) {
		super();
		this.reservationId = reservationId;
		this.guestId = guestId;
		this.roomId = roomId;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
	}

	public int getReservationId() {
		return reservationId;
	}
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	public int getGuestId() {
		return guestId;
	}
	public void setGuestId(int guestId) {
		this.guestId = guestId;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public LocalDateTime getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(LocalDateTime checkInDate) {
		this.checkInDate = checkInDate;
	}
	public LocalDateTime getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(LocalDateTime checkOutDate) {
		this.checkOutDate = checkOutDate;
	}



}
