package com.enterprise.chat.user_service.service;

import com.enterprise.chat.user_service.dto.UpdateUserRequest;
import com.enterprise.chat.user_service.dto.UserRequest;
import com.enterprise.chat.user_service.dto.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createUser(UserRequest request);
    List<UserResponse> getAllUsers();
    UserResponse getUserById(Long userId);
    UserResponse getUserByUsername(String username);
    UserResponse getUserByEmail(String email);
    UserResponse updateUser(Long userId, UpdateUserRequest request);
    void deleteUser(Long userId);
    void deleteUserByUsername(String username);


}
