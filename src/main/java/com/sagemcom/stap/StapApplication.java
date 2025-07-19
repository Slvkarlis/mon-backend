package com.sagemcom.stap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.sagemcom.stap.Entity.User;
import com.sagemcom.stap.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan("com.sagemcom.stap.Entity")
public class StapApplication {

    public static void main(String[] args) {
        SpringApplication.run(StapApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.existsByRole("ADMIN")) {
                System.out.println("⚠️ Un administrateur existe déjà.");
                return;
            }

            User admin = new User();
            admin.setEmail("admin@sagemcom.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ADMIN");
            admin.setName("Admin");
            admin.setImage(null);

            userRepository.save(admin);
            System.out.println("✅ Admin créé avec succès.");
        };
    }
}
