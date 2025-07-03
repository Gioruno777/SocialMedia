package com.example.backend.DTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CommentResponse {
    private Long commentId;
    private String content;
    private String userName;
    private LocalDateTime createdAt;
}
