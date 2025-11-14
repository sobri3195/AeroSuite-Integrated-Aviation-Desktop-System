package com.aerosuite.dao;

import com.aerosuite.database.DatabaseManager;
import com.aerosuite.model.Aircraft;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AircraftDAO {
    private final Connection connection;

    public AircraftDAO() {
        this.connection = DatabaseManager.getInstance().getConnection();
    }

    public void insert(Aircraft aircraft) throws SQLException {
        String sql = "INSERT INTO aircraft (registration, type, model, serial_number, engine_hours, airframe_hours, " +
                "status, fuel_capacity, fuel_consumption_rate, max_takeoff_weight, empty_weight) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, aircraft.getRegistration());
            pstmt.setString(2, aircraft.getType());
            pstmt.setString(3, aircraft.getModel());
            pstmt.setString(4, aircraft.getSerialNumber());
            pstmt.setDouble(5, aircraft.getEngineHours());
            pstmt.setDouble(6, aircraft.getAirframeHours());
            pstmt.setString(7, aircraft.getStatus());
            pstmt.setObject(8, aircraft.getFuelCapacity());
            pstmt.setObject(9, aircraft.getFuelConsumptionRate());
            pstmt.setObject(10, aircraft.getMaxTakeoffWeight());
            pstmt.setObject(11, aircraft.getEmptyWeight());
            pstmt.executeUpdate();
            
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    aircraft.setId(rs.getInt(1));
                }
            }
        }
    }

    public void update(Aircraft aircraft) throws SQLException {
        String sql = "UPDATE aircraft SET registration = ?, type = ?, model = ?, serial_number = ?, " +
                "engine_hours = ?, airframe_hours = ?, status = ?, fuel_capacity = ?, fuel_consumption_rate = ?, " +
                "max_takeoff_weight = ?, empty_weight = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, aircraft.getRegistration());
            pstmt.setString(2, aircraft.getType());
            pstmt.setString(3, aircraft.getModel());
            pstmt.setString(4, aircraft.getSerialNumber());
            pstmt.setDouble(5, aircraft.getEngineHours());
            pstmt.setDouble(6, aircraft.getAirframeHours());
            pstmt.setString(7, aircraft.getStatus());
            pstmt.setObject(8, aircraft.getFuelCapacity());
            pstmt.setObject(9, aircraft.getFuelConsumptionRate());
            pstmt.setObject(10, aircraft.getMaxTakeoffWeight());
            pstmt.setObject(11, aircraft.getEmptyWeight());
            pstmt.setInt(12, aircraft.getId());
            pstmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM aircraft WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    public Aircraft findById(int id) throws SQLException {
        String sql = "SELECT * FROM aircraft WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractAircraftFromResultSet(rs);
                }
            }
        }
        return null;
    }

    public List<Aircraft> findAll() throws SQLException {
        List<Aircraft> aircraftList = new ArrayList<>();
        String sql = "SELECT * FROM aircraft ORDER BY registration";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                aircraftList.add(extractAircraftFromResultSet(rs));
            }
        }
        return aircraftList;
    }

    private Aircraft extractAircraftFromResultSet(ResultSet rs) throws SQLException {
        Aircraft aircraft = new Aircraft();
        aircraft.setId(rs.getInt("id"));
        aircraft.setRegistration(rs.getString("registration"));
        aircraft.setType(rs.getString("type"));
        aircraft.setModel(rs.getString("model"));
        aircraft.setSerialNumber(rs.getString("serial_number"));
        aircraft.setEngineHours(rs.getDouble("engine_hours"));
        aircraft.setAirframeHours(rs.getDouble("airframe_hours"));
        aircraft.setStatus(rs.getString("status"));
        
        Double fuelCapacity = rs.getDouble("fuel_capacity");
        aircraft.setFuelCapacity(rs.wasNull() ? null : fuelCapacity);
        
        Double fuelConsumptionRate = rs.getDouble("fuel_consumption_rate");
        aircraft.setFuelConsumptionRate(rs.wasNull() ? null : fuelConsumptionRate);
        
        Double maxTakeoffWeight = rs.getDouble("max_takeoff_weight");
        aircraft.setMaxTakeoffWeight(rs.wasNull() ? null : maxTakeoffWeight);
        
        Double emptyWeight = rs.getDouble("empty_weight");
        aircraft.setEmptyWeight(rs.wasNull() ? null : emptyWeight);
        
        return aircraft;
    }
}
