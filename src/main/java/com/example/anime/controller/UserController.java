package com.example.anime.controller;

import com.example.anime.domain.dto.RequestUserRegister;
import com.example.anime.domain.dto.ResponseList;
import com.example.anime.domain.dto.ResponseMessage;
import com.example.anime.domain.model.User;
import com.example.anime.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired private BCryptPasswordEncoder passwordEncoder;

    @PostMapping(path = "/register" )
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

    @GetMapping
    public ResponseEntity<?> getALl(){
        return ResponseEntity.ok().body(ResponseList.list(userRepository.findBy()));
    }

    @GetMapping("/register/web")
    public String hack(){
        return "<div style='display:flex;flex-direction:column;width:20em;gap:0.5em'>" +
                "<input name='username' id='username' placeholder='Username'>" +
                "<input id='password' type='password' placeholder='Password'>" +
                "<input type='button' value='Register' onclick='fetch(\"/users/register/\",{method:\"POST\",headers:{\"Content-Type\":\"application/json\"},body:`{\"username\":\"${username.value}\",\"password\":\"${password.value}\"}`})'></div>";
    }
}
