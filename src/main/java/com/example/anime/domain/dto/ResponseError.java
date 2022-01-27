package com.example.anime.domain.dto;

public class ResponseError {
    public String message;

    public ResponseError(String message) {
        this.message = message;
    }

    public static ResponseError message(String message) {
        return new ResponseError(message);
    }
}
