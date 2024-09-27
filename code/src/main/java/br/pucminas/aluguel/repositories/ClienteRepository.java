package br.pucminas.aluguel.repositories;

import br.pucminas.aluguel.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Esta classe é obrigatória para que seja possível usar o JPA e o Hibernate.
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByEmail(String cpf);

}