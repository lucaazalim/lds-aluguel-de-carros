package br.pucminas.aluguel.dto.automovel;

import br.pucminas.aluguel.dto.cliente.ClienteDTO;
import br.pucminas.aluguel.models.Cliente;
import lombok.Data;

@Data
public class AutomovelDTO {

    private Long id;
    private String matricula;
    private int ano;
    private String marca;
    private String modelo;
    private String placa;
    private Double valorDiaria;
    private ClienteDTO proprietario;

}
