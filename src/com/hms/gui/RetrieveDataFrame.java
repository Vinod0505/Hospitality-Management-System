package com.hms.gui;

import com.hms.doa.HotelDoa;
import com.hms.doa.RoomDoa;
import com.hms.doa.GuestDoa;
import com.hms.doa.ReservationDoa;
import com.hms.model.Hotel;
import com.hms.model.Room;
import com.hms.model.Guest;
import com.hms.model.Reservation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class RetrieveDataFrame extends JFrame {

    private JTable table;
    private JButton btnEdit;
    private JButton btnDelete;
    private DefaultTableModel tableModel;

    private HotelDoa hotelDoa = new HotelDoa();
    private RoomDoa roomDoa = new RoomDoa();
    private GuestDoa guestDoa = new GuestDoa();
    private ReservationDoa reservationDoa = new ReservationDoa();

    public RetrieveDataFrame() {
        setTitle("Retrieve Data");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        btnEdit = new JButton("Edit");
        btnDelete = new JButton("Delete");
        panel.add(btnEdit);
        panel.add(btnDelete);
        add(panel, BorderLayout.SOUTH);

        populateTable();

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) tableModel.getValueAt(selectedRow, 0);
                    String type = (String) tableModel.getValueAt(selectedRow, 1);
                    // Open corresponding edit frame based on the type
                    switch (type) {
                        case "Hotel":
                            new EditHotelFrame(id).setVisible(true);
                            break;
                        case "Room":
                            new EditRoomFrame(id).setVisible(true);
                            break;
                        case "Guest":
                            new EditGuestFrame(id).setVisible(true);
                            break;
                        case "Reservation":
                            new EditReservationFrame(id).setVisible(true);
                            break;
                    }
                }
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) tableModel.getValueAt(selectedRow, 0);
                    String type = (String) tableModel.getValueAt(selectedRow, 1);
                    try {
                        switch (type) {
                            case "Hotel":
                                hotelDoa.deleteHotel(id);
                                break;
                            case "Room":
                                roomDoa.deleteRoom(id);
                                break;
                            case "Guest":
                                guestDoa.deleteGuest(id);
                                break;
                            case "Reservation":
                                reservationDoa.deleteReservation(id);
                                break;
                        }
                        JOptionPane.showMessageDialog(RetrieveDataFrame.this, type + " deleted successfully!");
                        populateTable();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(RetrieveDataFrame.this, "Error deleting " + type + ": " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    private void populateTable() {
        String[] columnNames = {"ID", "Type", "Name", "Details"};
        tableModel.setColumnIdentifiers(columnNames);
        tableModel.setRowCount(0); // Clear existing rows

        try {
            // Populate table with Hotel data
            List<Hotel> hotels = hotelDoa.getAllHotels();
            for (Hotel hotel : hotels) {
                tableModel.addRow(new Object[]{hotel.getHotelId(), "Hotel", hotel.getHotelName(), hotel.getHotelLocation()});
            }

            // Populate table with Room data
            List<Room> rooms = roomDoa.getAllRooms();
            for (Room room : rooms) {
                tableModel.addRow(new Object[]{room.getRoomId(), "Room", "Room " + room.getRoomNumber(), room.getRoomType()});
            }

            // Populate table with Guest data
            List<Guest> guests = guestDoa.getAllGuests();
            for (Guest guest : guests) {
                tableModel.addRow(new Object[]{guest.getGuestId(), "Guest", guest.getGuestName(), guest.getGuestEmail()});
            }

            // Populate table with Reservation data
            List<Reservation> reservations = reservationDoa.getAllReservations();
            for (Reservation reservation : reservations) {
                tableModel.addRow(new Object[]{reservation.getReservationId(), "Reservation", "Guest ID: " + reservation.getGuestId(), "Room ID: " + reservation.getRoomId()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
