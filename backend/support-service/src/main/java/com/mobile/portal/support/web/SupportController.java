package com.mobile.portal.support.web;

import com.mobile.portal.support.domain.ComplaintRequest;
import com.mobile.portal.support.domain.RepairRequest;
import com.mobile.portal.support.domain.ServiceTicket;
import com.mobile.portal.support.service.SupportTicketService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping
public class SupportController {

    private final SupportTicketService service;

    public SupportController(SupportTicketService service) {
        this.service = service;
    }

    @PostMapping("/repairs")
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceTicket createRepair(@Valid @RequestBody RepairRequest request) {
        return service.createRepair(request);
    }

    @PostMapping("/complaints")
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceTicket createComplaint(@Valid @RequestBody ComplaintRequest request) {
        return service.createComplaint(request);
    }

    @GetMapping("/tickets")
    public Collection<ServiceTicket> list(@RequestParam String phoneNumber) {
        return service.findByPhone(phoneNumber);
    }
}
