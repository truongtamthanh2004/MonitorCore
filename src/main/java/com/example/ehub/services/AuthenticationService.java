package com.example.ehub.services;

import com.example.ehub.controllers.requests.RegisterRequest;
import com.example.ehub.controllers.requests.SignInRequest;
import com.example.ehub.controllers.responses.TokenResponse;
import org.antlr.v4.runtime.Token;

public interface AuthenticationService {

    TokenResponse getAccessToken(SignInRequest request);

    TokenResponse getRefreshToken(String request);

    TokenResponse register(RegisterRequest request);
}
