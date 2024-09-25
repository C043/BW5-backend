package team7.BW5_team_7.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team7.BW5_team_7.entities.Fattura;
import team7.BW5_team_7.entities.StatoFattura;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, UUID> {

    // Trova fatture per cliente
    List<Fattura> findByClienteId(UUID clienteId);

    // Trova fatture per stato
    List<Fattura> findByStatoFattura(StatoFattura statoFattura);

    // Trova fatture per data
    List<Fattura> findByData(LocalDate data);

    // Trova fatture per anno
    List<Fattura> findByDataYear(int year);

    // Trova fatture per range di importi
    List<Fattura> findByImportoBetween(double minImporto, double maxImporto);
}
