package com.aerosuite.model;

import java.time.LocalDateTime;

public class Aircraft {
    private Integer id;
    private String registration;
    private String type;
    private String model;
    private String serialNumber;
    private Double engineHours;
    private Double airframeHours;
    private String status;
    private Double fuelCapacity;
    private Double fuelConsumptionRate;
    private Double maxTakeoffWeight;
    private Double emptyWeight;
    private LocalDateTime createdDate;

    public Aircraft() {}

    public Aircraft(String registration, String type, String model) {
        this.registration = registration;
        this.type = type;
        this.model = model;
        this.engineHours = 0.0;
        this.airframeHours = 0.0;
        this.status = "serviceable";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Double getEngineHours() {
        return engineHours;
    }

    public void setEngineHours(Double engineHours) {
        this.engineHours = engineHours;
    }

    public Double getAirframeHours() {
        return airframeHours;
    }

    public void setAirframeHours(Double airframeHours) {
        this.airframeHours = airframeHours;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(Double fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public Double getFuelConsumptionRate() {
        return fuelConsumptionRate;
    }

    public void setFuelConsumptionRate(Double fuelConsumptionRate) {
        this.fuelConsumptionRate = fuelConsumptionRate;
    }

    public Double getMaxTakeoffWeight() {
        return maxTakeoffWeight;
    }

    public void setMaxTakeoffWeight(Double maxTakeoffWeight) {
        this.maxTakeoffWeight = maxTakeoffWeight;
    }

    public Double getEmptyWeight() {
        return emptyWeight;
    }

    public void setEmptyWeight(Double emptyWeight) {
        this.emptyWeight = emptyWeight;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return registration + " - " + type;
    }
}
