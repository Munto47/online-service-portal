package com.mobile.portal.account.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AccountRequest {

    @NotBlank
    private String customerName;

    @NotBlank
    @Size(min = 18, max = 18)
    private String idNumber;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String planType;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }
}
