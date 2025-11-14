package com.aerosuite.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class LogbookEntry {
    private Integer id;
    private Integer pilotId;
    private Integer aircraftId;
    private LocalDate flightDate;
    private String departureAirport;
    private String arrivalAirport;
    private Double picTime;
    private Double sicTime;
    private Double dualTime;
    private Double soloTime;
    private Double nightTime;
    private Double crossCountryTime;
    private Double instrumentTime;
    private Double totalTime;
    private Integer landingsDay;
    private Integer landingsNight;
    private String remarks;
    private LocalDateTime createdDate;
    
    private String pilotName;
    private String aircraftRegistration;

    public LogbookEntry() {
        this.picTime = 0.0;
        this.sicTime = 0.0;
        this.dualTime = 0.0;
        this.soloTime = 0.0;
        this.nightTime = 0.0;
        this.crossCountryTime = 0.0;
        this.instrumentTime = 0.0;
        this.totalTime = 0.0;
        this.landingsDay = 0;
        this.landingsNight = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPilotId() {
        return pilotId;
    }

    public void setPilotId(Integer pilotId) {
        this.pilotId = pilotId;
    }

    public Integer getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId(Integer aircraftId) {
        this.aircraftId = aircraftId;
    }

    public LocalDate getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(LocalDate flightDate) {
        this.flightDate = flightDate;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public Double getPicTime() {
        return picTime;
    }

    public void setPicTime(Double picTime) {
        this.picTime = picTime;
    }

    public Double getSicTime() {
        return sicTime;
    }

    public void setSicTime(Double sicTime) {
        this.sicTime = sicTime;
    }

    public Double getDualTime() {
        return dualTime;
    }

    public void setDualTime(Double dualTime) {
        this.dualTime = dualTime;
    }

    public Double getSoloTime() {
        return soloTime;
    }

    public void setSoloTime(Double soloTime) {
        this.soloTime = soloTime;
    }

    public Double getNightTime() {
        return nightTime;
    }

    public void setNightTime(Double nightTime) {
        this.nightTime = nightTime;
    }

    public Double getCrossCountryTime() {
        return crossCountryTime;
    }

    public void setCrossCountryTime(Double crossCountryTime) {
        this.crossCountryTime = crossCountryTime;
    }

    public Double getInstrumentTime() {
        return instrumentTime;
    }

    public void setInstrumentTime(Double instrumentTime) {
        this.instrumentTime = instrumentTime;
    }

    public Double getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Double totalTime) {
        this.totalTime = totalTime;
    }

    public Integer getLandingsDay() {
        return landingsDay;
    }

    public void setLandingsDay(Integer landingsDay) {
        this.landingsDay = landingsDay;
    }

    public Integer getLandingsNight() {
        return landingsNight;
    }

    public void setLandingsNight(Integer landingsNight) {
        this.landingsNight = landingsNight;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getPilotName() {
        return pilotName;
    }

    public void setPilotName(String pilotName) {
        this.pilotName = pilotName;
    }

    public String getAircraftRegistration() {
        return aircraftRegistration;
    }

    public void setAircraftRegistration(String aircraftRegistration) {
        this.aircraftRegistration = aircraftRegistration;
    }
}
