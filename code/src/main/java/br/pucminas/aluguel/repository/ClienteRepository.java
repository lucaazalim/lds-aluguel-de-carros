package br.pucminas.aluguel.repository;

import br.pucminas.aluguel.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}