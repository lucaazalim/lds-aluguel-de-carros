package br.pucminas.aluguel.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Automovel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String matricula;

    @Column
    private int ano;

    @Column
    private String marca;

    @Column
    private String modelo;

    @Column(unique = true)
    private String placa;

    @Column
    private Double valorDiaria;

}
