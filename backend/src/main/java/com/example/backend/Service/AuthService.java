package com.example.backend.Service;

import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {
    public void register(String name, String phone, String email, String password);

    public String login(String phone, String password);

    public void verifyUser(HttpServletRequest request);
}
