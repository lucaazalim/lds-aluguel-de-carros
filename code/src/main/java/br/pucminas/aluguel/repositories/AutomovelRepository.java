package br.pucminas.aluguel.repositories;

import br.pucminas.aluguel.models.Automovel;
import org.springframework.data.jpa.repository.JpaRepository;

// Esta classe é obrigatória para que seja possível usar o JPA e o Hibernate.
public interface AutomovelRepository extends JpaRepository<Automovel, Long> {

}