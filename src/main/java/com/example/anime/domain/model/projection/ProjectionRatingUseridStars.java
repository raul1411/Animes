package com.example.anime.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.UUID;
@JsonPropertyOrder({"userid","stars"})
public interface ProjectionRatingUseridStars {
    UUID getUserid();
    float getStars();

}
