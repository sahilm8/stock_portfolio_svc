package com.sahil.stock.portfolio.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.sahil.stock.portfolio.dto.refreshToken.RefreshTokenRequest;
import com.sahil.stock.portfolio.dto.refreshToken.RefreshTokenResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final RestTemplate restTemplate;

    @Value("${auth.api-url}")
    private String apiUrl;
    
    public RefreshTokenResponse refreshToken(String refreshToken) {
        return restTemplate
            .postForObject(
                apiUrl + "/api/v1/auth/refresh-token",
                RefreshTokenRequest.builder().refreshToken(refreshToken).build(),
                RefreshTokenResponse.class
            );
    }
}

