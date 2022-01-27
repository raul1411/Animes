package com.example.anime.controller;

import com.example.anime.domain.dto.ResponseError;
import com.example.anime.domain.dto.ResponseList;
import com.example.anime.domain.model.Anime;
import com.example.anime.domain.model.projection.ProjectionAnimeIdNameImage;
import com.example.anime.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/animes")
public class AnimeController {

    @Autowired
    private final AnimeRepository animeRepository;
    public AnimeController(AnimeRepository animeRepository) { this.animeRepository = animeRepository; }

    @GetMapping("/")
    public ResponseEntity<?> todos(){
        return ResponseEntity.ok().body(new ResponseList(animeRepository.findBy(ProjectionAnimeIdNameImage.class)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAnimesById(@PathVariable UUID id) {
        Anime file = animeRepository.findById(id).orElse(null);

        if(file==null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseError.message("No s'ha trobat l'anime amd id: " + id));
        return ResponseEntity.ok().body(file);
    }

    @PostMapping("/")
    public ResponseEntity<?> createAnime(@RequestBody Anime anime) {
        for (Anime a : animeRepository.findAll()) {
            if (a.name.equals(anime.name)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(ResponseError.message("Ja existeix un anime amb el nom '" + anime.name + "'"));
            }
        }
        animeRepository.save(anime);
        return ResponseEntity.ok().body(anime);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnimes(@PathVariable UUID id) {
        Anime file = animeRepository.findById(id).orElse(null);

        if(file==null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseError.message("No s'ha trobat l'anime amd id: " + id));

        animeRepository.delete(file);
        return ResponseEntity.ok().body(ResponseError.message("S'ha eliminat l'anime amd id '" + id + "'"));
    }
}
