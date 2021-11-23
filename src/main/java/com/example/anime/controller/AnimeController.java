package com.example.anime.controller;

import com.example.anime.domain.model.Anime;
import com.example.anime.repository.AnimeRepository;
import com.example.anime.domain.dto.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/anime")
public class AnimeController {

    @Autowired
    private final AnimeRepository animeRepository;
    public AnimeController(AnimeRepository animeRepository) { this.animeRepository = animeRepository; }

    @GetMapping("/")
    public List<Anime> todos(){
        return animeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAllAnimes(@PathVariable UUID id) {
        Anime file = animeRepository.findById(id).orElse(null);

        if(file==null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Error.message("No s'ha trobat l'anime amb ID: " + id));
        return ResponseEntity.ok().body(file);
    }

    @PostMapping("/")
    public Anime getAnime(@RequestBody Anime anime) {
        return animeRepository.save(anime);
    }
}
