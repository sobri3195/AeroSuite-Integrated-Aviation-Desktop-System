package com.aerosuite.dao;

import com.aerosuite.database.DatabaseManager;
import com.aerosuite.model.LogbookEntry;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LogbookDAO {
    private final Connection connection;

    public LogbookDAO() {
        this.connection = DatabaseManager.getInstance().getConnection();
    }

    public void insert(LogbookEntry entry) throws SQLException {
        String sql = "INSERT INTO logbook_entries (pilot_id, aircraft_id, flight_date, departure_airport, " +
                "arrival_airport, pic_time, sic_time, dual_time, solo_time, night_time, cross_country_time, " +
                "instrument_time, total_time, landings_day, landings_night, remarks) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, entry.getPilotId());
            pstmt.setInt(2, entry.getAircraftId());
            pstmt.setString(3, entry.getFlightDate().toString());
            pstmt.setString(4, entry.getDepartureAirport());
            pstmt.setString(5, entry.getArrivalAirport());
            pstmt.setDouble(6, entry.getPicTime());
            pstmt.setDouble(7, entry.getSicTime());
            pstmt.setDouble(8, entry.getDualTime());
            pstmt.setDouble(9, entry.getSoloTime());
            pstmt.setDouble(10, entry.getNightTime());
            pstmt.setDouble(11, entry.getCrossCountryTime());
            pstmt.setDouble(12, entry.getInstrumentTime());
            pstmt.setDouble(13, entry.getTotalTime());
            pstmt.setInt(14, entry.getLandingsDay());
            pstmt.setInt(15, entry.getLandingsNight());
            pstmt.setString(16, entry.getRemarks());
            pstmt.executeUpdate();
            
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    entry.setId(rs.getInt(1));
                }
            }
        }
    }

    public List<LogbookEntry> findByPilotId(int pilotId) throws SQLException {
        List<LogbookEntry> entries = new ArrayList<>();
        String sql = "SELECT le.*, p.name as pilot_name, a.registration as aircraft_registration " +
                "FROM logbook_entries le " +
                "JOIN pilots p ON le.pilot_id = p.id " +
                "JOIN aircraft a ON le.aircraft_id = a.id " +
                "WHERE le.pilot_id = ? ORDER BY le.flight_date DESC";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, pilotId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    entries.add(extractLogbookEntryFromResultSet(rs));
                }
            }
        }
        return entries;
    }

    public List<LogbookEntry> findAll() throws SQLException {
        List<LogbookEntry> entries = new ArrayList<>();
        String sql = "SELECT le.*, p.name as pilot_name, a.registration as aircraft_registration " +
                "FROM logbook_entries le " +
                "JOIN pilots p ON le.pilot_id = p.id " +
                "JOIN aircraft a ON le.aircraft_id = a.id " +
                "ORDER BY le.flight_date DESC";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                entries.add(extractLogbookEntryFromResultSet(rs));
            }
        }
        return entries;
    }

    public double getTotalHoursByPilotId(int pilotId, String timeType) throws SQLException {
        String column = switch (timeType) {
            case "PIC" -> "pic_time";
            case "SIC" -> "sic_time";
            case "DUAL" -> "dual_time";
            case "SOLO" -> "solo_time";
            case "NIGHT" -> "night_time";
            case "XC" -> "cross_country_time";
            case "INSTRUMENT" -> "instrument_time";
            default -> "total_time";
        };
        
        String sql = "SELECT SUM(" + column + ") as total FROM logbook_entries WHERE pilot_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, pilotId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("total");
                }
            }
        }
        return 0.0;
    }

    private LogbookEntry extractLogbookEntryFromResultSet(ResultSet rs) throws SQLException {
        LogbookEntry entry = new LogbookEntry();
        entry.setId(rs.getInt("id"));
        entry.setPilotId(rs.getInt("pilot_id"));
        entry.setAircraftId(rs.getInt("aircraft_id"));
        entry.setFlightDate(LocalDate.parse(rs.getString("flight_date")));
        entry.setDepartureAirport(rs.getString("departure_airport"));
        entry.setArrivalAirport(rs.getString("arrival_airport"));
        entry.setPicTime(rs.getDouble("pic_time"));
        entry.setSicTime(rs.getDouble("sic_time"));
        entry.setDualTime(rs.getDouble("dual_time"));
        entry.setSoloTime(rs.getDouble("solo_time"));
        entry.setNightTime(rs.getDouble("night_time"));
        entry.setCrossCountryTime(rs.getDouble("cross_country_time"));
        entry.setInstrumentTime(rs.getDouble("instrument_time"));
        entry.setTotalTime(rs.getDouble("total_time"));
        entry.setLandingsDay(rs.getInt("landings_day"));
        entry.setLandingsNight(rs.getInt("landings_night"));
        entry.setRemarks(rs.getString("remarks"));
        entry.setPilotName(rs.getString("pilot_name"));
        entry.setAircraftRegistration(rs.getString("aircraft_registration"));
        return entry;
    }
}
