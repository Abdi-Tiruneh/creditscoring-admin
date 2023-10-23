package com.dxvalley.creditscoring;

import com.dxvalley.creditscoring.userAndRole.role.Role;
import com.dxvalley.creditscoring.userAndRole.role.RoleRepository;
import com.dxvalley.creditscoring.userAndRole.user.UserRepository;
import com.dxvalley.creditscoring.userAndRole.user.Users;
import com.dxvalley.creditscoring.utils.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@ConditionalOnProperty(prefix = "database", name = "seed", havingValue = "true")
@RequiredArgsConstructor
@Slf4j
public class ApplicationRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Initializes the database with preloaded data upon application startup.
     */
    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            try {
                // Create and save role
                Role role = createRole();
                Role savedRole = roleRepository.save(role);

                // Create and save user
                Users johnDoe = createUser(savedRole);
                userRepository.save(johnDoe);

                log.info("ApplicationRunner => Preloaded authority, roles, and user");
            } catch (Exception ex) {
                log.error("ApplicationRunner Preloading Error: {}", ex.getMessage());
                throw new RuntimeException("ApplicationRunner Preloading Error ", ex);
            }
        };
    }


    private Role createRole() {
        return new Role("ADMIN", "Oversees app functionality, user access, and settings.");
    }

    private Users createUser(Role role) {
        return Users.builder()
                .username("user@coop.com")
                .password(passwordEncoder.encode("123456"))
                .fullName("John Doe")
                .phoneNumber("+251912345678")
                .role(role)
                .isEnabled(true)
                .userStatus(Status.ACTIVE)
                .addedBy("System")
                .build();
    }
}