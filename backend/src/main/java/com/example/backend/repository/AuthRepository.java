package com.example.backend.repository;

import com.example.backend.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<User, Long> {
    @Procedure(name = "User.register")
    void register(
            @Param("p_user_name") String userName,
            @Param("p_phone") String phone,
            @Param("p_email") String email,
            @Param("p_password") String password,
            @Param("p_cover_image") String coverImage,
            @Param("p_biography") String biography);

    Optional<User> findByPhone(String phone);
}
