package team7.BW5_team_7.entities;

import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

import static java.lang.Double.parseDouble;

public class CustomerSpec {
    public static Specification<Cliente> fatturatoAnnualeFilter(String fatturatoAnnuale) {
        return (root, query, criteriaBuilder) -> fatturatoAnnuale == null ? null : criteriaBuilder.greaterThanOrEqualTo(root.get("fatturatoAnnuale"),
                parseDouble(fatturatoAnnuale));
    }

    public static Specification<Cliente> parteDellaRagioneSocialeFilter(String ragioneSociale) {
        return (root, query, criteriaBuilder) -> ragioneSociale == null ? null : criteriaBuilder.like(criteriaBuilder.lower(root.get("ragioneSociale")),
                "%" + ragioneSociale.toLowerCase() + "%");
    }

    public static Specification<Cliente> dataInserimentoFilter(LocalDate dataInserimento) {
        return (root, query, criteriaBuilder) -> dataInserimento == null ? null : criteriaBuilder.equal(root.get("dataInserimento"), dataInserimento);
    }

    public static Specification<Cliente> dataUltimoContattoFilter(LocalDate dataUltimoContatto) {
        return (root, query, criteriaBuilder) -> dataUltimoContatto == null ? null : criteriaBuilder.equal(root.get("dataUltimoContatto"), dataUltimoContatto);
    }
}
