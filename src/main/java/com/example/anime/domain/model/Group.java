package com.example.anime.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "groupp")
public class Group {

    @Id
    public UUID groupid;
    public UUID adminid;
    public String name;

}
