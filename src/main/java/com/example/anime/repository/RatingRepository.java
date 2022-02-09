package com.example.anime.repository;

import com.example.anime.domain.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RatingRepository extends JpaRepository<Rating, UUID> {

}
