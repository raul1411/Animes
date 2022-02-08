CREATE TABLE anime (
    animeid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    name text,
    description text,
    type text,
    year int,
    imageurl text,
    rating float DEFAULT 0);

CREATE TABLE file (
    fileid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    contenttype text,
    bytes bytea);

CREATE TABLE usser (
    userid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    username varchar(24) NOT NULL UNIQUE,
    password varchar(255) NOT NULL,
    role varchar(10),
    enabled boolean DEFAULT true);

CREATE TABLE author (
    authorid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    name text,
    imageurl text
);

CREATE TABLE anime_author (
    animeid uuid REFERENCES anime(animeid) ON DELETE CASCADE,
    authorid uuid REFERENCES author(authorid) ON DELETE CASCADE,
    PRIMARY KEY (animeid, authorid)
);

CREATE TABLE genre (
    genreid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    label text,
    image text
);

CREATE TABLE anime_genre (
    anime_id uuid REFERENCES anime(animeid) ON DELETE CASCADE,
    genre_id uuid REFERENCES genre(genreid) ON DELETE CASCADE,
    PRIMARY KEY (anime_id, genre_id)
);

CREATE TABLE favorite (
    userid uuid REFERENCES usser(userid) ON DELETE CASCADE,
    animeid uuid REFERENCES anime(animeid) ON DELETE CASCADE,
    PRIMARY KEY (userid, animeid));


-----------------------------------------------------------------------

CREATE TABLE groupp (
    groupid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    adminid uuid REFERENCES usser(userid) ON DELETE CASCADE,
    name text
);

CREATE TABLE users_group (
    userid uuid REFERENCES usser(userid) ON DELETE CASCADE,
    groupid uuid REFERENCES groupp(groupid) ON DELETE CASCADE,
    PRIMARY KEY (userid, groupid));

