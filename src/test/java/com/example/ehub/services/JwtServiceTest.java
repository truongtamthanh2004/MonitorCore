package com.example.ehub.services;

import com.example.ehub.commons.TokenType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class JwtServiceTest {

    @Mock
    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testGenerateAccessToken_Success() {
        String username = "john.doe";
        List<String> authorities = List.of("USER", "ADMIN");
        String expectedToken = "mockAccessToken";

        when(jwtService.generateAccessToken(username, authorities)).thenReturn(expectedToken);

        String actualToken = jwtService.generateAccessToken(username, authorities);

        assertEquals(expectedToken, actualToken);
        verify(jwtService, times(1)).generateAccessToken(username, authorities);
    }

    @Test
    void generateRefreshToken() {
        String username = "john.doe";
        List<String> authorities = List.of("USER", "ADMIN");
        String expectedToken = "mockAccessToken";

        when(jwtService.generateRefreshToken(username, authorities)).thenReturn(expectedToken);

        String actualToken = jwtService.generateRefreshToken(username, authorities);

        assertEquals(expectedToken, actualToken);
        verify(jwtService, times(1)).generateRefreshToken(username, authorities);
    }

    @Test
    public void testExtractUsername_Success() {
        String token = "mockToken";
        TokenType tokenType = TokenType.ACCESS_TOKEN;
        String expectedUsername = "john.doe";

        when(jwtService.extractUsername(eq(token), eq(tokenType))).thenReturn(expectedUsername);

        String actualUsername = jwtService.extractUsername(token, tokenType);

        assertEquals(expectedUsername, actualUsername);
        verify(jwtService, times(1)).extractUsername(eq(token), eq(tokenType));
    }
}
