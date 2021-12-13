package com.example.anime.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;
import java.util.UUID;

public interface ProjectionAnimeWithDetails {
    UUID getAnimeid();
    String getName();
    String getImageurl();

    @JsonIgnoreProperties("animes")
    Set<ProjectionAuthor> getAuthors();
}
