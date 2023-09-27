package br.com.attornatus.api.service;

import br.com.attornatus.api.domain.endereco.FormCadastroEndereco;
import br.com.attornatus.api.domain.endereco.Endereco;
import br.com.attornatus.api.domain.endereco.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {
    @Autowired
    EnderecoRepository repository;
    @Autowired
    PessoaService pessoaService;

    public Endereco cadastrar(FormCadastroEndereco form){
        var pessoa = pessoaService.buscar(form.idPessoa());
        var endereco = new Endereco(form, pessoa);
        
        if(endereco.getPrincipal())
            desmarcaFavoritoDoUsuario(form.idPessoa());

        return repository.save(new Endereco(form, pessoa));
    }

    private void desmarcaFavoritoDoUsuario(Long idPessoa) {
        var enderecos = listarPorPessoa(idPessoa);
        enderecos.forEach(it -> {
            it.setPrincipal(false);
            repository.save(it);
        });
    }

    public List<Endereco> favoritarEndereco(Long idEnderecoFav){
        var idPessoa = busca(idEnderecoFav).getPessoa().getId();
        var enderecosDaPessoa = listarPorPessoa(idPessoa);

        enderecosDaPessoa.forEach(it -> it.setPrincipal(it.getId().equals(idEnderecoFav)));

        return repository.saveAll(enderecosDaPessoa);
    }

    public List<Endereco> listarPorPessoa(Long idPessoa){
        return repository
                .findAllByPessoaId(idPessoa)
                .orElseThrow();
    }

    private Endereco busca(Long id){
        return repository
                .findById(id)
                .orElseThrow();
    }
}