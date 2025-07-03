package com.example.backend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.DTO.LoginRequest;
import com.example.backend.DTO.RegisterRequest;
import com.example.backend.Service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest req) {
        authService.register(req.getName(), req.getPhone(), req.getEmail(), req.getPassword());
        return ResponseEntity.ok(Map.of("message", "註冊成功"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest req) {
        String token = authService.login(req.getPhone(), req.getPassword());
        return ResponseEntity.ok(Map.of("token", token));
    }

    @GetMapping("/validatetoken")
    public ResponseEntity<?> verifyUser(HttpServletRequest request) {
        authService.verifyUser(request);
        return ResponseEntity.ok(Map.of("message", "登入成功"));
    }

}