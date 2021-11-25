package com.example.anime.domain.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID userid;

    public String username;
    public String password;
}