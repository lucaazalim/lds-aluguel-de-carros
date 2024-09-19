package br.pucminas.aluguel.model;

import br.pucminas.aluguel.enumerators.TipoUsuario;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Cliente extends Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String rg;
    private String cpf;
    private String nome;
    private String endereco;
    private String profissao;

    public Cliente(String email, String senha, String rg, String cpf, String nome, String endereco, String profissao) {
        super(TipoUsuario.PESSOA, email, senha);
        this.rg = rg;
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.profissao = profissao;
    }

}
