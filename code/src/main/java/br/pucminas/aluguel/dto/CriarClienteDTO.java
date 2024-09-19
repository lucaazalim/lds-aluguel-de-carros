package br.pucminas.aluguel.dto;

import br.pucminas.aluguel.utils.CPF;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
