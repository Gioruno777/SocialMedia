package com.example.backend.Service;

import com.example.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.backend.repository.AuthRepository;
import com.example.backend.util.JwtUtil;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void register(String name, String phone, String email, String password) {

        String hashedPassword = passwordEncoder.encode(password);

        try {
            authRepository.register(name, phone, email, hashedPassword, null, null);
        } catch (DataIntegrityViolationException e) {
            String msg = e.getRootCause().getMessage();

            if (msg.contains("users.phone")) {
                throw new RuntimeException("手機號碼已被註冊!");
            } else if (msg.contains("users.email")) {
                throw new RuntimeException("信箱已被註冊!");
            }
            throw new RuntimeException("註冊失敗!");
        }
    }

    @Override
    public String login(String phone, String password) {
        User user = authRepository.findByPhone(phone)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "帳號錯誤"));

        if (!BCrypt.checkpw(password, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "密碼錯誤");
        }
        String token = jwtUtil.generateToken(user.getUserId());
        return token;
    }

}
