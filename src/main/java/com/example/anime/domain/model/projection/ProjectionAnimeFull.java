package com.example.anime.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;
import java.util.UUID;
//@JsonPropertyOrder({"campo1","campo2","campo3"})
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
    Set<ProjectionRatingUseridStars> getRatedBy();
}
