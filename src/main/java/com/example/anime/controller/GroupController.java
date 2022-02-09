package com.example.anime.controller;

import com.example.anime.domain.dto.RequestUserId;
import com.example.anime.domain.dto.ResponseList;
import com.example.anime.domain.model.Group;
import com.example.anime.domain.model.Member;
import com.example.anime.domain.model.User;
import com.example.anime.domain.model.projection.ProjectionGroup;
import com.example.anime.domain.model.projection.ProjectionMemberInfo1;
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

    @PostMapping("/{id}/user/")
    public ResponseEntity<?> postUser(@PathVariable UUID id, @RequestBody RequestUserId requestAddUserToGroup) {
        if (userRepository.findByUserid(requestAddUserToGroup.userid) != null) {
            if (groupRepository.findByGroupid(id)!=null){
                Member member = new Member();
                User user=userRepository.findByUserid(requestAddUserToGroup.userid);
                Group group = groupRepository.findByGroupid(id);
                member.userid = user.userid;
                member.groupid = group.groupid;
                return ResponseEntity.ok().body(memberRepository.save(member));
            }else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el Grupo");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el usuario");
    }

    @GetMapping("/{id}/user/")
    public ResponseEntity<?> getMembers(@PathVariable UUID id) {
        if (groupRepository.findByGroupid(id)!=null) {
            Group group = groupRepository.findByGroupid(id);
            return ResponseEntity.ok().body(new ResponseList(groupRepository.findByGroupid(id, ProjectionMemberInfo1.class)));
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el Grupo");
        }
    }

    @DeleteMapping("/{id}/user/")
    public ResponseEntity<?> deleteMember(@PathVariable UUID id, @RequestBody RequestUserId requestDeleteMember) { // el requestBody es el del usuario
        if (groupRepository.findByGroupid(id)!=null) {
            if (userRepository.findByUserid(requestDeleteMember.userid) != null) {

                User user=new User();
                Group grupo=groupRepository.findByGroupid(id);//tienes el grupo

                boolean encontrado=false;
                for(User a:grupo.members){
                    if(a.userid.equals(requestDeleteMember.userid)){
                        user=a;
                        encontrado=true;
                    }
                }

                if (encontrado==true){
                    Group g=groupRepository.findByGroupid(id);
                    Member m=new Member();
                    m.groupid=g.groupid;
                    m.userid=requestDeleteMember.userid;
                    memberRepository.delete(m);

                    return ResponseEntity.ok().body("Usuario eliminado correctamente");
                }
                else{
                    System.out.println("El usuario no esta en el grupo");
                }
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el usuario");
            }
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el Grupo");
        }
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Error desconocido");
    }
}