package com.enterprise.chat.authservice.service.impl;

import com.enterprise.chat.authservice.dto.request.LoginRequest;
import com.enterprise.chat.authservice.dto.request.RegisterRequest;
import com.enterprise.chat.authservice.dto.response.LoginResponse;
import com.enterprise.chat.authservice.dto.response.UserResponse;
import com.enterprise.chat.authservice.entity.Role;
import com.enterprise.chat.authservice.entity.User;
import com.enterprise.chat.authservice.enums.RoleType;
import com.enterprise.chat.authservice.exception.ResourceAlreadyExistsException;
import com.enterprise.chat.authservice.exception.ResourceNotFoundException;
import com.enterprise.chat.authservice.repository.RoleRepository;
import com.enterprise.chat.authservice.repository.UserRepository;
import com.enterprise.chat.authservice.security.JwtService;
import com.enterprise.chat.authservice.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager,
                           JwtService jwtService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService=jwtService;
    }
    @Override
    public UserResponse register(RegisterRequest request) {
        //to check if username already exists
        if (userRepository.existsByUserName(request.getUserName())) {
            throw new ResourceAlreadyExistsException("Username already exists");}
            //to check if email already exists
        if (userRepository.existsByEmail(request.getEmail())){
                throw new ResourceAlreadyExistsException("Email already exists");}
            //Fetch default user role
            Role userRole = roleRepository.findByRoleName(RoleType.USER)
                    .orElseThrow(() -> new RuntimeException("Default USER not found"));
            //create User entity
            User user = new User();
            user.setUserName(request.getUserName());
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setPhoneNumber(request.getPhoneNumber());
            //Assign user role
            user.getRoles().add(userRole);
            //save user
            User savedUser = userRepository.save(user);
            //convert roles to string set
            Set<String> roles = savedUser.getRoles().stream()
                    .map(role -> role.getRoleName().name())
                    .collect(Collectors.toSet());
            //return response
            return new UserResponse(savedUser.getUserId(),
                    savedUser.getUserName(),
                    savedUser.getEmail(),
                    savedUser.getFirstName(),
                    savedUser.getLastName(),
                    savedUser.getPhoneNumber(),
                    roles);
        }


        @Override
        public LoginResponse login (LoginRequest request){

           Authentication authentication=authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(
                           request.getLogin(),
                           request.getPassword()
                   )
           );

           UserDetails userDetails =(UserDetails) authentication.getPrincipal();
           String token=jwtService.generateToken(userDetails);
           User user= userRepository.findByUserName(request.getLogin())
                   .orElseThrow(()->new ResourceNotFoundException(
                           "User not found with username"+request.getLogin()));
        return new LoginResponse(token,
                "Bearer",
                user.getUserId(),
                user.getUserName(

                ));
        }

        @Override
        @Transactional(readOnly = true)
        public UserResponse getUserById (Long userId){

        User user=userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User not found with id:"+userId));
        return new UserResponse(user.getUserId(),
                user.getUserName(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhoneNumber(),
                user.getRoles().stream().map(role->role.getRoleName().name())
                        .collect(Collectors.toSet()));
        }
    }
