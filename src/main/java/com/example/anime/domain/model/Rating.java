package com.example.anime.domain.model;

import com.example.anime.domain.model.compositekeys.ClaveAnimeidUserid;

import javax.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "animeid", insertable = false , updatable = false)
    public Anime anime;

    @ManyToOne
    @JoinColumn(name = "userid",insertable = false , updatable = false)
    public User user;
    
}
