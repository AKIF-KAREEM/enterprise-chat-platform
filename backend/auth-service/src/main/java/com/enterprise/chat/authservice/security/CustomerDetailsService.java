package com.enterprise.chat.authservice.security;

import com.enterprise.chat.authservice.entity.User;
import com.enterprise.chat.authservice.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    public CustomerDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        User user=userRepository.findByUserName(username)
                .orElseThrow(()->new UsernameNotFoundException
                        ("User not found with username: "+username));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUserName())
                .password(user.getPassword())
                .authorities(user.getRoles().stream().map(role->role.getRoleName().name())
                        .toArray(String[]::new))
                .accountExpired(!user.isAccountNonExpired())
                .accountLocked(!user.isAccountNonLocked())
                .credentialsExpired(!user.isCredentialsNonExpired())
                .disabled(!user.isEnabled())
                .build();
    }
}
