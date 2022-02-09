package com.example.anime.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Set;
import java.util.UUID;
@JsonPropertyOrder({"animeid","name","type","description","year","imageurl","rating","authors","ratedBy"})
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
