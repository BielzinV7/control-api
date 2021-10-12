package com.example.demo2.domain.repository.lancamento;

import com.example.demo2.domain.model.Lancamento;
import com.example.demo2.domain.repository.filter.LancamentoFilter;
import com.example.demo2.domain.repository.projection.ResumoLancamento;
import com.example.demo2.dto.LancamentoEstatisticaCategoria;
import com.example.demo2.dto.LancamentoEstatisticaDia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface LancamentoRepositoryQuey {

    List<LancamentoEstatisticaCategoria> porCategoria(LocalDate mesReferencia);

    List<LancamentoEstatisticaDia> porDia(LocalDate mesReferencia);

    Page<Lancamento> filtar(LancamentoFilter lancamentoFilter, Pageable pageable);

    Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable);
}
