package com.example.anime.repository;

import com.example.anime.domain.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {
    <T> List<T> findBy(Class<T> type);

}
