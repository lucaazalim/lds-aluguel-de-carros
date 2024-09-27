package br.pucminas.aluguel.dto.automovel;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CriarAutomovelDTO {

    @NotNull
    private String matricula;

    @NotNull
    private int ano;

    @NotNull
    private String marca;

    @NotNull
    private String modelo;

    @NotNull
    private String placa;

    @NotNull
    private Double valorDiaria;

}
