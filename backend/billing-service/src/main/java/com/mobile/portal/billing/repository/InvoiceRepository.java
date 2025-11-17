package com.mobile.portal.billing.repository;

import com.mobile.portal.billing.domain.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface InvoiceRepository extends JpaRepository<Invoice, String> {
    Collection<Invoice> findByPhoneNumber(String phoneNumber);
}
