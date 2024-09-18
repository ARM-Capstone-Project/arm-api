package com.alco.armapi.infrastructure.config;

import com.alco.armapi.common.Constants;
import com.alco.armapi.domain.model.User;
import com.alco.armapi.infrastructure.adapter.persistence.role.RoleEntity;
import com.alco.armapi.infrastructure.adapter.persistence.role.RoleRepository;
import com.alco.armapi.infrastructure.adapter.persistence.user.UserEntity;
import com.alco.armapi.infrastructure.adapter.persistence.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Bean
    public ApplicationRunner initializeData() {
        return args -> {
            // Insert default roles
            insertDefaultRoleIfNotExists(Constants.ROLE_ADMIN, "Admin role");
            insertDefaultRoleIfNotExists(Constants.ROLE_USER, "Default user role");
            insertDefaultRoleIfNotExists(Constants.ROLE_MANAGER, "Manager role");
            insertDefaultRoleIfNotExists(Constants.ROLE_OPERATOR, "Operator role");

            // Insert default admin user if not exists
            insertAdminUserIfNotExists();
        };
    }

    private void insertAdminUserIfNotExists() {
        Optional<UserEntity> adminUserOpt = userRepository.findByUsername("admin");
        if (adminUserOpt.isEmpty()) {
            UserEntity adminUser = new UserEntity();
            adminUser.setUsername("admin");
            adminUser.setEmail("admin@example.com");
            adminUser.setPassword(passwordEncoder.encode("admin123"));

            // Set roles for the admin user
            Set<RoleEntity> roles = Set.of(
                    roleRepository.findByName(Constants.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Role ADMIN not found")),
                    roleRepository.findByName(Constants.ROLE_USER).orElseThrow(() -> new RuntimeException("Role USER not found"))
            );
            adminUser.setRoles(roles);

            userRepository.save(adminUser);
            System.out.println("Inserted default admin user");
        }
    }


    private void insertDefaultRoleIfNotExists(String roleName, String description) {
        Optional<RoleEntity> roleOpt = roleRepository.findByName(roleName);
        if (roleOpt.isEmpty()) {
            RoleEntity role = new RoleEntity();
            role.setName(roleName);
            role.setDescription(description);
            roleRepository.save(role);
            System.out.println("Inserted default role: " + roleName);
        }
    }


}
