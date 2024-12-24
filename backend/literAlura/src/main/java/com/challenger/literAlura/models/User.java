package com.challenger.literAlura.models;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(unique = true)
        private String username;

        @Column(unique = true)
        private String email;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }


}
