package com.sagemcom.stap.Service;

import com.sagemcom.stap.Entity.User;
import com.sagemcom.stap.dto.AuthResponse;
import com.sagemcom.stap.repository.UserRepository;
import com.sagemcom.stap.utils.JwtUtils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, JwtUtils jwtUtils, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<AuthResponse> login(String email, String rawPassword) {
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isEmpty() || !passwordEncoder.matches(rawPassword, userOpt.get().getPassword())) {
            return Optional.empty();
        }

        User user = userOpt.get();

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("role", user.getRole());

        String token = jwtUtils.generateToken(claims, 1000 * 60 * 60 * 24 * 7); // 7 jours

        AuthResponse authResponse = new AuthResponse(token, user);

        return Optional.of(authResponse);
    }

    public Optional<AuthResponse> register(String email, String rawPassword) {
        if (userRepository.existsByEmail(email)) {
            return Optional.empty();
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRole("USER"); // rôle par défaut

        userRepository.save(user);

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("role", user.getRole());

        String token = jwtUtils.generateToken(claims, 1000 * 60 * 60 * 24 * 7);

        AuthResponse authResponse = new AuthResponse(token, user);

        return Optional.of(authResponse);
    }
}
