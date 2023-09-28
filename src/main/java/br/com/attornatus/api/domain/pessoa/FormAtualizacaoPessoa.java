package br.com.attornatus.api.domain.pessoa;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record FormAtualizacaoPessoa(
        String nome,
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate dataNascimento
){}
