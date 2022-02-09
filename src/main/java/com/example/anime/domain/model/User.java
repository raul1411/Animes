package com.example.anime.domain.model;

import javax.persistence.*;
import java.util.*;

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

    //-----------------------------

    //   1 .. *         1
    // M --------------- U
    //

//    @OneToMany(mappedBy = "receiver")
//    public List<Message> receivedmessages;






}