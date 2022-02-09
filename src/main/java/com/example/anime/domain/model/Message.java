package com.example.anime.domain.model;

import com.example.anime.domain.model.compositekeys.ClaveUseridUserid;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "message")
@IdClass(ClaveUseridUserid.class)
public class Message {
    @Id
    public UUID transmitterid;
    @Id
    public UUID receiverid;
    @Id
    public UUID messageid;

    public String message;


    @ManyToOne
    @JoinColumn(name = "userid",  insertable = false , updatable = false)
    public User receiver;



}