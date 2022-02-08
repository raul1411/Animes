package com.example.anime.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "anime")
public class Anime {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID animeid;

    public String name;
    public String description;
    public String type;
    public int year;
    public String imageurl;
    public float rating;

    @ManyToMany
    @JoinTable(name = "anime_author", joinColumns = @JoinColumn(name = "animeid"), inverseJoinColumns = @JoinColumn(name = "authorid"))
    @JsonIgnoreProperties("animes")
    public Set<Author> authors;

    @ManyToMany
    @JoinTable(name = "anime_genre", joinColumns = @JoinColumn(name = "anime_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @JsonIgnoreProperties("animes")
    public Set<Genre> genres;

    @ManyToMany
    @JoinTable(name = "favorite", joinColumns = @JoinColumn(name = "animeid"), inverseJoinColumns = @JoinColumn(name = "userid"))
    @JsonIgnoreProperties({"password", "enabled", "favorites"})
    public Set<User> favoritedby;

    @OneToMany(mappedBy = "anime")
    public Set<Rating> ratedBy = new HashSet<>();

    public void setRating(float newRatingValue){
        float total;
        float value =0, average =0;
        //si no tiene ratings, el rating del anime se pone al primer rating que entra
        if(this.ratedBy.isEmpty()){
            this.rating = newRatingValue;
        }else {
            //sumar los valores de estrellas de cada rating del anime
            for (Rating rating : this.ratedBy){
                value +=rating.stars;
            }
            value +=newRatingValue;
            average = value/(this.ratedBy.size()+1);
            this.rating = average;
        }
    }



}