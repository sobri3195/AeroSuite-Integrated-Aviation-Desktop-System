package com.aerosuite.model;

import java.time.LocalDateTime;

public class Drone {
    private Integer id;
    private String droneName;
    private String model;
    private String serialNumber;
    private String registration;
    private Integer maxFlightTime;
    private Double maxSpeed;
    private String status;
    private LocalDateTime createdDate;

    public Drone() {
        this.status = "operational";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDroneName() {
        return droneName;
    }

    public void setDroneName(String droneName) {
        this.droneName = droneName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public Integer getMaxFlightTime() {
        return maxFlightTime;
    }

    public void setMaxFlightTime(Integer maxFlightTime) {
        this.maxFlightTime = maxFlightTime;
    }

    public Double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return droneName + " (" + model + ")";
    }
}
