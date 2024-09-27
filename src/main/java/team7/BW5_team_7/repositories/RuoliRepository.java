package team7.BW5_team_7.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team7.BW5_team_7.entities.Ruolo;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RuoliRepository extends JpaRepository<Ruolo, UUID> {
    Optional<Ruolo> findByRuolo(String ruolo);
    boolean existsByRuolo(String ruolo);

}
