package com.enterprise.chat.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserRequest {
   @NotBlank(message="first name is required")
    private String firstName;
    @NotBlank(message="Last name is required")
    private String lastName;
    @NotBlank(message="Username is required")
    private String username;
    @NotBlank(message="email is required")
    @Email(message="Email must be valid")
    private String email;
    @NotBlank(message="Password is required")
    @Size(min=8,max=100, message="Password must be between 8 and 100 characters")
    private String password;
    @NotBlank(message="Phone number is required")
    @Pattern(regexp = "^[0-9]{10,15}$",
            message="Phone number must contain between 10 and 15 digits")
    private String phoneNumber;

    public UserRequest(){}

    public UserRequest(String firstName, String lastName, String username,
                       String email, String password, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;

    }
}
