package com.example.anime.domain.dto;

import java.util.List;

public class ResponseListMessage {
    public int messages;
    public List<?> result;

    public ResponseListMessage(List<?> result, int messages) {
        this.result = result;
        this.messages = messages;
    }

    public static ResponseListMessage list(List<?> result, int messages ){
        return new ResponseListMessage(result, messages);
    }
}
