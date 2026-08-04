package com.enterprise.chat.authservice.service;

import com.enterprise.chat.authservice.dto.request.LoginRequest;
import com.enterprise.chat.authservice.dto.request.RegisterRequest;
import com.enterprise.chat.authservice.dto.response.LoginResponse;
import com.enterprise.chat.authservice.dto.response.UserResponse;

public interface UserService {

    UserResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
    UserResponse getUserById(Long userId);

}
