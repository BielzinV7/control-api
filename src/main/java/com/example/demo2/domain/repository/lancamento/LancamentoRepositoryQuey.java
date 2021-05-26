package com.example.demo2.domain.repository.lancamento;

import com.example.demo2.domain.model.Lancamento;
import com.example.demo2.domain.repository.filter.LancamentoFilter;
import com.example.demo2.domain.repository.projection.ResumoLancamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LancamentoRepositoryQuey {

    Page<Lancamento> filtar(LancamentoFilter lancamentoFilter, Pageable pageable);

    Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable);
}
