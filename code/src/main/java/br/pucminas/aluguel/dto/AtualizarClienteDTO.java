package br.pucminas.aluguel.dto;

import lombok.Data;

@Data
public class AtualizarClienteDTO {

    private String email;
    private String senha;
    private String nome;
    private String endereco;
    private String profissao;

}
