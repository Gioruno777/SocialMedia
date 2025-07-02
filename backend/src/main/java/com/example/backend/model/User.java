package com.example.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@NamedStoredProcedureQuery(name = "User.register", procedureName = "sp_register_user", parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_user_name", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_phone", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_email", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_password", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_cover_image", type = String.class),
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_biography", type = String.class)
})
@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "cover_image")
    private String coverImage;

    @Column(name = "biography")
    private String biography;

}
