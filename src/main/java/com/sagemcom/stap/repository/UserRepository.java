package com.sagemcom.stap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sagemcom.stap.Entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByRole(String role );
}
