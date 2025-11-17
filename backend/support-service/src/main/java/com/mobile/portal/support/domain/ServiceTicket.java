package com.mobile.portal.support.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "service_ticket")
public class ServiceTicket {
    @Id
    private String id;
    @Column(nullable = false)
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private TicketType type;
    private String detail;
    private String status;
    private Instant createdAt;

    public ServiceTicket() {
    }

    public ServiceTicket(String id, String phoneNumber, TicketType type, String detail, String status, Instant createdAt) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.type = type;
        this.detail = detail;
        this.status = status;
        this.createdAt = createdAt;
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

    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
