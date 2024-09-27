package br.pucminas.aluguel.dto.aluguel;

import br.pucminas.aluguel.enumerators.StatusAluguel;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AtualizarAluguelDTO {

    @NotNull
    private StatusAluguel status;

}
