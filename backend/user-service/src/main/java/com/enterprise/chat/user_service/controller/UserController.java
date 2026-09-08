package com.enterprise.chat.user_service.controller;

import com.enterprise.chat.user_service.dto.UpdateUserRequest;
import com.enterprise.chat.user_service.dto.UserRequest;
import com.enterprise.chat.user_service.dto.UserResponse;
import com.enterprise.chat.user_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest request){
       UserResponse response=userService.createUser(request);
        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

   @GetMapping
   public ResponseEntity<List<UserResponse>> getAllUsers(){

    return ResponseEntity.ok(userService.getAllUsers());
   }
   @GetMapping("/{userId}")
   public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId){
    return ResponseEntity.ok(userService.getUserById(userId));
   }
   @GetMapping("/username/{username}")
   public ResponseEntity<UserResponse> getUserByUsername(@PathVariable String username){
    return ResponseEntity.ok(userService.getUserByUsername(username));
   }
   @GetMapping("/email/{email}")
   public ResponseEntity<UserResponse> getUserByEmail(@PathVariable String email){
    return ResponseEntity.ok(userService.getUserByEmail(email));
   }
   @PutMapping("/{userId}")
   public ResponseEntity<UserResponse> updateUser(@PathVariable Long userId,
                                                 @Valid @RequestBody UpdateUserRequest request){
    return ResponseEntity.ok(userService.updateUser(userId,request));
   }
   @DeleteMapping("/{userId}")
   public ResponseEntity<Void> deleteUser(@PathVariable Long userId){
    userService.deleteUser(userId);
    return ResponseEntity.noContent().build();
   }
   @DeleteMapping("/by-username/{username}")
   public ResponseEntity<Void> deleteUserByUsername(@PathVariable String username){
        userService.deleteUserByUsername(username);
        return ResponseEntity.noContent().build();
   }
   }