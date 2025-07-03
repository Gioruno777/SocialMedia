package com.example.backend.Service;

import java.util.List;

import com.example.backend.DTO.PostResponse;

public interface PostService {
    public void createPost(Long userId, String content);

    List<PostResponse> getAllPosts();

    void updatePost(Long userId, Long postId, String content);

    void deletePost(Long userId, Long postId);

}
