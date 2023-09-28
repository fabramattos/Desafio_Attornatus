package br.com.attornatus.api.service;

import br.com.attornatus.api.domain.endereco.FormCadastroEndereco;
import br.com.attornatus.api.domain.pessoa.FormCadastroPessoa;
import br.com.attornatus.api.domain.pessoa.Pessoa;
import br.com.attornatus.api.infra.exceptions.IdEnderecoInvalidoException;
import br.com.attornatus.api.infra.exceptions.IdPessoaInvalidoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class EnderecoServiceTest {

    @Autowired
    EnderecoService enderecoService;
    @Autowired
    PessoaService pessoaService;


    private Pessoa pessoaCadastrada;

    @BeforeEach
    void setup() {
        pessoaCadastrada = pessoaService.cadastrar(new FormCadastroPessoa("Reanu Keaves", LocalDate.now()));
    }

    @Test
    @DisplayName("Dado um ID e DTO válido, Quando tentar cadastrar, Deve criar endereço para usuario")
    void cadastrar1() {
        var dtoEndereco = formCadastroEndereco(1);
        var enderecoCadastrado = enderecoService.cadastrar(pessoaCadastrada.getId(), dtoEndereco);

        assertNotNull(enderecoCadastrado);
        assertEquals(dtoEndereco.cep(), enderecoCadastrado.getCep());
        assertEquals(dtoEndereco.cidade(), enderecoCadastrado.getCidade());
        assertEquals(dtoEndereco.logradouro(), enderecoCadastrado.getLogradouro());
        assertEquals(dtoEndereco.numero(), enderecoCadastrado.getNumero());
        assertEquals(dtoEndereco.principal(), enderecoCadastrado.getPrincipal());
    }

    @Test
    @DisplayName("Dado um id de usuario inválido, Quando tentar cadastrar endereço, Deve lançar exception")
    void cadastrar2() {
        assertThrows(
                IdPessoaInvalidoException.class,
                () -> enderecoService.cadastrar(-1L, formCadastroEndereco(1))
        );
    }

    @Test
    @DisplayName("Dado um idPessoa inválido, Quando tentar favoritar endereço,Deve lançar exception")
    void favoritarEndereco1() {
        assertThrows(
                IdEnderecoInvalidoException.class,
                () -> enderecoService.favoritarEndereco(pessoaCadastrada.getId(), -1L)
        );
    }

    @Test
    @DisplayName("Dado um idPessoa válido mas um idEndereco que não pertence ao usuario ou inexistente, Quando tentar favoritar endereço, Deve lançar exception")
    void favoritarEndereco2() {
        assertThrows(
                IdEnderecoInvalidoException.class,
                () -> enderecoService.favoritarEndereco(pessoaCadastrada.getId(), -1L)
        );
    }


    @Test
    @DisplayName("Dado um id válido de pessoa, Quando solicitado, Deve retornar todos endereços atrelados à pessoa")
    void listarPorPessoa() {
        enderecoService.cadastrar(pessoaCadastrada.getId(), formCadastroEndereco(1));
        enderecoService.cadastrar(pessoaCadastrada.getId(), formCadastroEndereco(2));
        enderecoService.cadastrar(pessoaCadastrada.getId(), formCadastroEndereco(3));

        var enderecos = enderecoService.listarPorPessoa(pessoaCadastrada.getId());

        assertEquals(3, enderecos.size());
    }

    private FormCadastroEndereco formCadastroEndereco(Integer numero) {
        return new FormCadastroEndereco(
                "12345000",
                "Cidade",
                "Logradouro",
                numero,
                false);
    }
}