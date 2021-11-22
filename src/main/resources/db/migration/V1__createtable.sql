CREATE TABLE anime (
    animeid uuid NOT NULL DEFAULT  gen_random_uuid() PRIMARY KEY,
    name text,
    description text,
    type text,
    yearr int,
    image text
);

INSERT INTO anime(name, description, type, yearr, image) VALUES
('One Piece', 'Random pirates', 'Shonen', 1998, '/images/123');

CREATE TABLE usser (
    userid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    username varchar(24) NOT NULL UNIQUE,
    password varchar(255) NOT NULL,
    role varchar(10),
    enabled boolean DEFAULT true
  );

CREATE EXTENSION IF NOT EXISTS pgcrypto;
INSERT INTO usser (username, password) VALUES ('user', crypt('pass', gen_salt('bf')));