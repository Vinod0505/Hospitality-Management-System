package com.hms.model;

public class Hotel {

	private int hotelId;
	private String hotelName;
	private String hotelLocation;
	private String hotelAmenities;

	public Hotel(int hotelId, String hotelName, String hotelLocation, String hotelAmenities) {
		super();
		this.hotelId = hotelId;
		this.hotelName = hotelName;
		this.hotelLocation = hotelLocation;
		this.hotelAmenities = hotelAmenities;
	}


	public int getHotelId() {
		return hotelId;
	}
	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getHotelLocation() {
		return hotelLocation;
	}
	public void setHotelLocation(String hotelLocation) {
		this.hotelLocation = hotelLocation;
	}
	public String getHotelAmenities() {
		return hotelAmenities;
	}
	public void setHotelAmenities(String hotelAmenities) {
		this.hotelAmenities = hotelAmenities;
	}


}
