package team7.BW5_team_7.entities;

import org.springframework.data.jpa.domain.Specification;

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
}
