package com.mobile.portal.account.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    private String id;
    @Column(nullable = false)
    private String customerName;
    @Column(nullable = false, unique = true, length = 18)
    private String idNumber;
    @Column(nullable = false, unique = true)
    private String phoneNumber;
    private String planType;
    private Instant createdAt;

    public Account() {
    }

    public Account(String id, String customerName, String idNumber, String phoneNumber, String planType, Instant createdAt) {
        this.id = id;
        this.customerName = customerName;
        this.idNumber = idNumber;
        this.phoneNumber = phoneNumber;
        this.planType = planType;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
