package com.fazo.esm;

import com.fazo.esm.entity.User;
import com.fazo.esm.payload.RegisterRequest;
import com.fazo.esm.repository.UserRepository;
import com.fazo.esm.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

import static com.fazo.esm.entity.Role.ADMIN;

@SpringBootApplication
@AllArgsConstructor
public class SecurityApplication {
    private final UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(SecurityApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            AuthenticationService service
    ) {
        return args -> {
            Optional<User> user = userRepository.findByUsername("admin@mail.com");
            if (user.isEmpty()) {
                logger.info("Admin user not found. Creating a new admin user.");

                var admin = RegisterRequest.builder()
                        .username("admin@mail.com")
                        .password("password")
                        .role(ADMIN)
                        .build();
                String adminToken = service.register(admin).getAccessToken();

                logger.info("Admin user created successfully. Admin token: {}", adminToken);
            } else {
                User existingUser = user.get();
                String existingToken = existingUser.getTokens().isEmpty() ? "No tokens found" : existingUser.getTokens().get(0).getToken();

                logger.info("Admin user found. Admin token: {}", existingToken);
            }
        };
    }
}
