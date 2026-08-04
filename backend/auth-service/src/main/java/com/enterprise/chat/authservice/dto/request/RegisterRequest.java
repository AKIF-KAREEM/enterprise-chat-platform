package com.enterprise.chat.authservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegisterRequest {
  @NotBlank
  @Size(min = 3, max = 50)
  private String userName;
  @NotBlank
  @Email
  private String email;
  @NotBlank
  @Size(min = 8,max = 100,message="Password must be between 8 and 100 characters")
  private String password;
  @NotBlank
  private String firstName;
  @NotBlank
  private String lastName;
  @NotBlank
  @Pattern(regexp ="^[0-9]{10,15}$", message="Phone number must contain between 10 and 15 digits")
  private String phoneNumber;
  public RegisterRequest() {}
  public RegisterRequest(String userName, String email, String password, String firstName, String lastName, String phoneNumber) {
      this.userName = userName;
      this.email = email;
      this.password = password;
      this.firstName = firstName;
      this.lastName = lastName;
      this.phoneNumber = phoneNumber;
  }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public void setPassword(String password) {
        this.password = password;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
