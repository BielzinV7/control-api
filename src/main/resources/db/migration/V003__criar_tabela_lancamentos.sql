CREATE TABLE lancamento(

    codigo BIGINT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(50),
    data_vencimento DATE,
    data_pagamento DATE,
    valor DECIMAL(10,2) NOT NULL,
    observacao VARCHAR(100),
    tipo VARCHAR(20) NOT NULL,
    codigo_categoria BIGINT(10) NOT NULL,
    codigo_pessoa BIGINT(10) NOT NULL,
    FOREIGN KEY (codigo_categoria) REFERENCES categoria(codigo),
    FOREIGN KEY (codigo_pessoa) REFERENCES pessoa(codigo)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;