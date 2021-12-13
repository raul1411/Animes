package com.example.anime.repository;

import com.example.anime.domain.model.Author;
import com.example.anime.domain.model.projection.ProjectionAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
    List<ProjectionAuthor> findBy();
}
