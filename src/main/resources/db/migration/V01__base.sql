CREATE TABLE usuario (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome_completo VARCHAR(300),
	apelido VARCHAR(80) NOT NULL,
    email VARCHAR(300) NOT NULL,
    senha VARCHAR(300) NOT NULL,
    criado_em DATETIME NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE categoria (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    icon_tag TEXT NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE anuncio (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    id_usuario BIGINT(20) NOT NULL,
    id_categoria BIGINT(20) NOT NULL,
    titulo VARCHAR(200) NOT NULL,
    descricao TEXT,
    preco Decimal(10,2),
	cep CHAR(8) NOT NULL,
	UF CHAR(2),
    ativo BOOLEAN NOT NULL DEFAULT 0,
    criado_em DATETIME NOT NULL,
    atualizado_em DATETIME,
    
    FOREIGN KEY(id_usuario) REFERENCES usuario(id),
    FOREIGN KEY(id_categoria) REFERENCES categoria(id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE foto (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    id_anuncio BIGINT(20) NOT NULL,
    
    FOREIGN KEY(id_anuncio) REFERENCES anuncio(id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;


-- INSERTS

INSERT INTO categoria VALUES (NULL, 'Autos e peças', '<i class="fas fa-car"></i>');
INSERT INTO categoria VALUES (NULL, 'Eletrônicos e celulares', '<i class="fas fa-desktop"></i>');
INSERT INTO categoria VALUES (NULL, 'Imóveis', '<i class="fas fa-warehouse"></i>');
INSERT INTO categoria VALUES (NULL, 'Para a sua casa', '<i class="fas fa-couch"></i>');
INSERT INTO categoria VALUES (NULL, 'Música e hobbies', '<i class="fas fa-guitar"></i>');
INSERT INTO categoria VALUES (NULL, 'Esportes e lazer', '<i class="fas fa-football-ball"></i>');
INSERT INTO categoria VALUES (NULL, 'Artigos infantis', '<i class="fas fa-baby-carriage"></i>');
INSERT INTO categoria VALUES (NULL, 'Animais de estimação', '<i class="fas fa-paw"></i>');
INSERT INTO categoria VALUES (NULL, 'Moda e beleza', '<i class="fas fa-tshirt"></i>');
INSERT INTO categoria VALUES (NULL, 'Agro e indústria', '<i class="fas fa-tractor"></i>');
INSERT INTO categoria VALUES (NULL, 'Comércio e escritório', '<i class="fas fa-store-alt"></i>');
INSERT INTO categoria VALUES (NULL, 'Serviços', '<i class="fas fa-hammer"></i>');
INSERT INTO categoria VALUES (NULL, 'Vagas de emprego', '<i class="far fa-handshake"></i>');
INSERT INTO categoria VALUES (NULL, 'Outros', '<i class="fas fa-poo"></i>');