package com.example.anime.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "users_group")
public class UsersGroup {

    @Id
    public UUID userid;

    @Id
    public UUID groupid;

}
