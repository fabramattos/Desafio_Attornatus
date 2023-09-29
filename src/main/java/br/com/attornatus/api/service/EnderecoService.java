package br.com.attornatus.api.service;

import br.com.attornatus.api.domain.endereco.Endereco;
import br.com.attornatus.api.domain.endereco.EnderecoRepository;
import br.com.attornatus.api.domain.endereco.FormCadastroEndereco;
import br.com.attornatus.api.infra.exceptions.IdEnderecoInvalidoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {
    @Autowired
    EnderecoRepository repository;
    @Autowired
    PessoaService pessoaService;

    public Endereco cadastrar(Long idPessoa, FormCadastroEndereco form){
        var pessoa = pessoaService.buscar(idPessoa);
        var endereco = new Endereco(form, pessoa);

        if(listarPorPessoa(idPessoa).isEmpty())
            endereco.setPrincipal(true);

        return repository.save(endereco);
    }

    public List<Endereco> favoritarEndereco(Long idPessoa, Long idEnderecoFav){
        var enderecosDaPessoa = listarPorPessoa(idPessoa);

        var contemEndereco = enderecosDaPessoa
                .stream()
                .anyMatch(it -> it.getId().equals(idEnderecoFav));

        if(!contemEndereco)
            throw new IdEnderecoInvalidoException();

        enderecosDaPessoa.forEach(it -> it.setPrincipal(it.getId().equals(idEnderecoFav)));
        return enderecosDaPessoa;
    }

    public List<Endereco> listarPorPessoa(Long idPessoa){
        pessoaService.buscar(idPessoa);

        return repository.findAllByPessoaId(idPessoa);
    }

}