package com.example.anime.domain.model.projection;

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

    /*
    *    public String name;
    public String description;
    public String type;
    public int year;
    public String imageurl;

    * */

    @JsonIgnoreProperties("animes")
    Set<ProjectionAuthor> getAuthors();
}
