package com.example.anime.domain.model.compositekeys;

import java.io.Serializable;
import java.util.UUID;

public class ClaveUseridUserid implements Serializable {
    UUID transmitterid;
    UUID receiverid;
    UUID messageid;
}
