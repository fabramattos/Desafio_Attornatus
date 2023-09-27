package br.com.attornatus.api.service;

import br.com.attornatus.api.domain.pessoa.FormAtualizacaoPessoa;
import br.com.attornatus.api.domain.pessoa.FormCadastroPessoa;
import br.com.attornatus.api.domain.pessoa.Pessoa;
import br.com.attornatus.api.domain.pessoa.PessoaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class PessoaServiceTest {

    @MockBean
    private PessoaRepository repository;

    @Autowired
    private PessoaService service;

    private static final FormCadastroPessoa FORM_CADASTRO_PESSOA = new FormCadastroPessoa("Melon Husk", LocalDate.now());

    @Test
    @DisplayName("Dado um DTO válido, Quando solicitado, Deve cadastrar uma pessoa")
    void cadastrar() {
        var pessoaEsperada = new Pessoa(FORM_CADASTRO_PESSOA);
        Mockito
                .when(repository.save(any()))
                .thenReturn(pessoaEsperada);

        var pessoaSalva = service.cadastrar(FORM_CADASTRO_PESSOA);

        verify(repository, times(1)).save(any());
        assertEquals(pessoaEsperada, pessoaSalva);
    }

    @Test
    @DisplayName("Dado um DTO válido, Quando solicitado, Deve alterar dados da pessoa")
    void editar() {
        var pessoaSalva = new Pessoa(FORM_CADASTRO_PESSOA);
        Mockito
                .when(repository.findById(anyLong()))
                .thenReturn(Optional.of(pessoaSalva));

        var pessoaEditada = service.editar(new FormAtualizacaoPessoa(1L, "Nome Alterado", null));

        verify(repository, times(1)).findById(anyLong());
        assertEquals("Nome Alterado", pessoaEditada.getNome());
    }

    @Test
    void buscar() {
    }

    @Test
    void listar() {
    }
}