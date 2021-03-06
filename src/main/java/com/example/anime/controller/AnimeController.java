package com.example.anime.controller;

import com.example.anime.domain.dto.*;
import com.example.anime.domain.model.Anime;
import com.example.anime.domain.model.Rating;
import com.example.anime.domain.model.projection.ProjectionAnimeFull;
import com.example.anime.domain.model.projection.ProjectionAnimeIdNameImage;
import com.example.anime.repository.AnimeRepository;
import com.example.anime.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
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


    @PostMapping("/rating")
    public ResponseEntity<?> postRating(@RequestBody RequestRating requestRating) {
        Set<Rating> ratingsDelAnime;
        if (requestRating != null) {
            if (requestRating.rating < 0 || requestRating.rating > 10) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(ResponseError.message("La valoraci?? ha de ser del 0 al 10!"));
            }else {
                animeRepository.findByAnimeid(requestRating.animeid).setRating(requestRating.rating);
                Rating newRating  = new Rating();
                newRating.userid = requestRating.userid;
                newRating.animeid = requestRating.animeid;
                newRating.stars = requestRating.rating;
                ratingRepository.save(newRating);
                return ResponseEntity.ok().body(requestRating);
            }
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseError.message("No has introduit tots els camps"));
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
