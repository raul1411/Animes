CREATE TABLE anime (
    animeid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    name text,
    description text,
    type text,
    year int,
    image text
);

INSERT INTO anime(name, description, type, year, image) VALUES
('One Piece', 'Random pirates', 'Shonen', 1998, '/images/123');