package com.example.anime.domain.dto;

import java.util.UUID;

public class FileDetails {
    public UUID fileid;
    public String contenttype;

    public FileDetails(UUID fileid, String contenttype) {
        this.fileid = fileid;
        this.contenttype = contenttype;
    }
}