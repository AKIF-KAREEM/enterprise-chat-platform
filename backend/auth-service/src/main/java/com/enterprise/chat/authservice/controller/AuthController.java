package com.enterprise.chat.authservice.controller;

import com.enterprise.chat.authservice.dto.request.LoginRequest;
import com.enterprise.chat.authservice.dto.request.RegisterRequest;
import com.enterprise.chat.authservice.dto.response.LoginResponse;
import com.enterprise.chat.authservice.dto.response.UserResponse;
import com.enterprise.chat.authservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request){
     UserResponse response=userService.register(request);
     return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request){
        LoginResponse response=userService.login(request);

        return ResponseEntity.ok(response);

    }
    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("userId") Long userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }
    @GetMapping("/protected")
    public ResponseEntity<String> protectedEndPoint(Authentication authentication){
        return ResponseEntity.ok("Authenticated successffully as:"+authentication.getName());
    }
}
