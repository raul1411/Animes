package com.example.anime.domain.dto;

import com.example.anime.domain.model.File;

import java.util.List;

public class ResponseFile {

    public List<File> result;

    public ResponseFile(List<File> result) {
        this.result = result;
    }
}
