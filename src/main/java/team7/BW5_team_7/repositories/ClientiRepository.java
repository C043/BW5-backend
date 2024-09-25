package team7.BW5_team_7.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import team7.BW5_team_7.entities.Cliente;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ClientiRepository extends JpaRepository<Cliente, UUID> {
    boolean existsByEmail(String email);

    @Query("SELECT c FROM Cliente c WHERE c.fatturatoAnnuale >= :minimo")
    List<Cliente> filterByMoreOrEqualFatturatoAnnuo(double minimo);

    @Query("SELECT c FROM Cliente c WHERE c.dataInserimento = :dataInserimento")
    List<Cliente> filterByDataInserimento(LocalDate dataInserimento);

    @Query("SELECT c FROM Cliente c WHERE c.dataUltimoContatto= :data")
    List<Cliente> filterByDataUltimoContatto(LocalDate data);
}
