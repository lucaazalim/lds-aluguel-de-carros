package br.pucminas.aluguel.repositories;

import br.pucminas.aluguel.models.Aluguel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface AluguelRepository extends JpaRepository<Aluguel, Long> {

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END " +
            "FROM Aluguel a " +
            "WHERE (a.inicio <= :termino AND a.termino >= :inicio)")
    boolean existsByDateRange(@Param("inicio") LocalDate inicio, @Param("termino") LocalDate termino);
}