package com.mobile.portal.account.web;

import com.mobile.portal.account.domain.AccountRequest;
import com.mobile.portal.account.domain.AccountResponse;
import com.mobile.portal.account.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponse create(@Valid @RequestBody AccountRequest request) {
        return service.create(request);
    }

    @GetMapping("/{phoneNumber}")
    public AccountResponse find(@PathVariable String phoneNumber) {
        return service.findByPhone(phoneNumber);
    }
}
