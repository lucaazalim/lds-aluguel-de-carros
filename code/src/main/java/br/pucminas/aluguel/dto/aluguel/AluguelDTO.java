package br.pucminas.aluguel.dto.aluguel;

import br.pucminas.aluguel.enumerators.StatusAluguel;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AluguelDTO {

    private Long id;
    private StatusAluguel status;
    private LocalDate inicio;
    private LocalDate termino;
    private Double valorTotal;

}
