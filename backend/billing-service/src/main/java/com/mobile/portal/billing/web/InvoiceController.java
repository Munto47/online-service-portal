package com.mobile.portal.billing.web;

import com.mobile.portal.billing.domain.Invoice;
import com.mobile.portal.billing.domain.InvoiceRequest;
import com.mobile.portal.billing.service.BillingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private final BillingService billingService;

    public InvoiceController(BillingService billingService) {
        this.billingService = billingService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice create(@Valid @RequestBody InvoiceRequest request) {
        return billingService.create(request);
    }

    @GetMapping
    public Collection<Invoice> findByPhone(@RequestParam String phoneNumber) {
        return billingService.findByPhone(phoneNumber);
    }

    @PostMapping("/{invoiceId}/pay")
    public Invoice pay(@PathVariable String invoiceId) {
        return billingService.pay(invoiceId);
    }
}
