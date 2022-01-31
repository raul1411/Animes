package com.example.anime.domain.model.projection;

import java.util.Set;
import java.util.UUID;

public interface ProjectionMemberInfo1 {

    UUID getGroupid();
    UUID getAdminid();
    String getName();

    Set<ProjectionMemberInfo2> getMembers();

}
