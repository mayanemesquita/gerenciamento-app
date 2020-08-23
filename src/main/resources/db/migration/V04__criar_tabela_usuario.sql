CREATE TABLE usuario
(
    codigo   BIGINT(20) PRIMARY KEY,
    nome     VARCHAR(50)  NOT NULL,
    username VARCHAR(50)  NOT NULL,
    password VARCHAR(200) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;