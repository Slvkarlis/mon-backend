package com.sagemcom.stap.Controller;

import com.sagemcom.stap.Service.AuthService;
import com.sagemcom.stap.dto.AuthResponse;
import com.sagemcom.stap.dto.LoginRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<AuthResponse> authResponse = authService.login(loginRequest.getEmail(), loginRequest.getPassword());

        if (authResponse.isEmpty()) {
            return ResponseEntity.status(401).body(
                Map.of("error", "Invalid email or password")
            );
        }

        return ResponseEntity.ok(authResponse.get());
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody LoginRequest registerRequest) {
        Optional<AuthResponse> authResponse = authService.register(registerRequest.getEmail(), registerRequest.getPassword());

        if (authResponse.isEmpty()) {
            return ResponseEntity.badRequest().body(
                Map.of("error", "Email already in use")
            );
        }

        return ResponseEntity.status(201).body(authResponse.get());
    }
}
