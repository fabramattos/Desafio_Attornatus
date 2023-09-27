package br.com.attornatus.api.domain.endereco;

import br.com.attornatus.api.domain.pessoa.Pessoa;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "enderecos")
@Entity(name = "Endereco")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Endereco{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cep;
    private String cidade;
    private String logradouro;
    private Integer numero;
    private Boolean principal = false;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    public Endereco(FormCadastroEndereco form, Pessoa pessoa) {
        cep = form.cep();
        cidade = form.cidade();
        logradouro = form.logradouro();
        numero = form.numero();
        principal = form.principal();
        this.pessoa = pessoa;
    }

    public void setPrincipal(boolean principal){
        this.principal = principal;
    }

}