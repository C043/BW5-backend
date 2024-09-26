package team7.BW5_team_7.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import team7.BW5_team_7.entities.Provincia;

import java.util.Optional;

public interface ProvinciaRepository extends JpaRepository<Provincia, Integer> {
    Optional<Provincia> findByNome(String nome);
    Optional<Provincia> findByNomeIgnoreCase(String nome);
}
