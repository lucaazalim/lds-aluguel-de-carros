package br.pucminas.aluguel.model;

import br.pucminas.aluguel.enumerators.StatusAluguel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Aluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private StatusAluguel status = StatusAluguel.PENDENTE;

    @Column(nullable = false)
    private LocalDate inicio;

    @Column(nullable = false)
    private LocalDate termino;

    @Column(nullable = false)
    private Double valorTotal;

    @ManyToOne
    private Automovel automovel;

    public Aluguel(LocalDate inicio, LocalDate termino, Automovel automovel) {

        if (inicio.isAfter(termino)) {
            throw new IllegalArgumentException("A data de término deve ser maior ou igual à data de início.");
        }

        this.inicio = inicio;
        this.termino = termino;
        this.valorTotal = automovel.getValorDiaria() * ChronoUnit.DAYS.between(inicio, termino);
        this.automovel = automovel;

    }


}
