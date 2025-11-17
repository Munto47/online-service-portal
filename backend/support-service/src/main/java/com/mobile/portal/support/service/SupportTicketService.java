package com.mobile.portal.support.service;

import com.mobile.portal.support.client.AccountClient;
import com.mobile.portal.support.domain.ComplaintRequest;
import com.mobile.portal.support.domain.RepairRequest;
import com.mobile.portal.support.domain.ServiceTicket;
import com.mobile.portal.support.domain.TicketType;
import com.mobile.portal.support.repository.ServiceTicketRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

@Service
public class SupportTicketService {

    private final AccountClient accountClient;
    private final ServiceTicketRepository repository;

    public SupportTicketService(AccountClient accountClient, ServiceTicketRepository repository) {
        this.accountClient = accountClient;
        this.repository = repository;
    }

    public ServiceTicket createRepair(RepairRequest request) {
        validateAccount(request.getPhoneNumber());
        ServiceTicket ticket = new ServiceTicket(
                UUID.randomUUID().toString(),
                request.getPhoneNumber(),
                TicketType.REPAIR,
                request.getDeviceType() + ":" + request.getDescription(),
                "处理中",
                Instant.now()
        );
        return repository.save(ticket);
    }

    public ServiceTicket createComplaint(ComplaintRequest request) {
        validateAccount(request.getPhoneNumber());
        ServiceTicket ticket = new ServiceTicket(
                UUID.randomUUID().toString(),
                request.getPhoneNumber(),
                TicketType.COMPLAINT,
                request.getCategory() + ":" + request.getDetail(),
                "待回复",
                Instant.now()
        );
        return repository.save(ticket);
    }

    public Collection<ServiceTicket> findByPhone(String phoneNumber) {
        return repository.findByPhoneNumber(phoneNumber);
    }

    private void validateAccount(String phoneNumber) {
        Map<?, ?> account = accountClient.fetchAccount(phoneNumber);
        if (account == null) {
            throw new IllegalArgumentException("客户尚未开户");
        }
    }
}
