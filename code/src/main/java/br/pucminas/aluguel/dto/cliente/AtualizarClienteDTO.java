package br.pucminas.aluguel.dto.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AtualizarClienteDTO {

    @Email
    @NotNull
    private String email;

    @NotNull
    private String senha;

    @NotNull
    private String nome;

    @NotNull
    private String endereco;

    @NotNull
    private String profissao;

}
