package com.example.anime.repository;

import com.example.anime.domain.model.User;
import com.example.anime.domain.model.projection.ProjectionUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findByUsername(String username);

    <T> List<T> findByUsername(String username, Class<T> type);

    List<ProjectionUser> findBy();

    <T> List<T> findByUserid(UUID id, Class<T> type);

    User findByUserid(UUID id);

}