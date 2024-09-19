package br.pucminas.aluguel.model;

import br.pucminas.aluguel.enumerators.TipoUsuario;
import br.pucminas.aluguel.utils.CPF;
import jakarta.persistence.*;
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

    @Column(unique = true)
    private String rg;

    @CPF
    @Column(unique = true)
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
