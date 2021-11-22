package com.example.anime.controller;

import com.example.anime.domain.model.Anime;
import com.example.anime.repository.AnimeRepository;
import org.apache.coyote.Response;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/anime")
public class AnimeController {

    private final AnimeRepository animeRepository;
    public AnimeController(AnimeRepository animeRepository) { this.animeRepository = animeRepository; }

    @GetMapping("/")
    public List<Anime> findAllMovies(Authentication authentication) {
        return animeRepository.findAll();
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> getAnime(@RequestBody Anime anime, Authentication authentication) {
        return ResponseEntity.ok().body(animeRepository.save(anime));
    }
}
