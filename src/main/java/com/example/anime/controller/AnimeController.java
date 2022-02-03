package com.example.anime.controller;

import com.example.anime.domain.dto.*;
import com.example.anime.domain.model.Anime;
import com.example.anime.domain.model.projection.ProjectionAnimeFull;
import com.example.anime.domain.model.projection.ProjectionAnimeIdNameImage;
import com.example.anime.repository.AnimeRepository;
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
    public AnimeController(AnimeRepository animeRepository) { this.animeRepository = animeRepository; }

    @GetMapping("/")
    public ResponseEntity<?> todos(){
        return ResponseEntity.ok().body(new ResponseList(animeRepository.findBy(ProjectionAnimeFull.class)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAnimesById(@PathVariable UUID id) {
        ProjectionAnimeFull anime = animeRepository.findByAnimeid(id, ProjectionAnimeFull.class);

        if(anime == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseError.message("No s'ha trobat l'anime amd id: " + id));
        return ResponseEntity.ok().body(anime);
    }

    @GetMapping("/search/")
    public ResponseEntity<?> findAnimesBySearch(@RequestBody RequestSearch requestSearch) {
        List<ProjectionAnimeIdNameImage> animes = animeRepository.findByNameContaining(requestSearch.name, ProjectionAnimeIdNameImage.class);
        return ResponseEntity.ok(animes);
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
    public ResponseEntity<?> postRating(@PathVariable UUID id, @RequestBody RequestRating requestRating) {


        float avg = 0;
        if (requestRating.rating < 0 || requestRating.rating > 10) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ResponseError.message("La valoració ha de ser del 0 al 10!"));
        }
        else {
            //hay que usar el findby que devuelve un objeto anime por id
            //y con el rating de ese anime hacer la media del rating introducido con el actual que tiene
            Anime anime= animeRepository.findByAnimeid(id);
            avg = anime.rating+ requestRating.rating/2;
            anime.rating = avg;
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnimes(@PathVariable UUID id) {
        Anime anime = animeRepository.findByAnimeid(id);

        if (anime == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseError.message("No s'ha trobat l'anime amd id: " + id));

        animeRepository.delete(anime);

        return ResponseEntity.ok().body(ResponseError.message("S'ha eliminat l'anime amd id '" + id + "'"));
    }
}
