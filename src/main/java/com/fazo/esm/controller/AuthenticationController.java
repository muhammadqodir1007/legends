package com.fazo.esm.controller;

import com.fazo.esm.payload.AuthenticationRequest;
import com.fazo.esm.payload.AuthenticationResponse;
import com.fazo.esm.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@Slf4j
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        log.info("Received authentication request: {}", request);
        ResponseEntity<AuthenticationResponse> response = ResponseEntity.ok(service.authenticate(request));
        log.info("Authentication response: {}", response);
        return response;
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        log.info("Received request to refresh token.");
        service.refreshToken(request, response);
        log.info("Token refreshed successfully.");
    }
}
