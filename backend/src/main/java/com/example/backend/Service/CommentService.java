package com.example.backend.Service;

import java.util.List;

import com.example.backend.DTO.CommentResponse;

public interface CommentService {
    public void createComment(Long userId, Long postId, String content);

    public List<CommentResponse> getCommentsByPost(Long postId);
}
