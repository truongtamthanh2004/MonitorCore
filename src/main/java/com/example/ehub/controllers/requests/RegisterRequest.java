package com.example.ehub.controllers.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {
    @NotBlank(message = "First name must not be blank")
    private String firstName;

    @NotBlank(message = "Last name must not be blank")
    private String lastName;

    @NotBlank(message = "Username must not be blank")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password must not be blank")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotBlank(message = "Confirm password must not be blank")
    @Size(min = 6, message = "Confirm password must be at least 6 characters")
    private String confirmPassword;

    public @NotBlank(message = "First name must not be blank") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotBlank(message = "First name must not be blank") String firstName) {
        this.firstName = firstName;
    }

    public @NotBlank(message = "Last name must not be blank") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotBlank(message = "Last name must not be blank") String lastName) {
        this.lastName = lastName;
    }

    public @NotBlank(message = "Username must not be blank") @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "Username must not be blank") @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters") String username) {
        this.username = username;
    }

    public @NotBlank(message = "Email must not be blank") @Email(message = "Invalid email format") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email must not be blank") @Email(message = "Invalid email format") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Password must not be blank") @Size(min = 6, message = "Password must be at least 6 characters") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password must not be blank") @Size(min = 6, message = "Password must be at least 6 characters") String password) {
        this.password = password;
    }

    public @NotBlank(message = "Password must not be blank") @Size(min = 6, message = "Password must be at least 6 characters") String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(@NotBlank(message = "Password must not be blank") @Size(min = 6, message = "Password must be at least 6 characters") String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
