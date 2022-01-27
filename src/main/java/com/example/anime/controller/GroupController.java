package com.example.anime.controller;

import com.example.anime.domain.dto.RequestAddUserToGroup;
import com.example.anime.domain.dto.ResponseList;
import com.example.anime.domain.model.Member;
import com.example.anime.domain.model.projection.ProjectionGroup;
import com.example.anime.repository.GroupRepository;
import com.example.anime.repository.MemberRepository;
import com.example.anime.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MemberRepository memberRepository;


    @GetMapping("/")
    public ResponseEntity<?> todos(){
        return ResponseEntity.ok().body(new ResponseList(groupRepository.findBy(ProjectionGroup.class)));
    }

    @PostMapping("/{id}/user")
    public ResponseEntity<?> postUser(@PathVariable UUID id, @RequestBody RequestAddUserToGroup requestAddUserToGroup) {
        if (userRepository.findByUserid(requestAddUserToGroup.userid) != null) {
            if (groupRepository.findByGroupid(id)!=null){
                Member member = new Member();
                member.userid = requestAddUserToGroup.userid;
                member.groupid = id;
                return ResponseEntity.ok().body(memberRepository.save(member));
            }else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el Grupo");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el usuario");
    }

    @GetMapping("/{id}/users")
    public ResponseEntity<?> getMembers(@PathVariable UUID id) {
        if (groupRepository.findByGroupid(id)!=null) {
         //   return ResponseEntity.ok().body(new ResponseList(memberRepository.findBy())); //pasar la lista de miembros findByGroupid como return
        }
        return ResponseEntity.ok().body(new ResponseList(groupRepository.findBy(ProjectionGroup.class)));
    }
}