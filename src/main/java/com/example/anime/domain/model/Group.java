package com.example.anime.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "groupp")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID groupid;
    public UUID adminid;
    public String name;

    @ManyToMany
    @JoinTable(name = "users_group", joinColumns = @JoinColumn(name = "groupid"), inverseJoinColumns = @JoinColumn(name = "userid"))
    @JsonIgnoreProperties("group")
    public Set<User> members;

}
