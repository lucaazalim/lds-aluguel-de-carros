package br.pucminas.aluguel.dto.cliente;

import lombok.Data;

@Data
public class LoginDto {

    private String token;
    private long expiraEm;

}
