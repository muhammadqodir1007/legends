package com.fazo.esm.service;

import com.fazo.esm.payload.AuthenticationRequest;
import com.fazo.esm.payload.AuthenticationResponse;
import com.fazo.esm.payload.RegisterRequest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
