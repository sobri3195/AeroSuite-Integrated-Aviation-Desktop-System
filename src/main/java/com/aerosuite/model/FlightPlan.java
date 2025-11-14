package com.aerosuite.model;

import java.time.LocalDateTime;

public class FlightPlan {
    private Integer id;
    private Integer pilotId;
    private Integer aircraftId;
    private String planName;
    private String departureAirport;
    private String arrivalAirport;
    private String route;
    private Integer altitude;
    private Double groundSpeed;
    private Integer windDirection;
    private Double windSpeed;
    private String etd;
    private String eta;
    private Double estimatedDuration;
    private Double taxiFuel;
    private Double tripFuel;
    private Double reserveFuel;
    private Double totalFuel;
    private String notam;
    private String weather;
    private Boolean isTemplate;
    private LocalDateTime createdDate;

    public FlightPlan() {
        this.isTemplate = false;
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

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
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

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Integer getAltitude() {
        return altitude;
    }

    public void setAltitude(Integer altitude) {
        this.altitude = altitude;
    }

    public Double getGroundSpeed() {
        return groundSpeed;
    }

    public void setGroundSpeed(Double groundSpeed) {
        this.groundSpeed = groundSpeed;
    }

    public Integer getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(Integer windDirection) {
        this.windDirection = windDirection;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getEtd() {
        return etd;
    }

    public void setEtd(String etd) {
        this.etd = etd;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public Double getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(Double estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public Double getTaxiFuel() {
        return taxiFuel;
    }

    public void setTaxiFuel(Double taxiFuel) {
        this.taxiFuel = taxiFuel;
    }

    public Double getTripFuel() {
        return tripFuel;
    }

    public void setTripFuel(Double tripFuel) {
        this.tripFuel = tripFuel;
    }

    public Double getReserveFuel() {
        return reserveFuel;
    }

    public void setReserveFuel(Double reserveFuel) {
        this.reserveFuel = reserveFuel;
    }

    public Double getTotalFuel() {
        return totalFuel;
    }

    public void setTotalFuel(Double totalFuel) {
        this.totalFuel = totalFuel;
    }

    public String getNotam() {
        return notam;
    }

    public void setNotam(String notam) {
        this.notam = notam;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public Boolean getIsTemplate() {
        return isTemplate;
    }

    public void setIsTemplate(Boolean isTemplate) {
        this.isTemplate = isTemplate;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
