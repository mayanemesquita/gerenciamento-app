CREATE TABLE pessoa(
       codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
       nome VARCHAR (50) NOT NULL,
       status BOOLEAN,
       logradouro VARCHAR (100),
       numero VARCHAR (20),
       complemento VARCHAR (50),
       bairro VARCHAR (50),
       cidade VARCHAR (50),
       estado VARCHAR (2),
       cep VARCHAR(10)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;