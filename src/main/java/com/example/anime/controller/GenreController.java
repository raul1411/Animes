package com.example.anime.controller;

import com.example.anime.domain.dto.Error;
import com.example.anime.domain.dto.ResponseList;
import com.example.anime.domain.model.Genre;
import com.example.anime.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    private final GenreRepository genreRepository;
    public GenreController(GenreRepository genreRepository) { this.genreRepository = genreRepository; }

    @GetMapping("/")
    public ResponseEntity<?> todos(){
        return ResponseEntity.ok().body(new ResponseList(genreRepository.findBy()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findGenresById(@PathVariable UUID id) {
        Genre file = genreRepository.findById(id).orElse(null);

        if(file==null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Error.message("No s'ha trobat l'genere amd id: " + id));
        return ResponseEntity.ok().body(file);
    }
}