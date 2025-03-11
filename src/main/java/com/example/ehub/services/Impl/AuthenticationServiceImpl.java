package com.example.ehub.services.Impl;

import com.example.ehub.commons.UserStatus;
import com.example.ehub.commons.UserType;
import com.example.ehub.controllers.requests.RegisterRequest;
import com.example.ehub.controllers.requests.SignInRequest;
import com.example.ehub.controllers.responses.TokenResponse;
import com.example.ehub.exceptions.ForBiddenException;
import com.example.ehub.exceptions.InvalidDataException;
import com.example.ehub.models.UserEntity;
import com.example.ehub.repositories.UserRepository;
import com.example.ehub.services.AuthenticationService;
import com.example.ehub.services.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.example.ehub.commons.TokenType.REFRESH_TOKEN;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private static final Logger log = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    @Autowired
    public AuthenticationServiceImpl(JwtService jwtService,
                                     UserRepository userRepository,
                                     AuthenticationManager authenticationManager,
                                     PasswordEncoder passwordEncoder) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public TokenResponse getAccessToken(SignInRequest request) {
        log.info("Get access token");
        var user = userRepository.findByUsername(request.getUsername());
        List<String> authorities = new ArrayList<>();

        try {
//            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), passwordEncoder.encode(request.getPassword())));

            if (user == null) {
                throw new UsernameNotFoundException("User not found");
            }

            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                throw new BadCredentialsException("Invalid password");
            }

            Authentication authenticate = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticate);

            log.info("isAuthenticated = {}", authenticate.isAuthenticated());
            log.info("Authorities: {}", authenticate.getAuthorities().toString());
            authorities.add(authenticate.getAuthorities().toString());

            String accessToken = jwtService.generateAccessToken(user.getUsername(), authorities);
            String refreshToken = jwtService.generateRefreshToken(user.getUsername(), authorities);

            return new TokenResponse.Builder().accessToken(accessToken).refreshToken(refreshToken).build();

        } catch (BadCredentialsException | DisabledException e) {
            log.error("errorMessage: {}", e.getMessage());
            throw new AccessDeniedException(e.getMessage());
        }
    }

    @Override
    public TokenResponse getRefreshToken(String refreshToken) {
        log.info("Get refresh token");

        if (!StringUtils.hasLength(refreshToken)) {
            throw new InvalidDataException("Token must be not blank");
        }

        try {
            // Verify token
            String userName = jwtService.extractUsername(refreshToken, REFRESH_TOKEN);

            // check user is active or inactivated
            UserEntity user = userRepository.findByUsername(userName);

            List<String> authorities = new ArrayList<>();
            user.getAuthorities().forEach(authority -> authorities.add(authority.getAuthority()));

            // generate new access token
            String accessToken = jwtService.generateAccessToken(user.getUsername(), authorities);

            return new TokenResponse.Builder().accessToken(accessToken).refreshToken(refreshToken).build();
        } catch (Exception e) {
            log.error("Access denied! errorMessage: {}", e.getMessage());
            throw new ForBiddenException(e.getMessage());
        }
    }

    @Override
    public TokenResponse register(RegisterRequest request) {
        try {
            if (!request.getConfirmPassword().equals(request.getPassword())) {
                throw new InvalidDataException("Password and confirm password do not match");
            }

            if (userRepository.existsByUsername(request.getUsername())) {
                throw new InvalidDataException("Username already exists");
            }
            if (userRepository.existsByEmail(request.getEmail())) {
                throw new InvalidDataException("Email already exists");
            }

            UserEntity newUser = new UserEntity();
            newUser.setFirstName(request.getFirstName());
            newUser.setLastName(request.getLastName());
            newUser.setUsername(request.getUsername());
            newUser.setEmail(request.getEmail());
            newUser.setPassword(passwordEncoder.encode(request.getPassword()));
            newUser.setStatus(UserStatus.ACTIVE);
            newUser.setStatus(UserStatus.ACTIVE);
            newUser.setType(UserType.USER);

            userRepository.save(newUser);

            List<String> authorities = new ArrayList<>();
            newUser.getAuthorities().forEach(authority -> authorities.add(authority.getAuthority()));

            String accessToken = jwtService.generateAccessToken(newUser.getUsername(), authorities);
            String refreshToken = jwtService.generateRefreshToken(newUser.getUsername(), authorities);

            return new TokenResponse.Builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
        } catch (InvalidDataException e) {
            log.error("Register failed! errorMessage: {}", e.getMessage());
            throw new InvalidDataException(e.getMessage());
        }
    }
}
