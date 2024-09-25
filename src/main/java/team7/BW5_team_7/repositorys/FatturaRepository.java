package team7.BW5_team_7.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team7.BW5_team_7.entities.Fattura;

import java.util.UUID;

@Repository

public interface FatturaRepository extends JpaRepository<Fattura, UUID> {

    Fattura findByNumeroFattura(int numeroFattura);

}