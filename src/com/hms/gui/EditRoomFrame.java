package com.hms.gui;

import com.hms.doa.RoomDoa;
import com.hms.model.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class EditRoomFrame extends JFrame {

    private JTextField txtRoomId;
    private JTextField txtRoomNumber;
    private JTextField txtRoomType;
    private JTextField txtRoomPrice;
    private JTextField txtRoomStatus;
    private JTextField txtHotelId;
    private JButton btnSave;
    private int roomId;

    private RoomDoa roomDoa = new RoomDoa();

    public EditRoomFrame(int roomId) {
        this.roomId = roomId;
        setTitle("Edit Room");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 10, 10));

        add(new JLabel("Room ID:"));
        txtRoomId = new JTextField();
        txtRoomId.setEditable(false);
        add(txtRoomId);

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

        add(new JLabel("Hotel ID:"));
        txtHotelId = new JTextField();
        add(txtHotelId);

        btnSave = new JButton("Save Changes");
        add(btnSave);

        loadRoomData();

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveRoom();
            }
        });
    }

    private void loadRoomData() {
        try {
            Room room = roomDoa.getRoom(roomId);
            if (room != null) {
                txtRoomId.setText(String.valueOf(room.getRoomId()));
                txtRoomNumber.setText(String.valueOf(room.getRoomNumber()));
                txtRoomType.setText(room.getRoomType());
                txtRoomPrice.setText(String.valueOf(room.getRoomPrice()));
                txtRoomStatus.setText(room.getRoomStatus());
                txtHotelId.setText(String.valueOf(room.getHotelId()));
            } else {
                JOptionPane.showMessageDialog(this, "Room not found.", "Error", JOptionPane.ERROR_MESSAGE);
                dispose();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading room data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveRoom() {
        try {
            int roomNumber = Integer.parseInt(txtRoomNumber.getText());
            String roomType = txtRoomType.getText();
            int roomPrice = Integer.parseInt(txtRoomPrice.getText());
            String roomStatus = txtRoomStatus.getText();
            int hotelId = Integer.parseInt(txtHotelId.getText());

            Room room = new Room(roomId, roomNumber, roomType, roomPrice, roomStatus, hotelId);
            roomDoa.updateRoom(room);
            JOptionPane.showMessageDialog(this, "Room updated successfully!");
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number format.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error updating room: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
