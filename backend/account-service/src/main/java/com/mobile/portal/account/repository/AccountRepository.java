package com.mobile.portal.account.repository;

import com.mobile.portal.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {
    Optional<Account> findByPhoneNumber(String phoneNumber);
}
