package br.pucminas.aluguel.repository;

import br.pucminas.aluguel.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

// Esta classe é obrigatória para que seja possível usar o JPA e o Hibernate.
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}