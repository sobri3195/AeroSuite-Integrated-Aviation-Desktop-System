package com.aerosuite.model;

import java.time.LocalDateTime;

public class WeightBalanceConfig {
    private Integer id;
    private Integer aircraftId;
    private String configName;
    private Double pilotWeight;
    private Double copilotWeight;
    private Double passengerWeight;
    private Double baggageWeight;
    private Double fuelWeight;
    private Double totalWeight;
    private Double cgPosition;
    private Boolean isWithinLimits;
    private LocalDateTime createdDate;

    public WeightBalanceConfig() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId(Integer aircraftId) {
        this.aircraftId = aircraftId;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public Double getPilotWeight() {
        return pilotWeight;
    }

    public void setPilotWeight(Double pilotWeight) {
        this.pilotWeight = pilotWeight;
    }

    public Double getCopilotWeight() {
        return copilotWeight;
    }

    public void setCopilotWeight(Double copilotWeight) {
        this.copilotWeight = copilotWeight;
    }

    public Double getPassengerWeight() {
        return passengerWeight;
    }

    public void setPassengerWeight(Double passengerWeight) {
        this.passengerWeight = passengerWeight;
    }

    public Double getBaggageWeight() {
        return baggageWeight;
    }

    public void setBaggageWeight(Double baggageWeight) {
        this.baggageWeight = baggageWeight;
    }

    public Double getFuelWeight() {
        return fuelWeight;
    }

    public void setFuelWeight(Double fuelWeight) {
        this.fuelWeight = fuelWeight;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public Double getCgPosition() {
        return cgPosition;
    }

    public void setCgPosition(Double cgPosition) {
        this.cgPosition = cgPosition;
    }

    public Boolean getIsWithinLimits() {
        return isWithinLimits;
    }

    public void setIsWithinLimits(Boolean isWithinLimits) {
        this.isWithinLimits = isWithinLimits;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
