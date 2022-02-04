package com.example.anime.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
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



 /*   //    @JsonIgnoreProperties({"ratings"})

    @ManyToMany(mappedBy = "ratings")
    public List<Anime> rated;*/
}