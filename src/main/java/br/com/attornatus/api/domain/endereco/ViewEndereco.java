package br.com.attornatus.api.domain.endereco;

public record ViewEndereco(Long idEndereco, String cep, String cidade, String logradouro, Integer numero, Boolean principal){
    public ViewEndereco(Endereco endereco){
        this(endereco.getId(),
                endereco.getCep(),
                endereco.getCidade(),
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getPrincipal()
        );
    }
}