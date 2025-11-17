package com.mobile.portal.billing.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    private String id;
    @Column(nullable = false)
    private String phoneNumber;
    private String period;
    private BigDecimal amount;
    private String status;

    public Invoice() {
    }

    public Invoice(String id, String phoneNumber, String period, BigDecimal amount, String status) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.period = period;
        this.amount = amount;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
