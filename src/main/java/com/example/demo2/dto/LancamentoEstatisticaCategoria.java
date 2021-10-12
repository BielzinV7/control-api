package com.example.demo2.dto;

import com.example.demo2.domain.model.Categoria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Setter
@Getter
public class LancamentoEstatisticaCategoria {

    private Categoria categoria;
    private BigDecimal total;
    private LocalDate dataVencimento;

}
