package com.example.anime.controller;

import com.example.anime.domain.dto.*;
import com.example.anime.domain.model.Anime;
import com.example.anime.domain.model.Rating;
import com.example.anime.domain.model.compositekeys.ClaveAnimeidUserid;
import com.example.anime.domain.model.projection.ProjectionAnimeFull;
import com.example.anime.domain.model.projection.ProjectionAnimeIdNameImage;
import com.example.anime.repository.AnimeRepository;
import com.example.anime.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/animes")
public class AnimeController {

    @Autowired
    private final AnimeRepository animeRepository;

    @Autowired
    private final RatingRepository ratingRepository;

    public AnimeController(AnimeRepository animeRepository, RatingRepository ratingRepository) {
        this.animeRepository = animeRepository;
        this.ratingRepository = ratingRepository;
    }

    @GetMapping("/")
    public ResponseEntity<?> todos() {
        return ResponseEntity.ok().body(new ResponseList(animeRepository.findBy(ProjectionAnimeFull.class)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAnimesById(@PathVariable UUID id) {
        ProjectionAnimeFull anime = animeRepository.findByAnimeid(id, ProjectionAnimeFull.class);

        if (anime == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseError.message("No s'ha trobat l'anime amd id: " + id));
        return ResponseEntity.ok().body(anime);
    }

    //ESTO
    @GetMapping("/search/")
    public ResponseEntity<?> findAnimesBySearch(@RequestBody RequestSearch requestSearch) {
        List<ProjectionAnimeIdNameImage> animes = animeRepository.findByNameContaining(requestSearch.name, ProjectionAnimeIdNameImage.class);

        return ResponseEntity.ok().body(new ResponseList(animes));
    }

    @PostMapping("/")
    public ResponseEntity<?> createAnime(@RequestBody RequestAnime requestAnime) {
        for (Anime a : animeRepository.findAll()) {
            if (a.name.equals(requestAnime.name)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(ResponseError.message("Ja existeix un anime amb el nom '" + requestAnime.name + "'"));
            }
        }

        Anime anime = new Anime();

        anime.name = requestAnime.name;
        anime.description = requestAnime.description;
        anime.year = requestAnime.year;
        anime.imageurl = requestAnime.imageurl;
        anime.type = requestAnime.type;

        animeRepository.save(anime);
        return ResponseEntity.ok().body(requestAnime);
    }

    @PostMapping("/rating/{id}")
    public ResponseEntity<?> postRating(@PathVariable UUID idAnime, @RequestBody RequestRating requestRating) {
        List<Rating> ratingsDelAnime;
        if (requestRating != null) {
            if (requestRating.rating < 0 || requestRating.rating > 10) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(ResponseError.message("La valoració ha de ser del 0 al 10!"));
            }else {
                ClaveAnimeidUserid clave = new ClaveAnimeidUserid();
                clave.animeid = idAnime;
                clave.userid = requestRating.userid;
                //Lista de todos los ratings del anime escogido:
                ratingsDelAnime = ratingRepository.findByRatingid(clave).ratings;
                       // animeRepository.findByAnimeid(idAnime).ratings;
                //valor del nuevo rating a añadir
                float nuevoRating = requestRating.rating;
                //Hacer media
                float valorTotalRatings =0f;
                for (Rating rating: ratingsDelAnime){
                    valorTotalRatings+=rating.stars;
                }
                valorTotalRatings+=nuevoRating;
                float mediaTotalRating = valorTotalRatings/ratingsDelAnime.size();
                animeRepository.findByAnimeid(idAnime).rating =mediaTotalRating;
                return ResponseEntity.ok().body(requestRating);
            }
        }else {
            return null;
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnimes(@PathVariable UUID id) {
        Anime anime = animeRepository.findByAnimeid(id);

        if (anime == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseError.message("No s'ha trobat l'anime amd id: " + id));

        animeRepository.delete(anime);

        return ResponseEntity.ok().body(ResponseError.message("S'ha eliminat l'anime amd id '" + id + "'"));
    }
}
