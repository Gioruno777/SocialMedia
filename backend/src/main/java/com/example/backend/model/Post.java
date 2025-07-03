package com.example.backend.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "Post.create", procedureName = "sp_create_post", parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_user_id", type = Long.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_content", type = String.class)
        }),
        @NamedStoredProcedureQuery(name = "Post.update", procedureName = "sp_update_post", parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_post_id", type = Long.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_content", type = String.class)
        }),
        @NamedStoredProcedureQuery(name = "Post.delete", procedureName = "sp_delete_post", parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_post_id", type = Long.class)
        })
})
@Entity
@Getter
@Setter
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "content")
    private String content;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

}
