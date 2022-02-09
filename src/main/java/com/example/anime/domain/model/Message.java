package com.example.anime.domain.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID messageid;
    public UUID transmitterid;

    public UUID receiverid;

    public String message;

//
//    @ManyToOne
//    @JoinColumn(name = "userid",  insertable = false , updatable = false)
//    public User receiver;



}