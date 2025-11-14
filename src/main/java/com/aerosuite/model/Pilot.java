package com.aerosuite.model;

import java.time.LocalDateTime;

public class Pilot {
    private Integer id;
    private String name;
    private String licenseNumber;
    private String licenseType;
    private String email;
    private String phone;
    private LocalDateTime createdDate;

    public Pilot() {}

    public Pilot(String name, String licenseNumber, String licenseType) {
        this.name = name;
        this.licenseNumber = licenseNumber;
        this.licenseType = licenseType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return name + (licenseNumber != null ? " (" + licenseNumber + ")" : "");
    }
}
