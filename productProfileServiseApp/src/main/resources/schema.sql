CREATE TABLE IF NOT EXISTS client_data
(
    UUID            binary(16)  PRIMARY KEY,
    name            varchar(25) NOT NULL,
    surname         varchar(25) NOT NULL,
    patronymic      varchar(25),
    telefone_mob     varchar(10)
);
CREATE TABLE IF NOT EXISTS product_data
(
    ID              binary(16)  PRIMARY KEY,
    name            varchar(40) NOT NULL,
    uuid_client     binary(16)  NOT NULL,
    number          varchar(16) NOT NULL,
    balance         numeric(10,2)  default 0,
    main_card       bit         NOT NULL,
    FOREIGN KEY (uuid_client) REFERENCES client_data(UUID)
);


