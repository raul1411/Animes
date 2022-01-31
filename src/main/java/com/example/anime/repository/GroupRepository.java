package com.example.anime.repository;

import com.example.anime.domain.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface GroupRepository extends JpaRepository<Group, UUID> {

    <T> List<T> findBy(Class<T> type);
    //User findBy(UUID id);

  //  User findByMemberName(String username);

 //   <T> List<T> findByUsername(String username, Class<T> type);

  //  <T> List<T> findByGroupid(UUID id, Class<T> type);

    Group findByGroupid(UUID id);
    <T> List<T> findByGroupid(UUID id, Class<T> type);
}
