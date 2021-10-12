INSERT ignore INTO categoria (codigo,nome) values (1,'Salgados');
INSERT ignore INTO categoria (codigo,nome) values (2,'Marmitas');
INSERT ignore INTO categoria (codigo,nome) values (3,'Bebidas');
INSERT ignore INTO categoria (codigo,nome) values (4,'Gelados');
INSERT ignore INTO categoria (codigo,nome) values (5,'Outras');

INSERT ignore INTO pessoa(codigo,nome) VALUES (1,'Cliente');

INSERT ignore INTO lancamento(descricao,data_vencimento,data_pagamento,valor,observacao,tipo,codigo_categoria,codigo_pessoa) values (null,null,'2019-05-03',13.50,null,'RECEITA',1,1);