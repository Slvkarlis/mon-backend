package com.sagemcom.stap.dto;

import com.sagemcom.stap.Entity.User;

public class AuthResponse {

    private String token;
    private User user;

    // Constructeurs
    public AuthResponse() {}

    public AuthResponse(String token, User user) {
        this.token = token;
        this.user = user;
    }

    // Getters & Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
