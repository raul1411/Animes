package com.example.anime.controller;

import com.example.anime.domain.dto.ListResult;
import com.example.anime.domain.model.Anime;
import com.example.anime.repository.AnimeRepository;
import org.apache.coyote.Response;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/anime")
public class AnimeController {

    @Autowired
    private final AnimeRepository animeRepository;
    public AnimeController(AnimeRepository animeRepository) { this.animeRepository = animeRepository; }

    @GetMapping("/")
    public ResponseEntity<?> findAllAnimes(Authentication authentication) {
        return ResponseEntity.ok().body(ListResult.list(animeRepository.findAll()));
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> getAnime(@RequestBody Anime anime, Authentication authentication) {
        return ResponseEntity.ok().body(animeRepository.save(anime));
    }
}
