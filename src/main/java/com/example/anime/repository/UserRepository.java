package com.example.anime.repository;

import com.example.anime.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository {

    User findByUsername(String username);
}
