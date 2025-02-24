CREATE SCHEMA IF NOT EXISTS media_schema;

SET SEARCH_PATH TO media_schema;

CREATE TABLE IF NOT EXISTS media
(
    id              SERIAL PRIMARY KEY,
    name            VARCHAR(255) NOT NULL,
    description     TEXT,
    release_country TEXT,
    release_year    INT,
    cover_image_url TEXT,
    creator_id      BIGINT,
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    type            VARCHAR(50) DEFAULT 'FILM' CHECK (type IN ('FILM', 'SERIES'))
);

CREATE TABLE IF NOT EXISTS genres
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS media_genres
(
    media_id INT REFERENCES media (id) ON DELETE CASCADE,
    genre_id INT REFERENCES genres (id) ON DELETE CASCADE,
    PRIMARY KEY (media_id, genre_id)
);

CREATE TABLE IF NOT EXISTS users
(
    id         SERIAL PRIMARY KEY,
    username   VARCHAR(255) NOT NULL,
    first_name VARCHAR(255),
    last_name  VARCHAR(255)
);

INSERT INTO genres (name)
VALUES ('Драма'),
       ('Триллер'),
       ('Комедия'),
       ('Приключения'),
       ('Фэнтези'),
       ('Мелодрама'),
       ('Хоррор'),
       ('Детектив'),
       ('Антиутопия'),
       ('Постапокалипсис'),
       ('Фантастика'),
       ('Мистика'),
       ('Экшн'),
       ('Романтика'),
       ('Документальный'),
       ('Музыка'),
       ('Приключения, драма'),
       ('Семейный'),
       ('Исторический'),
       ('Криминал'),
       ('Сатирический'),
       ('Военный'),
       ('Спортивный');
