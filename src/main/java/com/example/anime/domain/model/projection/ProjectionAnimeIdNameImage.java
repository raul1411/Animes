package com.example.anime.domain.model.projection;

import java.util.UUID;

public interface ProjectionAnimeIdNameImage {
    UUID getAnimeid();
    String getName();
    String getImageurl();
}