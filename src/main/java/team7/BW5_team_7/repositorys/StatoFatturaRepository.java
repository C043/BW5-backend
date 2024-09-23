package team7.BW5_team_7.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team7.BW5_team_7.entities.StatoFattua;

import java.util.UUID;

@Repository
public interface StatoFatturaRepository extends JpaRepository<StatoFattua, UUID> {
}
