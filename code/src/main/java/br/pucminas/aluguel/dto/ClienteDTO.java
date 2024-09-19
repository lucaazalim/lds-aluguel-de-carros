package br.pucminas.aluguel.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class ClienteDTO {

    private Long id;
    private String email;
    private String rg;
    private String cpf;
    private String nome;
    private String endereco;
    private String profissao;

}
