CREATE TABLE lancamento(

    codigo BIGINT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(50),
    data_vencimento DATE,
    data_pagamento DATE NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    observacao VARCHAR(100),
    tipo VARCHAR(20) NOT NULL,
    id_categoria BIGINT(10) NOT NULL,
    id_pessoa BIGINT(10) NOT NULL,
    FOREIGN KEY (id_categoria) REFERENCES categoria(id),
    FOREIGN KEY (id_pessoa) REFERENCES pessoa(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;