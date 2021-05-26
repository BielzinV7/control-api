package com.example.demo2.service;


import com.example.demo2.domain.model.Pessoa;
import com.example.demo2.domain.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa atualizar(Long id, Pessoa pessoa){

        Pessoa pessoaAtual = buscarOuFalhar(id);

        BeanUtils.copyProperties(pessoa,pessoaAtual,"id");

        return pessoaRepository.save(pessoaAtual);

    }

    public Pessoa buscarOuFalhar(Long id){

        return pessoaRepository.findById(id).orElseThrow(()-> new EmptyResultDataAccessException(1));

    }

}
