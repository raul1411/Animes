package com.example.anime.domain.model.projection;

import com.example.anime.domain.model.Message;

import java.util.Set;
import java.util.UUID;

public interface ProjectionUser {
    UUID getUserid();
    String getUsername();


    Set<Message> getReceivedmesssages();
}