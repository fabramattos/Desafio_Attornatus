package br.com.attornatus.api.domain.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record FormCadastroEndereco(
    @NotBlank @Pattern(regexp = "\\d{8}")
    String cep,
    @NotBlank
    String cidade,
    @NotBlank
    String logradouro,
    @NotNull
    Integer numero,
    @NotNull
    Boolean principal
) {
}
