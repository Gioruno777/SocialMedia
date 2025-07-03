package com.example.backend.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.DTO.CommentResponse;
import com.example.backend.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void createComment(Long userId, Long postId, String content) {
        commentRepository.createComment(userId, postId, content);
    }

    @Override
    public List<CommentResponse> getCommentsByPost(Long postId) {
        return commentRepository.findByPost_PostIdOrderByCreatedAtAsc(postId).stream().map(comment -> {
            CommentResponse res = new CommentResponse();
            res.setCommentId(comment.getCommentId());
            res.setContent(comment.getContent());
            res.setCreatedAt(comment.getCreatedAt());
            res.setUserName(comment.getUser().getUserName());
            return res;
        }).toList();
    }
}