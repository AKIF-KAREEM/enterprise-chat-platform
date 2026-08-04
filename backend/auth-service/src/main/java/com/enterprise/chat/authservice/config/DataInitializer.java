package com.enterprise.chat.authservice.config;

import com.enterprise.chat.authservice.entity.Role;
import com.enterprise.chat.authservice.enums.RoleType;
import com.enterprise.chat.authservice.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Override
    public void run(String... args){
        if(!roleRepository.existsByRoleName(RoleType.ADMIN)){
            roleRepository.save(new Role(RoleType.ADMIN));
        }
        if(!roleRepository.existsByRoleName(RoleType.USER)){
            roleRepository.save(new Role(RoleType.USER));
        }
        if(!roleRepository.existsByRoleName(RoleType.MODERATOR)){
            roleRepository.save(new Role(RoleType.MODERATOR));
        }
    }
}
