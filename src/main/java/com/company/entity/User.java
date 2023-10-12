package com.company.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;
    private String uid;
    private String firstName;
    private String lastName;
    private Boolean activated;
    private String email;
    private String langKey;
    private String imageUrl;
}
