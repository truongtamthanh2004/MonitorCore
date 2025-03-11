package com.example.ehub.controllers;

import com.example.ehub.commons.UserStatus;
import com.example.ehub.controllers.requests.RegisterRequest;
import com.example.ehub.controllers.requests.SignInRequest;
import com.example.ehub.controllers.responses.TokenResponse;
import com.example.ehub.exceptions.InvalidDataException;
import com.example.ehub.models.UserEntity;
import com.example.ehub.repositories.UserRepository;
import com.example.ehub.services.AuthenticationService;
import com.example.ehub.services.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication Controller")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public TokenResponse register(@Valid @RequestBody RegisterRequest request) {
        log.info("Register request for username: {}", request.getUsername());

        return authenticationService.register(request);
    }

    @Operation(summary = "Access token", description = "Get access token and refresh token by username and password")
    @PostMapping("/access-token")
    public TokenResponse accessToken(@RequestBody SignInRequest request) {
        log.info("Access token request");

        return authenticationService.getAccessToken(request);
    }

    @Operation(summary = "Refresh token", description = "Get access token by refresh token")
    @PostMapping("/refresh-token")
    public TokenResponse refreshToken(@RequestBody String refreshToken) {
        log.info("Refresh token request");

        return authenticationService.getRefreshToken(refreshToken);
    }
}
