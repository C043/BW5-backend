package team7.BW5_team_7.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team7.BW5_team_7.entities.Provincia;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, UUID> {
    Provincia findByProvinciaIgnoreCase(String nomeProvincia);

    Optional<Provincia> findByProvincia(String provincia);

}



