package com.aerosuite.util;

import com.aerosuite.dao.AircraftDAO;
import com.aerosuite.dao.LogbookDAO;
import com.aerosuite.dao.PilotDAO;
import com.aerosuite.model.Aircraft;
import com.aerosuite.model.LogbookEntry;
import com.aerosuite.model.Pilot;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class DummyDataGenerator {
    
    public static void generateDummyData() {
        try {
            PilotDAO pilotDAO = new PilotDAO();
            AircraftDAO aircraftDAO = new AircraftDAO();
            LogbookDAO logbookDAO = new LogbookDAO();
            
            List<Pilot> existingPilots = pilotDAO.findAll();
            if (!existingPilots.isEmpty()) {
                System.out.println("Dummy data already exists. Skipping generation.");
                return;
            }
            
            Pilot pilot1 = new Pilot("Captain John Smith", "ATP-12345", "ATPL");
            pilot1.setEmail("john.smith@aerosuite.com");
            pilot1.setPhone("+1-555-0101");
            pilotDAO.insert(pilot1);
            
            Pilot pilot2 = new Pilot("Sarah Johnson", "CPL-67890", "CPL");
            pilot2.setEmail("sarah.j@aerosuite.com");
            pilot2.setPhone("+1-555-0102");
            pilotDAO.insert(pilot2);
            
            Pilot pilot3 = new Pilot("Mike Anderson", "PPL-11223", "PPL");
            pilot3.setEmail("mike.a@aerosuite.com");
            pilot3.setPhone("+1-555-0103");
            pilotDAO.insert(pilot3);
            
            Aircraft cessna172 = new Aircraft("N12345", "Cessna 172", "C172S");
            cessna172.setSerialNumber("172S-9876");
            cessna172.setEngineHours(1250.5);
            cessna172.setAirframeHours(2340.2);
            cessna172.setFuelCapacity(56.0);
            cessna172.setFuelConsumptionRate(9.5);
            cessna172.setMaxTakeoffWeight(2550.0);
            cessna172.setEmptyWeight(1680.0);
            cessna172.setStatus("serviceable");
            aircraftDAO.insert(cessna172);
            
            Aircraft piper28 = new Aircraft("N67890", "Piper PA-28", "PA-28-181");
            piper28.setSerialNumber("PA28-4321");
            piper28.setEngineHours(845.3);
            piper28.setAirframeHours(1523.7);
            piper28.setFuelCapacity(50.0);
            piper28.setFuelConsumptionRate(8.8);
            piper28.setMaxTakeoffWeight(2440.0);
            piper28.setEmptyWeight(1480.0);
            piper28.setStatus("serviceable");
            aircraftDAO.insert(piper28);
            
            Aircraft da40 = new Aircraft("N24680", "Diamond DA40", "DA40-NG");
            da40.setSerialNumber("DA40-5678");
            da40.setEngineHours(567.8);
            da40.setAirframeHours(890.4);
            da40.setFuelCapacity(40.0);
            da40.setFuelConsumptionRate(7.5);
            da40.setMaxTakeoffWeight(2646.0);
            da40.setEmptyWeight(1764.0);
            da40.setStatus("serviceable");
            aircraftDAO.insert(da40);
            
            LogbookEntry entry1 = new LogbookEntry();
            entry1.setPilotId(pilot1.getId());
            entry1.setAircraftId(cessna172.getId());
            entry1.setFlightDate(LocalDate.now().minusDays(10));
            entry1.setDepartureAirport("KJFK");
            entry1.setArrivalAirport("KBOS");
            entry1.setPicTime(2.5);
            entry1.setTotalTime(2.5);
            entry1.setCrossCountryTime(2.5);
            entry1.setLandingsDay(1);
            entry1.setRemarks("VFR flight, excellent weather");
            logbookDAO.insert(entry1);
            
            LogbookEntry entry2 = new LogbookEntry();
            entry2.setPilotId(pilot2.getId());
            entry2.setAircraftId(piper28.getId());
            entry2.setFlightDate(LocalDate.now().minusDays(5));
            entry2.setDepartureAirport("KLAX");
            entry2.setArrivalAirport("KSAN");
            entry2.setPicTime(1.8);
            entry2.setTotalTime(1.8);
            entry2.setCrossCountryTime(1.8);
            entry2.setLandingsDay(1);
            entry2.setRemarks("Training flight, pattern work");
            logbookDAO.insert(entry2);
            
            LogbookEntry entry3 = new LogbookEntry();
            entry3.setPilotId(pilot3.getId());
            entry3.setAircraftId(da40.getId());
            entry3.setFlightDate(LocalDate.now().minusDays(2));
            entry3.setDepartureAirport("KMIA");
            entry3.setArrivalAirport("KFLL");
            entry3.setDualTime(1.2);
            entry3.setTotalTime(1.2);
            entry3.setLandingsDay(3);
            entry3.setRemarks("Touch and go practice");
            logbookDAO.insert(entry3);
            
            LogbookEntry entry4 = new LogbookEntry();
            entry4.setPilotId(pilot1.getId());
            entry4.setAircraftId(cessna172.getId());
            entry4.setFlightDate(LocalDate.now().minusDays(1));
            entry4.setDepartureAirport("KORD");
            entry4.setArrivalAirport("KMDW");
            entry4.setPicTime(0.8);
            entry4.setNightTime(0.8);
            entry4.setTotalTime(0.8);
            entry4.setLandingsNight(1);
            entry4.setRemarks("Night currency flight");
            logbookDAO.insert(entry4);
            
            System.out.println("Dummy data generated successfully!");
            
        } catch (SQLException e) {
            System.err.println("Error generating dummy data: " + e.getMessage());
        }
    }
}
