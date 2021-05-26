package com.example.demo2.domain.repository;


import com.example.demo2.domain.model.Lancamento;
import com.example.demo2.domain.repository.lancamento.LancamentoRepositoryQuey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento,Long>, LancamentoRepositoryQuey {
}
