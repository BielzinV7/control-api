package com.example.demo2.api.controller;


import com.example.demo2.domain.model.Categoria;
import com.example.demo2.domain.repository.CategoriaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    final CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and #oauth2.hasScope('read')")
    public List<Categoria> listar(){

        return categoriaRepository.findAll();

    }

    @GetMapping("/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA')and #oauth2.hasScope('read')")
    public ResponseEntity<Categoria> buscarPeloId(@PathVariable Long codigo){

        Optional<Categoria> categoriaId  = categoriaRepository.findById(codigo);
        return categoriaId.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_CATEGORIA')and #oauth2.hasScope('write')")
    public ResponseEntity<Categoria> adicionar(@Valid @RequestBody Categoria categoria, UriComponentsBuilder builder){

        Categoria categoriaSalva = categoriaRepository.save(categoria);
        URI uri = builder.path("/categorias/{id}").buildAndExpand(categoria.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(categoriaSalva);

    }

}
