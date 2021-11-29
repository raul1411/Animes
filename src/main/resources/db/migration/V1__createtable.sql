CREATE TABLE anime (
    animeid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    name text,
    description text,
    type text,
    year int,
    imageurl text);

INSERT INTO anime(name, description, type, year, imageurl) VALUES
('One Piece', 'Random pirates', 'Shonen', 1998, '/images/123');

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

CREATE TABLE doblador (
    dobladorid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    name text,
    imageurl text);

CREATE TABLE animedoblador (
    animeid uuid REFERENCES anime(animeid) ON DELETE CASCADE,
    dobladorid uuid REFERENCES doblador(dobladorid) ON DELETE CASCADE,
    PRIMARY KEY (animeid, dobladorid));