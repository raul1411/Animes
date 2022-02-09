package com.example.anime.domain.model;

import com.example.anime.domain.model.compositekeys.ClaveAnimeidUserid;
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

    public String message;

//    @ManyToOne
//    @JoinColumn(name = "userid")
//    public User transmitter;

//    @ManyToOne
//    @JoinColumn(name = "userid")
//    public User receiver;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "address_id", referencedColumnName = "id")
//    private Address address;

    @OneToOne
    @JoinColumn(name = "receiverid", referencedColumnName = "userid",insertable = false , updatable = false)
    public User receiver;

}