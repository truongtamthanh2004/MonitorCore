package com.example.ehub.services;

import com.example.ehub.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public record UserServiceDetail(UserRepository userRepository) {

    public UserDetailsService userDetailsService() {
        return userRepository::findByUsername;
    }
}
