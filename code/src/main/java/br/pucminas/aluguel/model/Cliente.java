package br.pucminas.aluguel.model;

import br.pucminas.aluguel.enumerators.TipoUsuario;
import br.pucminas.aluguel.utils.CPF;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Cliente extends Usuario {

    private TipoUsuario tipo = TipoUsuario.PESSOA;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    @NotBlank
    private String rg;

    @CPF
    @NotBlank
    @Column(unique = true)
    private String cpf;

    @NotBlank
    private String nome;

    @NotBlank
    private String endereco;

    @NotBlank
    private String profissao;

}
