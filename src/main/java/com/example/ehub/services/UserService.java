package com.example.ehub.services;

import com.example.ehub.controllers.requests.UserCreationRequest;
import com.example.ehub.controllers.requests.UserPasswordRequest;
import com.example.ehub.controllers.requests.UserUpdateRequest;
import com.example.ehub.controllers.responses.UserPageResponse;
import com.example.ehub.controllers.responses.UserResponse;

public interface UserService {

    UserPageResponse findAll(String keyword, String sort, int page, int size);

    UserResponse findById(Long id);

    UserResponse findByUsername(String username);

    UserResponse findByEmail(String email);

    long save(UserCreationRequest req);

    void update(UserUpdateRequest req);

    void changePassword(UserPasswordRequest req);

    void delete(Long id);
}
