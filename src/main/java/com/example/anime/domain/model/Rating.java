package com.example.anime.domain.model;

import com.example.anime.domain.model.compositekeys.ClaveAnimeidUserid;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "rating")
@IdClass(ClaveAnimeidUserid.class)
public class Rating {
    @Id
    public UUID userid;
    @Id
    public UUID animeid;

    public float stars;

    @OneToMany
    @JoinTable(name = "anime", joinColumns = {@JoinColumn(name = "animeid"),  @JoinColumn(name = "userid")})
    public Anime anime;

    @OneToMany
    @JoinTable(name = "usser", joinColumns = {@JoinColumn(name = "userid"),  @JoinColumn(name = "animeid")})
    public User user;


}
