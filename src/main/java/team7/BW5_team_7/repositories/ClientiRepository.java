package team7.BW5_team_7.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import team7.BW5_team_7.entities.Cliente;

import java.util.UUID;

@Repository
public interface ClientiRepository extends JpaRepository<Cliente, UUID>, JpaSpecificationExecutor<Cliente> {
    boolean existsByEmail(String email);
}
