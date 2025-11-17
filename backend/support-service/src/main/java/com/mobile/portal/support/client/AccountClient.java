package com.mobile.portal.support.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class AccountClient {

    private final RestTemplate restTemplate;

    public AccountClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map<?, ?> fetchAccount(String phoneNumber) {
        try {
            ResponseEntity<Map> response = restTemplate.getForEntity("http://account-service/accounts/{phone}", Map.class, phoneNumber);
            return response.getBody();
        } catch (Exception ex) {
            return null;
        }
    }
}
