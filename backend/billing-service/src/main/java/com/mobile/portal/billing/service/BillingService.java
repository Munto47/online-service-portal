package com.mobile.portal.billing.service;

import com.mobile.portal.billing.domain.Invoice;
import com.mobile.portal.billing.domain.InvoiceRequest;
import com.mobile.portal.billing.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.UUID;

@Service
public class BillingService {

    private final InvoiceRepository repository;

    public BillingService(InvoiceRepository repository) {
        this.repository = repository;

        // demo data
        if (repository.count() == 0) {
            repository.save(new Invoice(UUID.randomUUID().toString(), "13800001234", "2024-10", BigDecimal.valueOf(129), "待缴"));
            repository.save(new Invoice(UUID.randomUUID().toString(), "13800001234", "2024-11", BigDecimal.valueOf(139), "待缴"));
        }
    }

    public Invoice create(InvoiceRequest request) {
        Invoice invoice = new Invoice(
                UUID.randomUUID().toString(),
                request.getPhoneNumber(),
                request.getPeriod(),
                request.getAmount(),
                "待缴"
        );
        return repository.save(invoice);
    }

    public Collection<Invoice> findByPhone(String phoneNumber) {
        return repository.findByPhoneNumber(phoneNumber);
    }

    public Invoice pay(String invoiceId) {
        Invoice invoice = repository.findById(invoiceId)
                .orElseThrow(() -> new IllegalArgumentException("账单不存在"));
        invoice.setStatus("已支付");
        repository.save(invoice);
        return invoice;
    }
}
