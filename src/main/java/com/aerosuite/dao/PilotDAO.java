package com.aerosuite.dao;

import com.aerosuite.database.DatabaseManager;
import com.aerosuite.model.Pilot;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PilotDAO {
    private final Connection connection;

    public PilotDAO() {
        this.connection = DatabaseManager.getInstance().getConnection();
    }

    public void insert(Pilot pilot) throws SQLException {
        String sql = "INSERT INTO pilots (name, license_number, license_type, email, phone) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, pilot.getName());
            pstmt.setString(2, pilot.getLicenseNumber());
            pstmt.setString(3, pilot.getLicenseType());
            pstmt.setString(4, pilot.getEmail());
            pstmt.setString(5, pilot.getPhone());
            pstmt.executeUpdate();
            
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    pilot.setId(rs.getInt(1));
                }
            }
        }
    }

    public void update(Pilot pilot) throws SQLException {
        String sql = "UPDATE pilots SET name = ?, license_number = ?, license_type = ?, email = ?, phone = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, pilot.getName());
            pstmt.setString(2, pilot.getLicenseNumber());
            pstmt.setString(3, pilot.getLicenseType());
            pstmt.setString(4, pilot.getEmail());
            pstmt.setString(5, pilot.getPhone());
            pstmt.setInt(6, pilot.getId());
            pstmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM pilots WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    public Pilot findById(int id) throws SQLException {
        String sql = "SELECT * FROM pilots WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractPilotFromResultSet(rs);
                }
            }
        }
        return null;
    }

    public List<Pilot> findAll() throws SQLException {
        List<Pilot> pilots = new ArrayList<>();
        String sql = "SELECT * FROM pilots ORDER BY name";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                pilots.add(extractPilotFromResultSet(rs));
            }
        }
        return pilots;
    }

    private Pilot extractPilotFromResultSet(ResultSet rs) throws SQLException {
        Pilot pilot = new Pilot();
        pilot.setId(rs.getInt("id"));
        pilot.setName(rs.getString("name"));
        pilot.setLicenseNumber(rs.getString("license_number"));
        pilot.setLicenseType(rs.getString("license_type"));
        pilot.setEmail(rs.getString("email"));
        pilot.setPhone(rs.getString("phone"));
        return pilot;
    }
}
