CREATE TABLE usuario
(
    codigo BIGINT(20) PRIMARY KEY,
    nome   VARCHAR(50)  NOT NULL,
    email  VARCHAR(50)  NOT NULL,
    senha  VARCHAR(200) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;