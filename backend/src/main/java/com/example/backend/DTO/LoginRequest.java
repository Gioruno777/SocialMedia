package com.example.backend.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "手機號碼不能為空")
    private String phone;
    @NotBlank(message = "密碼不能為空")
    private String password;
}
