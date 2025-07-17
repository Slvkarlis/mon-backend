package com.sagemcom.stap.Controller;

import java.util.Map;
import java.util.Optional;
import com.sagemcom.stap.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sagemcom.stap.Entity.Category;
import com.sagemcom.stap.Entity.User;
import com.sagemcom.stap.Service.AuthService;
import com.sagemcom.stap.dto.AuthResponse;
import com.sagemcom.stap.dto.LoginRequest;

@RestController
//@RequestMapping("/api/admin")
public class AdminController {

    private final UserRepository userRepository;

private final BCryptPasswordEncoder passwordEncoder;
     private final AuthService authService;

    public AdminController(AuthService authService, UserRepository userRepository,BCryptPasswordEncoder passwordEncoder) {
        this.authService = authService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

       @PostMapping("/create-admin")
public ResponseEntity<?> createAdmin() {
    // Vérifier si un admin existe déjà
    if (userRepository.existsByRole("ADMIN")) {
        return ResponseEntity.badRequest().body("❌ Un administrateur existe déjà !");
    }

    // Création de l'utilisateur admin
    User admin = new User();
    admin.setEmail("admin@sagemcom.com");
    admin.setPassword(passwordEncoder.encode("admin123")); // Choisis un bon mot de passe
    admin.setRole("ADMIN");
    admin.setName("Admin");
    admin.setImage(null); // ou une image par défaut

    userRepository.save(admin);

    return ResponseEntity.ok("✅ Admin créé avec succès !");
}


  @GetMapping("/api/admin")
    public ResponseEntity<?> helloadmin() {
        return ResponseEntity.ok("✅ hello admin ");
    }

    
}
