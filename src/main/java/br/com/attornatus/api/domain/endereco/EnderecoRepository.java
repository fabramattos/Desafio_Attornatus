package br.com.attornatus.api.domain.endereco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    @Query("SELECT e FROM Endereco e WHERE e.pessoa.id = :idPessoa")
    Optional<List<Endereco>> findAllByPessoaId(@Param("idPessoa") Long idPessoa);
}
