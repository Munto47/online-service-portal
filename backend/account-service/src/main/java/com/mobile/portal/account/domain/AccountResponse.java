package com.mobile.portal.account.domain;

import java.time.Instant;

public record AccountResponse(
        String id,
        String customerName,
        String phoneNumber,
        String planType,
        Instant createdAt
) {
}
