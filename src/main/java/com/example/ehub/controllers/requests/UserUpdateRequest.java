package com.example.ehub.controllers.requests;

import com.example.ehub.commons.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class UserUpdateRequest implements Serializable {
    @NotNull(message = "Id must be not null")
    @Min(value = 1, message = "userId must be equal or greater than 1")
    private Long id;
    @NotBlank(message = "First name must be not blank")
    private String firstName;

    @NotBlank(message = "First name must be not blank")
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Date birthday;
    private String username;

    @Email(message = "Email invalid")
    private String email;
    private String phone;
    private List<AddressRequest> addresses;

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(@NotNull(message = "Id must be not null") @Min(value = 1, message = "userId must be equal or greater than 1") Long id) {
        this.id = id;
    }

    public void setFirstName(@NotBlank(message = "First name must be not blank") String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(@NotBlank(message = "First name must be not blank") String lastName) {
        this.lastName = lastName;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(@Email(message = "Email invalid") String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddresses(List<AddressRequest> addresses) {
        this.addresses = addresses;
    }

    public List<AddressRequest> getAddresses() {
        return addresses;
    }

    @Override
    public String toString() {
        return "UserUpdateRequest{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", addresses=" + addresses +
                '}';
    }
}
