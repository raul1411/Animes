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