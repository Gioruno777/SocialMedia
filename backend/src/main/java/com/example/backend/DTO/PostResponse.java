package com.example.backend.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PostResponse {
    private Long postId;
    private String content;
    private Long userId;
    private String userName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
