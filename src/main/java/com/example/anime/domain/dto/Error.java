package com.example.anime.domain.dto;

public class Error {
    public String message;

    public Error(String message) {
        this.message = message;
    }

    public static Error message(String message) {
        return new Error(message);
    }
}
