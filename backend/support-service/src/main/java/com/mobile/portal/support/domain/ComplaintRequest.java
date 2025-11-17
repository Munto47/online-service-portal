package com.mobile.portal.support.domain;

import jakarta.validation.constraints.NotBlank;

public class ComplaintRequest {

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String category;

    @NotBlank
    private String detail;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
