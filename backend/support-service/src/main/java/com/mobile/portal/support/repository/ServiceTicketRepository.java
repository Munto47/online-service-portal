package com.mobile.portal.support.repository;

import com.mobile.portal.support.domain.ServiceTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ServiceTicketRepository extends JpaRepository<ServiceTicket, String> {
    Collection<ServiceTicket> findByPhoneNumber(String phoneNumber);
}
