package br.pucminas.aluguel.dto.aluguel;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CriarAluguelDTO {

    @NotNull
    private LocalDate inicio;

    @NotNull
    private LocalDate termino;

    @NotNull
    private Long automovelId;

}