package com.example.demo2.dto;

import com.example.demo2.domain.model.TipoLancamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
@AllArgsConstructor
@Setter
@Getter
public class LancamentoEstatisticaDia {

    private TipoLancamento tipo;
    private LocalDate dia;
    private BigDecimal total;

}
