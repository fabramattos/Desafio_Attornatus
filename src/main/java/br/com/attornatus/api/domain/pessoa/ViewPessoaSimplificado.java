package br.com.attornatus.api.domain.pessoa;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record ViewPessoaSimplificado(
        Long id,
        String nome,
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate dataNascimento
){
    public ViewPessoaSimplificado(Pessoa pessoa){
        this(pessoa.getId(), pessoa.getNome(), pessoa.getDataNascimento());
    }
}