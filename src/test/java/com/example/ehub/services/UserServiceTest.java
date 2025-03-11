package com.example.ehub.services;

import com.example.ehub.commons.Gender;
import com.example.ehub.commons.UserStatus;
import com.example.ehub.commons.UserType;
import com.example.ehub.controllers.requests.AddressRequest;
import com.example.ehub.controllers.requests.UserCreationRequest;
import com.example.ehub.controllers.requests.UserPasswordRequest;
import com.example.ehub.controllers.requests.UserUpdateRequest;
import com.example.ehub.controllers.responses.UserPageResponse;
import com.example.ehub.controllers.responses.UserResponse;
import com.example.ehub.exceptions.ResourceNotFoundException;
import com.example.ehub.models.UserEntity;
import com.example.ehub.repositories.AddressRepository;
import com.example.ehub.repositories.UserRepository;
import com.example.ehub.services.Impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

// Unit test cho service layer
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private UserService userService;

    private @Mock UserRepository userRepository;
    private @Mock AddressRepository addressRepository;
    private @Mock PasswordEncoder passwordEncoder;
    private @Mock EmailService emailService;

    private static UserEntity thanh;
    private static UserEntity johnDoe;

    @BeforeAll
    static void beforeAll() {
        thanh = new UserEntity();
        thanh.setId(1L);
        thanh.setFirstName("Thanh");
        thanh.setLastName("Truong");
        thanh.setGender(Gender.MALE);
        thanh.setBirthday(new Date());
        thanh.setEmail("truongtamthanh2006@gmail.com");
        thanh.setPhone("0975118228");
        thanh.setUsername("thanhtruong");
        thanh.setPassword("password");
        thanh.setType(UserType.USER);
        thanh.setStatus(UserStatus.ACTIVE);

        johnDoe = new UserEntity();
        johnDoe.setId(2L);
        johnDoe.setFirstName("John");
        johnDoe.setLastName("Doe");
        johnDoe.setGender(Gender.FEMALE);
        johnDoe.setBirthday(new Date());
        johnDoe.setEmail("johndoe@gmail.com");
        johnDoe.setPhone("0123456789");
        johnDoe.setUsername("johndoe");
        johnDoe.setPassword("password");
        johnDoe.setType(UserType.USER);
        johnDoe.setStatus(UserStatus.INACTIVE);
    }

    @BeforeEach
    void beforeEach() {
        userService = new UserServiceImpl(userRepository, addressRepository, passwordEncoder, emailService);
    }

    @Test
    void testGetUserList_Success() {
        Page<UserEntity> userPage = new PageImpl<>(List.of(thanh, johnDoe));
        when(userRepository.findAll(any(Pageable.class))).thenReturn(userPage);

        UserPageResponse result = userService.findAll(null, null, 0, 20);

        Assertions.assertNotNull(result);
        assertEquals(2, result.totalElements);
    }

    @Test
    void testSearchUser_Success() {
        Page<UserEntity> userPage = new PageImpl<>(List.of(thanh, johnDoe));
        when(userRepository.searchByKeyword(any(), any(Pageable.class))).thenReturn(userPage);

        UserPageResponse result = userService.findAll("thanh", null, 0, 20);

        Assertions.assertNotNull(result);
        assertEquals(2, result.totalElements);
        assertEquals("thanhtruong", result.getUsers().get(0).getUsername());
    }

    @Test
    void testGetUserList_Empty() {
        Page<UserEntity> userPage = new PageImpl<>(List.of());
        when(userRepository.findAll(any(Pageable.class))).thenReturn(userPage);

        UserPageResponse result = userService.findAll(null, null, 0, 20);

        Assertions.assertNotNull(result);
        assertEquals(0, result.getUsers().size());
    }

    @Test
    void testGetUserById_Success() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(thanh));

        UserResponse result = userService.findById(1L);

        Assertions.assertNotNull(result);
        assertEquals("thanhtruong", result.getUsername());
    }

    @Test
    void testGetUserById_Failure() {
        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> userService.findById(10L));
        assertEquals("User not found", thrown.getMessage());
    }

    @Test
    void testFindByUsername_Success() {
        when(userRepository.findByUsername("thanhtruong")).thenReturn(thanh);

        UserResponse result = userService.findByUsername("thanhtruong");

        Assertions.assertNotNull(result);
        assertEquals("thanhtruong", result.getUsername());
    }

    @Test
    void testFindByEmail_Success() {
        when(userRepository.findByEmail("truongtamthanh2006@gmail.com")).thenReturn(thanh);

        UserResponse result = userService.findByEmail("truongtamthanh2006@gmail.com");

        Assertions.assertNotNull(result);
        assertEquals("truongtamthanh2006@gmail.com", result.getEmail());
    }

    @Test
    void testSave_Success() {
        when(userRepository.save(any(UserEntity.class))).thenReturn(thanh);

        UserCreationRequest userCreationRequest = new UserCreationRequest();
        userCreationRequest.setFirstName("Thanh");
        userCreationRequest.setLastName("Truong");
        userCreationRequest.setGender(Gender.MALE);
        userCreationRequest.setBirthday(new Date());
        userCreationRequest.setEmail("truongtamthanh2006@gmail.com");
        userCreationRequest.setPhone("0975118228");
        userCreationRequest.setUsername("thanhtruong");

        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setApartmentNumber("ApartmentNumber");
        addressRequest.setFloor("Floor");
        addressRequest.setBuilding("Building");
        addressRequest.setStreetNumber("StreetNumber");
        addressRequest.setStreet("Street");
        addressRequest.setCity("City");
        addressRequest.setCountry("Country");
        addressRequest.setAddressType(1);
        userCreationRequest.setAddresses(List.of(addressRequest));

        long result = userService.save(userCreationRequest);

        assertNotNull(result);
        assertEquals(1L, result);
    }

    @Test
    void testUpdate_Success() {
        Long userId = 2L;

        UserEntity updatedUser = new UserEntity();
        updatedUser.setId(userId);
        updatedUser.setFirstName("Jane");
        updatedUser.setLastName("Smith");
        updatedUser.setGender(Gender.FEMALE);
        updatedUser.setBirthday(new Date());
        updatedUser.setEmail("janesmith@gmail.com");
        updatedUser.setPhone("0123456789");
        updatedUser.setUsername("janesmith");
        updatedUser.setType(UserType.USER);
        updatedUser.setStatus(UserStatus.ACTIVE);

        when(userRepository.findById(userId)).thenReturn(Optional.of(johnDoe));
        when(userRepository.save(any(UserEntity.class))).thenReturn(updatedUser);

        UserUpdateRequest updateRequest = new UserUpdateRequest();
        updateRequest.setId(userId);
        updateRequest.setFirstName("Jane");
        updateRequest.setLastName("Smith");
        updateRequest.setGender(Gender.MALE);
        updateRequest.setBirthday(new Date());
        updateRequest.setEmail("janesmith@gmail.com");
        updateRequest.setPhone("0123456789");
        updateRequest.setUsername("janesmith");

        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setApartmentNumber("ApartmentNumber");
        addressRequest.setFloor("Floor");
        addressRequest.setBuilding("Building");
        addressRequest.setStreetNumber("StreetNumber");
        addressRequest.setStreet("Street");
        addressRequest.setCity("City");
        addressRequest.setCountry("Country");
        addressRequest.setAddressType(1);
        updateRequest.setAddresses(List.of(addressRequest));

        userService.update(updateRequest);

        UserResponse result = userService.findById(userId);

        assertEquals("janesmith", result.getUsername());
        assertEquals("janesmith@gmail.com", result.getEmail());
    }

    @Test
    void testChangePassword_Success() {
        Long userId = 2L;

        UserPasswordRequest request = new UserPasswordRequest();
        request.setId(userId);
        request.setPassword("newPassword");
        request.setConfirmPassword("newPassword");

        when(userRepository.findById(userId)).thenReturn(Optional.of(johnDoe));
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedNewPassword");

        userService.changePassword(request);

        assertEquals("encodedNewPassword", johnDoe.getPassword());
        verify(userRepository, times(1)).save(johnDoe);
        verify(passwordEncoder, times(1)).encode(request.getPassword());
    }

    @Test
    void testDeleteUser_Success() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.of(thanh));

        userService.delete(userId);

        assertEquals(UserStatus.INACTIVE, thanh.getStatus());
        verify(userRepository, times(1)).save(thanh);
    }

    @Test
    void testUserNotFound_ThrowsException() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> userService.delete(userId));

        assertEquals("User not found", exception.getMessage());
        verify(userRepository, never()).save(any(UserEntity.class));
    }
}
