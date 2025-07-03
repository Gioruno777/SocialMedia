package com.example.backend.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.backend.DTO.PostResponse;
import com.example.backend.model.Post;
import com.example.backend.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public void createPost(Long userId, String content) {
        postRepository.createPost(userId, content);
    }

    @Override
    public List<PostResponse> getAllPosts() {
        return postRepository.findAllByOrderByCreatedAtDescPostIdDesc().stream().map(post -> {
            PostResponse res = new PostResponse();
            res.setPostId(post.getPostId());
            res.setContent(post.getContent());
            res.setUserId(post.getUser().getUserId());
            res.setUserName(post.getUser().getUserName());
            res.setCreatedAt(post.getCreatedAt());
            res.setCreatedAt(post.getUpdatedAt());
            return res;
        }).toList();
    }

    @Override
    public void updatePost(Long userId, Long postId, String content) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "貼文不存在"));

        if (!post.getUser().getUserId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "沒有權限編輯此貼文");
        }

        postRepository.updatePost(postId, content);
    }

    @Override
    public void deletePost(Long userId, Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "貼文不存在"));

        if (!post.getUser().getUserId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "沒有權限刪除此貼文");
        }
        postRepository.deletePost(postId);
    }
}
