INSERT ignore INTO categoria (id,nome) values (1,'salgado');
INSERT ignore INTO categoria (id,nome) values (2,'Marmita');
INSERT ignore INTO categoria (id,nome) values (3,'Caldo');

INSERT ignore INTO pessoa(id,nome) VALUES (1,'Cliente');

INSERT ignore INTO lancamento(descricao,data_vencimento,data_pagamento,valor,observacao,tipo,id_categoria,id_pessoa) values (null,null,'2019-05-03',13.50,null,'RECEITA',1,1);