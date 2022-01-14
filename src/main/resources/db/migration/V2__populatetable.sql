INSERT INTO anime(name, description, type, year, imageurl) VALUES
    ('One Piece', 'Random pirates', 'Shonen', 1998, '/images/123');

INSERT INTO author (name, imageurl) values
    ('Author 1', 'images/1'),
    ('Author 2', 'images/2');

INSERT INTO anime_author values
    ((SELECT animeid FROM anime WHERE name='One Piece'), (SELECT authorid FROM author WHERE name='Author 1'));

INSERT INTO genre(label, image) values
    ('Genre One', '/files/5312cdc8-6832-405b-b93f-4107d9f34b14');

INSERT INTO anime_genre values
    ((SELECT animeid FROM anime WHERE name='One Piece'), (SELECT genreid FROM genre WHERE label='Genre One'));

CREATE EXTENSION IF NOT EXISTS pgcrypto;
INSERT INTO usser (username, password) VALUES ('user', crypt('pass', gen_salt('bf')));