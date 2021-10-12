package com.example.demo2.api.controller;


import com.example.demo2.domain.model.Pessoa;
import com.example.demo2.domain.repository.PessoaRepository;
import com.example.demo2.service.PessoaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    final PessoaRepository pessoaRepository;
    final PessoaService pessoaService;

    public PessoaController(PessoaRepository pessoaRepository, PessoaService pessoaService) {
        this.pessoaRepository = pessoaRepository;
        this.pessoaService = pessoaService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')")
    public ResponseEntity<Pessoa> adicionar(@Valid @RequestBody Pessoa pessoa, UriComponentsBuilder builder){

        Pessoa pessoaSalva = pessoaRepository.save(pessoa);
        URI uri = builder.path("/pessoas/{id}").buildAndExpand(pessoa.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(pessoaSalva);
    }

    @GetMapping("/{codigo}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA') and #oauth2.hasScope('read')")
    public Pessoa buscar(@PathVariable Long codigo){

        return pessoaService.buscarOuFalhar(codigo);
    }

    @PutMapping("/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')")
    public ResponseEntity<Pessoa> atualizar(@Valid @RequestBody Pessoa pessoa , @PathVariable Long codigo){

        Pessoa pessoaAtualizada = pessoaService.atualizar(codigo,pessoa);

        return ResponseEntity.ok(pessoaAtualizada);


    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_REMOVER_PESSOA') and #oauth2.hasScope('write')")
    public void remover(@PathVariable Long codigo) {
        pessoaRepository.deleteById(codigo);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA')")
    public Page<Pessoa> pesquisar(@RequestParam(required = false, defaultValue="") String nome, Pageable pageable) {
        return pessoaRepository.findByNomeContaining(nome, pageable);
    }
}
