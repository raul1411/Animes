package com.example.anime.domain.model.projection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.UUID;

@JsonPropertyOrder({"transmitterid","message"})
public interface ProjectionMessage {
    UUID getTransmitterid();
    String getMessage();



}
