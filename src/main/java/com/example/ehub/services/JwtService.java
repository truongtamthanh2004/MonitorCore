package com.example.ehub.services;

import com.example.ehub.commons.TokenType;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public interface JwtService {

//    String generateAccessToken(long userId, String username, Collection<? extends GrantedAuthority> authorities);
    String generateAccessToken(String username, List<String> authorities);

//    String generateRefreshToken(long userId, String username, Collection<? extends GrantedAuthority> authorities);
    String generateRefreshToken(String username, List<String> authorities);

    String extractUsername(String token, TokenType type);
}
