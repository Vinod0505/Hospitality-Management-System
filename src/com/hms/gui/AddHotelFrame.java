package com.hms.gui;

import com.hms.doa.HotelDoa;
import com.hms.model.Hotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddHotelFrame extends JFrame {

    private JTextField txtHotelId;
    private JTextField txtHotelName;
    private JTextField txtHotelLocation;
    private JTextField txtHotelAmenities;
    private JButton btnAdd;
    
    private HotelDoa hotelDoa = new HotelDoa();

    public AddHotelFrame() {
        setTitle("Add Hotel");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Hotel ID:"));
        txtHotelId = new JTextField();
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

        btnAdd = new JButton("Add Hotel");
        add(btnAdd);

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addHotel();
            }
        });
    }

    private void addHotel() {
        try {
            int hotelId = Integer.parseInt(txtHotelId.getText());
            String hotelName = txtHotelName.getText();
            String hotelLocation = txtHotelLocation.getText();
            String hotelAmenities = txtHotelAmenities.getText();
            Hotel hotel = new Hotel(hotelId, hotelName, hotelLocation, hotelAmenities);
            hotelDoa.addHotel(hotel);
            JOptionPane.showMessageDialog(this, "Hotel added successfully!");
            dispose();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error adding hotel: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid ID format.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
