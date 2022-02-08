package com.example.anime.domain.model.projection;

import com.example.anime.domain.model.Rating;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;
import java.util.UUID;

public interface ProjectionAnimeFull {
    UUID getAnimeid();
    String getName();
    String getDescription();
    String getType();
    int getYear();
    String getImageurl();
    float getRating();



    @JsonIgnoreProperties("animes")
    Set<ProjectionAuthor> getAuthors();

    @JsonIgnoreProperties({"user","anime"})
    Set<Rating> getRatedBy();
}
