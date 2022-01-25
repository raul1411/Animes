INSERT INTO anime(name, description, type, year, imageurl) VALUES
    ('One Piece', 'Random pirates', 'Shonen', 1998, '/images/123'),
    ('One Piece 2', 'Random pirates 2', 'Shonen', 1998, '/images/123');

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
INSERT INTO usser (username, password) VALUES
    ('Raul', crypt('pass', gen_salt('bf'))),
    ('Oihane', crypt('pass2', gen_salt('bf'))),
    ('Bryan', crypt('pass3', gen_salt('bf'))),
    ('Montse', crypt('pass4', gen_salt('bf')));

INSERT INTO groupp(name) VALUES ('Grupo de Bryan', 'Grupo de Raúl');