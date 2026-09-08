package com.enterprise.chat.user_service.service.impl;

import com.enterprise.chat.user_service.dto.UpdateUserRequest;
import com.enterprise.chat.user_service.dto.UserRequest;
import com.enterprise.chat.user_service.dto.UserResponse;
import com.enterprise.chat.user_service.entity.User;
import com.enterprise.chat.user_service.exception.ResourceAlreadyExistsException;
import com.enterprise.chat.user_service.exception.ResourceNotFoundException;
import com.enterprise.chat.user_service.repository.UserRepository;
import com.enterprise.chat.user_service.service.UserService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

   private final UserRepository userRepository;
   private final PasswordEncoder passwordEncoder;
   private final JdbcTemplate jdbcTemplate;

   public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                          JdbcTemplate jdbcTemplate) {
       this.userRepository = userRepository;
       this.passwordEncoder = passwordEncoder;
       this.jdbcTemplate = jdbcTemplate;
   }

    @Override
    @Transactional
    public UserResponse createUser(UserRequest request) {
     if(userRepository.existsByUsername(request.getUsername()))
         throw new ResourceAlreadyExistsException("Username already exists");

     if(userRepository.existsByEmail(request.getEmail()))
         throw new ResourceAlreadyExistsException("Email already exists");
     User user=new User();
     user.setFirstName(request.getFirstName());
     user.setLastName(request.getLastName());
     user.setUsername(request.getUsername());
     user.setEmail(request.getEmail());
     user.setPassword(passwordEncoder.encode(request.getPassword()));
     user.setPhoneNumber(request.getPhoneNumber());

     User savedUser=userRepository.save(user);

     Long userRoleId=jdbcTemplate.queryForObject("SELECT role_id FROM roles WHERE role_name=?",
             Long.class,"USER");
     if(userRoleId==null){
         throw new ResourceNotFoundException("Default USER role not found");
     }
     jdbcTemplate.update("INSERT INTO user_roles (user_id,role_id) VALUES (?,?)",
             savedUser.getUserId(),
             userRoleId);

     return mapToResponse(savedUser);
   }


    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public UserResponse getUserById(Long userId) {

        User user=userRepository.findById(userId)
                .orElseThrow(()->
                        new ResourceNotFoundException(
                                "User not found with id "+userId
                        ));

       return mapToResponse(user);
    }

    @Override
    public UserResponse getUserByUsername(String username) {
       User user=userRepository.findByUsername(username)
               .orElseThrow(()->new ResourceNotFoundException(
                       "User not found with username "+username
               ));

        return mapToResponse(user);
    }

    @Override
    public UserResponse getUserByEmail(String email) {
       User user=userRepository.findByEmail(email)
               .orElseThrow(()->new ResourceNotFoundException(
                       "User not found with email "+email
               ));

        return mapToResponse(user);
    }

    @Override
    @Transactional
    public UserResponse updateUser(Long userId, UpdateUserRequest request) {
       User user=userRepository.findById(userId).orElseThrow(
               ()->new ResourceNotFoundException(
                       "User not found with id "+userId
               )
       );
       if(!user.getUsername().equals(request.getUsername())
       && userRepository.existsByUsername(request.getUsername())){
           throw new  ResourceAlreadyExistsException("Username already exists"+
                   request.getUsername());
       }
       if(!user.getEmail().equals(request.getEmail())&&
       userRepository.existsByEmail(request.getEmail())){
           throw new  ResourceAlreadyExistsException("Email already exists "+request.getEmail());
       }
       user.setFirstName(request.getFirstName());
       user.setLastName(request.getLastName());
       user.setUsername(request.getUsername());
       user.setEmail(request.getEmail());

       if(request.getPhoneNumber()!=null &&
       !request.getPhoneNumber().isBlank()){
           user.setPhoneNumber(request.getPhoneNumber());
       }

       if(request.getPassword()!=null && !request.getPassword().isBlank()){
           user.setPassword(passwordEncoder.encode(request.getPassword()));
       }
       User updatedUser=userRepository.save(user);
        return mapToResponse(updatedUser);
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
     if(!userRepository.existsById(userId)){
         throw new ResourceNotFoundException("User not found with id "+userId);
     }
     jdbcTemplate.update("DELETE FROM user_roles WHERE user_id=?",userId);
     userRepository.deleteById(userId);
    }

    @Override
    @Transactional
    public void deleteUserByUsername(String username){
       User user=userRepository.findByUsername(username)
               .orElseThrow(()-> new ResourceNotFoundException(
                       "User not found with username "+username
               ));
       Long userId=user.getUserId();
       jdbcTemplate.update("DELETE FROM user_roles WHERE user_id=?", userId);
       userRepository.deleteById(userId);
    }

    private UserResponse mapToResponse(User user) {
       return new UserResponse(
               user.getUserId(),
               user.getFirstName(),
               user.getLastName(),
               user.getUsername(),
               user.getEmail()
       );
    }
}

