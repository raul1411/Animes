package com.example.anime.repository;

import com.example.anime.domain.model.Message;
import com.example.anime.domain.model.User;
import com.example.anime.domain.model.projection.ProjectionMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {
    <T> List<T> findBy(Class<T> type);
    ProjectionMessage findByMessageid(UUID id);

}
