CREATE TABLE IF NOT EXISTS contato (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    telefone VARCHAR(30) NOT NULL,
    id_usuario BIGINT(20) NOT NULL,
    
    FOREIGN KEY(id_usuario) REFERENCES usuario(id)
) Engine = InnoDB DEFAULT CHARSET = utf8;

ALTER TABLE anuncio ADD COLUMN id_contato BIGINT(20) NOT NULL;
ALTER TABLE anuncio ADD FOREIGN KEY(id_contato) REFERENCES contato(id);