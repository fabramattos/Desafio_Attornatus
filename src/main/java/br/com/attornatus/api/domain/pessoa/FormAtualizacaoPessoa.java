package br.com.attornatus.api.domain.pessoa;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record FormAtualizacaoPessoa(
        @NotNull
        Long id,
        String nome,
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate dataNascimento
){}
