package com.hms.gui;

import com.hms.doa.RoomDoa;
import com.hms.model.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddRoomFrame extends JFrame {

    private JTextField txtRoomId;
    private JTextField txtHotelId;
    private JTextField txtRoomNumber;
    private JTextField txtRoomType;
    private JTextField txtRoomPrice;
    private JTextField txtRoomStatus;
    private JButton btnAdd;

    private RoomDoa roomDoa = new RoomDoa();

    public AddRoomFrame() {
        setTitle("Add Room");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 10, 10));

        add(new JLabel("Room ID:"));
        txtRoomId = new JTextField();
        add(txtRoomId);

        add(new JLabel("Hotel ID:"));
        txtHotelId = new JTextField();
        add(txtHotelId);

        add(new JLabel("Room Number:"));
        txtRoomNumber = new JTextField();
        add(txtRoomNumber);

        add(new JLabel("Room Type:"));
        txtRoomType = new JTextField();
        add(txtRoomType);

        add(new JLabel("Room Price:"));
        txtRoomPrice = new JTextField();
        add(txtRoomPrice);

        add(new JLabel("Room Status:"));
        txtRoomStatus = new JTextField();
        add(txtRoomStatus);

        btnAdd = new JButton("Add Room");
        add(btnAdd);

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRoom();
            }
        });
    }

    private void addRoom() {
        try {
            int roomId = Integer.parseInt(txtRoomId.getText());
            int hotelId = Integer.parseInt(txtHotelId.getText());
            int roomNumber = Integer.parseInt(txtRoomNumber.getText());
            String roomType = txtRoomType.getText();
            int roomPrice = Integer.parseInt(txtRoomPrice.getText());
            String roomStatus = txtRoomStatus.getText();
            
            Room room = new Room(roomId, hotelId, roomNumber, roomType, roomPrice, roomStatus);
            roomDoa.addRoom(room);
            JOptionPane.showMessageDialog(this, "Room added successfully!");
            dispose();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error adding room: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid format for numeric fields.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
