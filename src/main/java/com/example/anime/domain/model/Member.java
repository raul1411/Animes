package com.example.anime.domain.model;

import com.example.anime.domain.model.compositekeys.ClaveUseridGroupid;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "users_group")
@IdClass(ClaveUseridGroupid.class)
public class Member {
    @Id
    public UUID userid;

    @Id
    public UUID groupid;
}
