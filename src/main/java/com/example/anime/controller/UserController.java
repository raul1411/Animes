package com.example.anime.controller;

import com.example.anime.domain.dto.Error;
import com.example.anime.domain.dto.*;
import com.example.anime.domain.model.Favorite;
import com.example.anime.domain.model.User;
import com.example.anime.domain.model.projection.ProjectionUserFavorites;
import com.example.anime.repository.FavoriteRepository;
import com.example.anime.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired private BCryptPasswordEncoder passwordEncoder;
    @Autowired private FavoriteRepository favRepository;

    @PostMapping(path = "/register")
    public ResponseEntity<?> register(@RequestBody RequestUserRegister requestUserRegister) {

        if (userRepository.findByUsername(requestUserRegister.username) == null) {
            User user = new User();
            user.username = requestUserRegister.username;
            user.password = passwordEncoder.encode(requestUserRegister.password);
            user.enabled = true;
            return ResponseEntity.ok().body(userRepository.save(user).userid.toString());
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ResponseMessage.message("Nom d'usuari no disponible"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable UUID id){
        User file = userRepository.findById(id).orElse(null);
        if(file==null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Error.message("No s'ha trobat l'usuari amd id: " + id));
        return ResponseEntity.ok().body(ResponseList.list(userRepository.findByUserid(id, ProjectionUserFavorites.class)));
    }

    @PostMapping("/favorites")
    public ResponseEntity<?> addFavorite(@RequestBody FavoriteAnime favoriteAnime, Authentication authentication) {
        if (authentication != null) {

            User aUser = userRepository.findByUsername(authentication.getName());

            if (aUser != null) {
                Favorite favorite = new Favorite();
                favorite.animeid = favoriteAnime.animeid;
                favorite.userid = aUser.userid;
                favRepository.save(favorite);
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ResponseMessage.message("No autoritzat"));
    }

    @GetMapping("/favorites")
    public ResponseEntity<?> addFavorite(Authentication authentication) {

        return ResponseEntity.ok().body(userRepository.findByUsername(authentication.getName(), ProjectionUserFavorites.class));
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok().body(ResponseList.list(userRepository.findBy()));
    }

    @GetMapping("/register/web")
    public String hack(){
        return "<div style='display:flex;flex-direction:column;width:20em;gap:0.5em'>" +
                "<input name='username' id='username' placeholder='Username'>" +
                "<input id='password' type='password' placeholder='Password'>" +
                "<input type='button' value='Register' onclick='fetch(\"/users/register/\",{method:\"POST\",headers:{\"Content-Type\":\"application/json\"},body:`{\"username\":\"${username.value}\",\"password\":\"${password.value}\"}`})'></div>";
    }

    @DeleteMapping("/favorites")
    public ResponseEntity<?> deleteFavorite(@RequestBody FavoriteAnime requestFavorite, Authentication authentication) {
        if (authentication != null) {

            User aUser = userRepository.findByUsername(authentication.getName());

            if (aUser != null) {
                Favorite favorite = new Favorite();
                favorite.animeid = requestFavorite.animeid;
                favorite.userid = aUser.userid;
                favRepository.delete(favorite);
                return ResponseEntity.ok().body("S'ha eliminat dels favorits l'anime amd id " + favorite.animeid);
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ResponseMessage.message("No autoritzat"));
    }
}
