package com.example.anime.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;
import java.util.UUID;

public interface ProjectionAnimeGenres {

    UUID getAnimeid();
    String getName();
    String getType();
    String getImageurl();

    @JsonIgnoreProperties("animes")
    Set<ProjectionAuthor> getAuthors();

}
