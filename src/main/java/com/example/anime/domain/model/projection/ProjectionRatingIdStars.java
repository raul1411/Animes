package com.example.anime.domain.model.projection;

import com.example.anime.domain.model.Anime;
import com.example.anime.domain.model.User;

import java.util.List;
import java.util.UUID;

public interface ProjectionRatingIdStars {
    UUID getAnimeid();
    UUID getUserid();
    float getStars();



}
