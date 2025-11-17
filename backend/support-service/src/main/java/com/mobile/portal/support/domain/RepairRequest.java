package com.mobile.portal.support.domain;

import jakarta.validation.constraints.NotBlank;

public class RepairRequest {

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String deviceType;

    @NotBlank
    private String description;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
