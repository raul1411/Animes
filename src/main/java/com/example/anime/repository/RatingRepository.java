package com.example.anime.repository;

import com.example.anime.domain.model.Anime;
import com.example.anime.domain.model.Favorite;
import com.example.anime.domain.model.Rating;
import com.example.anime.domain.model.compositekeys.ClaveAnimeidUserid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RatingRepository extends JpaRepository<Rating, ClaveAnimeidUserid> {
    //<T> List<T> findBy(Class<T> type);
    Rating findByRatingid(ClaveAnimeidUserid id);

}
