package com.example.demo2.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@EqualsAndHashCode
@Data
@Entity
public class Pessoa {

    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long codigo;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 50)
    private String nome;

    @Embedded
    private Endereco endereco;

}
