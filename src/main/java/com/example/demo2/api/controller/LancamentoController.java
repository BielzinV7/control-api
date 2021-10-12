package com.example.demo2.api.controller;


import com.example.demo2.api.controller.exceptionhandler.ControlExceptionHandler;
import com.example.demo2.domain.model.Lancamento;
import com.example.demo2.domain.repository.LancamentoRepository;
import com.example.demo2.domain.repository.filter.LancamentoFilter;
import com.example.demo2.domain.repository.projection.ResumoLancamento;
import com.example.demo2.dto.LancamentoEstatisticaCategoria;
import com.example.demo2.dto.LancamentoEstatisticaDia;
import com.example.demo2.service.LancamentoService;
import com.example.demo2.service.exception.PessoaInexistenteOuInativaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {

    @Autowired
    MessageSource messageSource;

    final LancamentoRepository lancamentoRepository;
    final LancamentoService lancamentoService;

    public LancamentoController(LancamentoRepository lancamentoRepository, LancamentoService lancamentoService) {
        this.lancamentoRepository = lancamentoRepository;
        this.lancamentoService = lancamentoService;
    }

    @GetMapping("/estatistica/por-dia")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
    public List<LancamentoEstatisticaDia> porDia(){
        return lancamentoRepository.porDia(LocalDate.now());
    }

    @GetMapping("/estatistica/por-categoria")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
    public List<LancamentoEstatisticaCategoria> porCategoria(){
        return lancamentoRepository.porCategoria(LocalDate.now());
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
    public Page<Lancamento> pesquisar(LancamentoFilter lancamentoFilter, Pageable pageable) {

        return lancamentoRepository.filtar(lancamentoFilter, pageable);

    }

    @GetMapping(params = "resumo")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
    public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable) {
        return lancamentoRepository.resumir(lancamentoFilter, pageable);
    }

    @GetMapping("/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
    public ResponseEntity<Lancamento> buscar(@PathVariable Long codigo) {

        Optional<Lancamento> lancamentoOpt = lancamentoRepository.findById(codigo);

        return lancamentoOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO') and #oauth2.hasScope('write')")
    public ResponseEntity<Lancamento> adicionar(@Valid @RequestBody Lancamento lancamento, UriComponentsBuilder builder) {

        Lancamento lancamentoSalvo = lancamentoRepository.save(lancamento);

        URI uri = builder.path("/lancamentos/{codigo}").buildAndExpand(lancamento.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(lancamentoSalvo);

    }

    @DeleteMapping("/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_REMOVER_LANCAMENTO') and #oauth2.hasScope('write')")
    public void deletar(@PathVariable Long codigo) {

        lancamentoRepository.deleteById(codigo);

    }

    @PutMapping("/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO')")
    public ResponseEntity<Lancamento> atualizar(@PathVariable Long codigo, @Valid @RequestBody Lancamento lancamento) {
        try {
            Lancamento lancamentoSalvo = lancamentoService.atualizar(codigo, lancamento);
            return ResponseEntity.ok(lancamentoSalvo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @ExceptionHandler({PessoaInexistenteOuInativaException.class})
    public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex) {
        String mensagemUsuario = messageSource.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<ControlExceptionHandler.Erro> erros = Arrays.asList(new ControlExceptionHandler.Erro(mensagemUsuario, mensagemDesenvolvedor));
        return ResponseEntity.badRequest().body(erros);

    }

}
