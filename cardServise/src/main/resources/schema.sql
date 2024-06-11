CREATE TABLE IF NOT EXISTS card
(
    ID              binary(16)      PRIMARY KEY,
    card_number     varchar(16) NOT NULL unique,
    is_using        bit         NOT NULL
);
