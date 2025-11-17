package com.mobile.portal.account.service;

import com.mobile.portal.account.domain.Account;
import com.mobile.portal.account.domain.AccountRequest;
import com.mobile.portal.account.domain.AccountResponse;
import com.mobile.portal.account.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public AccountResponse create(AccountRequest request) {
        Account account = new Account(
                UUID.randomUUID().toString(),
                request.getCustomerName(),
                request.getIdNumber(),
                request.getPhoneNumber(),
                request.getPlanType(),
                Instant.now()
        );
        repository.save(account);
        return map(account);
    }

    public AccountResponse findByPhone(String phone) {
        Account account = repository.findByPhoneNumber(phone)
                .orElseThrow(() -> new IllegalArgumentException("未找到对应客户信息"));
        return map(account);
    }

    private AccountResponse map(Account account) {
        return new AccountResponse(
                account.getId(),
                account.getCustomerName(),
                account.getPhoneNumber(),
                account.getPlanType(),
                account.getCreatedAt()
        );
    }
}
