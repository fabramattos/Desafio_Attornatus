package br.com.attornatus.api.service;

import br.com.attornatus.api.domain.pessoa.FormAtualizacaoPessoa;
import br.com.attornatus.api.domain.pessoa.FormCadastroPessoa;
import br.com.attornatus.api.domain.pessoa.Pessoa;
import br.com.attornatus.api.domain.pessoa.PessoaRepository;
import br.com.attornatus.api.infra.exceptions.IdPessoaInvalidoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class PessoaServiceTest {

    @MockBean
    private PessoaRepository repository;

    @Autowired
    private PessoaService service;

    private static final FormCadastroPessoa FORM_CADASTRO_PESSOA = new FormCadastroPessoa("Melon Husk", LocalDate.now());
    private static final Pessoa PESSOA_ESPERADA = new Pessoa(FORM_CADASTRO_PESSOA);

    @Test
    @DisplayName("Dado um DTO válido para cadastro, Quando solicitado, Deve cadastrar uma pessoa")
    void cadastrar1() {
        var pessoaEsperada = new Pessoa(FORM_CADASTRO_PESSOA);
        when(repository.save(any()))
                .thenReturn(pessoaEsperada);

        var pessoaSalva = service.cadastrar(FORM_CADASTRO_PESSOA);

        verify(repository, times(1)).save(any());
        assertEquals(pessoaEsperada, pessoaSalva);
    }

    @Test
    @DisplayName("Dado um DTO inválido para cadastro, Quando solicitado, Deve lançar exception")
    void cadastrar2() {
        //TODO criar tratamento de erro para validations do DTO
    }

    @Test
    @DisplayName("Dado um DTO válido para atualização, Quando solicitado, Deve alterar dados da pessoa")
    void editar1() {
        when(repository.findById(anyLong()))
                .thenReturn(Optional.of(PESSOA_ESPERADA));

        var pessoaEditada = service.editar(new FormAtualizacaoPessoa(1L, "Nome Alterado", null));

        verify(repository, times(1)).findById(anyLong());
        assertEquals("Nome Alterado", pessoaEditada.getNome());
    }

    @Test
    @DisplayName("Dado um DTO inválido para atualização, Quando solicitado, Deve lançar exception")
    void editar2() {
        when(repository.findById(anyLong()))
                .thenReturn(Optional.empty());

        assertThrows(IdPessoaInvalidoException.class, () -> service.buscar(111L));
    }
}