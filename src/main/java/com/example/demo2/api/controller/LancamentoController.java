package com.example.demo2.api.controller;


import com.example.demo2.domain.model.Lancamento;
import com.example.demo2.domain.repository.LancamentoRepository;
import com.example.demo2.domain.repository.filter.LancamentoFilter;
import com.example.demo2.domain.repository.projection.ResumoLancamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;


@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {

    final LancamentoRepository lancamentoRepository;

    public LancamentoController(LancamentoRepository lancamentoRepository) {
        this.lancamentoRepository = lancamentoRepository;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
    public Page<Lancamento> pesquisar(LancamentoFilter lancamentoFilter, Pageable pageable){

        return lancamentoRepository.filtar(lancamentoFilter,pageable);

    }

    @GetMapping(params = "resumo")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
    public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable) {
        return lancamentoRepository.resumir(lancamentoFilter, pageable);
    }

    @GetMapping("/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
    public ResponseEntity<Lancamento> buscar(@PathVariable Long codigo){

        Optional<Lancamento> lancamentoOpt = lancamentoRepository.findById(codigo);

        return lancamentoOpt.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());

    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO') and #oauth2.hasScope('write')")
    public ResponseEntity<Lancamento> adicionar(@Valid @RequestBody Lancamento lancamento, UriComponentsBuilder builder){

        Lancamento lancamentoSalvo = lancamentoRepository.save(lancamento);

        URI uri = builder.path("/lancamentos/{codigo}").buildAndExpand(lancamento.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(lancamentoSalvo);

    }

    @DeleteMapping("/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_REMOVER_LANCAMENTO') and #oauth2.hasScope('write')")
    public void deletar(@PathVariable Long codigo){

        lancamentoRepository.deleteById(codigo);

    }

}
