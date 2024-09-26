package team7.BW5_team_7.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team7.BW5_team_7.entities.Comune;

import java.util.UUID;

@Repository
public interface ComuneRepository extends JpaRepository<Comune, UUID> {

    Comune findByDenominazione(String denominazione);

   
}
