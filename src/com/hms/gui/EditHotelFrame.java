package com.hms.gui;

import com.hms.doa.HotelDoa;
import com.hms.model.Hotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class EditHotelFrame extends JFrame {

    private JTextField txtHotelId;
    private JTextField txtHotelName;
    private JTextField txtHotelLocation;
    private JTextField txtHotelAmenities;
    private JButton btnSave;
    private int hotelId;

    private HotelDoa hotelDoa = new HotelDoa();

    public EditHotelFrame(int hotelId) {
        this.hotelId = hotelId;
        setTitle("Edit Hotel");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Hotel ID:"));
        txtHotelId = new JTextField();
        txtHotelId.setEditable(false);
        add(txtHotelId);

        add(new JLabel("Hotel Name:"));
        txtHotelName = new JTextField();
        add(txtHotelName);

        add(new JLabel("Hotel Location:"));
        txtHotelLocation = new JTextField();
        add(txtHotelLocation);

        add(new JLabel("Hotel Amenities:"));
        txtHotelAmenities = new JTextField();
        add(txtHotelAmenities);

        btnSave = new JButton("Save Changes");
        add(btnSave);

        loadHotelData();

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveHotel();
            }
        });
    }

    private void loadHotelData() {
        try {
            Hotel hotel = hotelDoa.getHotel(hotelId);
            if (hotel != null) {
                txtHotelId.setText(String.valueOf(hotel.getHotelId()));
                txtHotelName.setText(hotel.getHotelName());
                txtHotelLocation.setText(hotel.getHotelLocation());
                txtHotelAmenities.setText(hotel.getHotelAmenities());
            } else {
                JOptionPane.showMessageDialog(this, "Hotel not found.", "Error", JOptionPane.ERROR_MESSAGE);
                dispose();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading hotel data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveHotel() {
        try {
            String hotelName = txtHotelName.getText();
            String hotelLocation = txtHotelLocation.getText();
            String hotelAmenities = txtHotelAmenities.getText();
            
            Hotel hotel = new Hotel(hotelId, hotelName, hotelLocation, hotelAmenities);
            hotelDoa.updateHotel(hotel);
            JOptionPane.showMessageDialog(this, "Hotel updated successfully!");
            dispose();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error updating hotel: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
