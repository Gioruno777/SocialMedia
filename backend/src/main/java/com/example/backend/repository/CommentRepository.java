package com.example.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.backend.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Procedure(name = "Comment.create")
    void createComment(
            @Param("p_user_id") Long userId,
            @Param("p_post_id") Long postId,
            @Param("p_content") String content);

    List<Comment> findByPost_PostIdOrderByCreatedAtAsc(Long postId);
}
