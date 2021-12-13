package com.example.anime.domain.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="doblador")
public class Doblador {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        public UUID dobladorid;
        public String name;
        public String imageurl;

        /*
        @ManyToMany(mappedBy = "dobladors")
        Set<Anime> animes;
*/
}

