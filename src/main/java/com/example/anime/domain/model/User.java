package com.example.anime.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "usser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID userid;

    public String username;
    public String password;
    public boolean enabled;

    @ManyToMany(mappedBy = "favoritedby")
    public Set<Anime> favorites;

    @ManyToMany(mappedBy = "members")
    public Set<Group> group;

    @OneToMany(mappedBy = "user")
    public Set<Rating> ratings = new HashSet<>();

//    @ManyToMany
//    @JoinTable(name = "message", joinColumns = {@JoinColumn(name = "userid"), @JoinColumn(name = "receiverid")})
//    public Set<Message> receivedmessages = new HashSet<>();

    @OneToMany(mappedBy = "receiver")
    public Set<Message> receivedmessages = new HashSet<>();

//    @ManyToMany
//    @JoinTable(name = "message", joinColumns = {@JoinColumn(name = "userid"), @JoinColumn(name = "transmitterid")})
//    public Set<Message> sentmessages = new HashSet<>();







}