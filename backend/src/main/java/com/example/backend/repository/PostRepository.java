package com.example.backend.repository;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.backend.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Procedure(name = "Post.create")
    void createPost(
            @Param("p_user_id") Long userId,
            @Param("p_content") String content);

    @Procedure(name = "Post.update")
    void updatePost(@Param("p_post_id") Long postId, @Param("p_content") String content);

    @Procedure(name = "Post.delete")
    void deletePost(@Param("p_post_id") Long postId);

    List<Post> findAllByOrderByCreatedAtDescPostIdDesc();
}
