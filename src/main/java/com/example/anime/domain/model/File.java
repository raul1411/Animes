package com.example.anime.domain.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "file")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID fileid;

    public String contenttype;

    @Type(type="org.hibernate.type.BinaryType")
    @Lob
    public byte[] bytes;
}