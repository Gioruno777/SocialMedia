package com.example.backend.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PostRequest {
    @NotBlank(message = "內容不能為空")
    private String content;
}
