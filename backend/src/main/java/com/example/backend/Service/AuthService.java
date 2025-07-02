package com.example.backend.Service;

public interface AuthService {
    public void register(String name, String phone, String email, String password);

    public String login(String phone, String password);
}
