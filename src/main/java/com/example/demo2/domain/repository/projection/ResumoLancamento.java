package com.example.demo2.domain.repository.projection;

import com.example.demo2.domain.model.TipoLancamento;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Data
public class ResumoLancamento {

    private Long codigo;
    private String descricao;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private BigDecimal valor;
    private TipoLancamento tipo;
    private String categoria;
    private String pessoa;

}

