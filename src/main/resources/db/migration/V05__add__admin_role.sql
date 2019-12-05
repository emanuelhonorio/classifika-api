CREATE TABLE IF NOT EXISTS role (
	id INT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL
) Engine = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS usuario_role (
	id  BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    id_usuario BIGINT(20) NOT NULL,
    id_role INT NOT NULL,
    
    FOREIGN KEY(id_usuario) REFERENCES usuario(id),
    FOREIGN KEY(id_role) REFERENCES `role`(id)
) Engine = InnoDB DEFAULT CHARSET = utf8;

# INSERTS 
INSERT INTO role(id, nome) VALUES (1, 'ROLE_USER');
INSERT INTO role(id, nome) VALUES (2, 'ROLE_ADMIN');

# ADMIN
INSERT INTO usuario(apelido, email, senha, criado_em) VALUES ('Administrador', 'admin@classifika.com', '$2a$10$fJLYTN7HFte84OU/vD/Dp.AmFEfxNS5/6dpYPQ/CW3mMSty8Ycj72', now());
INSERT INTO usuario_role(id_usuario, id_role) VALUES (LAST_INSERT_ID(), 1);
INSERT INTO usuario_role(id_usuario, id_role) VALUES (LAST_INSERT_ID(), 2);