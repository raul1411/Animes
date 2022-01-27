package com.example.anime.controller;

import com.example.anime.domain.dto.ResponseError;
import com.example.anime.domain.dto.ResponseList;
import com.example.anime.domain.model.Author;
import com.example.anime.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping("/")
    public ResponseEntity<?> todos(){
        return ResponseEntity.ok().body(new ResponseList(authorRepository.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAuthorsById(@PathVariable UUID id) {
        Author file = authorRepository.findById(id).orElse(null);

        if(file==null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseError.message("No s'ha trobat l'autor amd id: " + id));
        return ResponseEntity.ok().body(file);
    }

}
