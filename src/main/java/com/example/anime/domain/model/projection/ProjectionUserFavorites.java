package com.example.anime.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

public interface ProjectionUserFavorites {

    @JsonIgnoreProperties("favoritedby")
    Set<ProjectionAnimeIdNameImage> getFavorites();
}
