package com.example.backend.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data

public class RegisterRequest {

    @NotBlank(message = "名稱不能為空")
    private String name;
    @NotBlank(message = "手機號碼不能為空")
    private String phone;
    @NotBlank(message = "Email 不能為空")
    private String email;
    @NotBlank(message = "密碼不能為空")
    private String password;
}
