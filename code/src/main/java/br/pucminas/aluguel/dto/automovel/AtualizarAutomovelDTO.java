package br.pucminas.aluguel.dto.automovel;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AtualizarAutomovelDTO {

    @NotNull
    private Double valorDiaria;

}
