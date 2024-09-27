package br.pucminas.aluguel.dto.cliente;

import lombok.Data;

@Data
public class CriarClienteDTO {

    private String email;
    private String senha;
    private String rg;
    private String cpf;
    private String nome;
    private String endereco;
    private String profissao;

}
