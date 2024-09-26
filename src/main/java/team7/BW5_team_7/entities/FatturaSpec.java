package team7.BW5_team_7.entities;

import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class FatturaSpec {
    public static Specification<Fattura> clienteFilter(Cliente cliente) {
        return (root, query, criteriaBuilder) -> cliente == null ? null : criteriaBuilder.equal(root.get("cliente"), cliente);
    }

    public static Specification<Fattura> statoFatturaFilter(StatoFattura statoFattura) {
        return (root, query, criteriaBuilder) -> statoFattura == null ? null : criteriaBuilder.equal(root.get("statoFattura"), statoFattura);
    }

    public static Specification<Fattura> dataFatturaFilter(LocalDate date) {
        return (root, query, criteriaBuilder) -> date == null ? null : criteriaBuilder.equal(root.get("data"), date);
    }

    public static Specification<Fattura> annoFilter(int anno) {
        return (root, query, criteriaBuilder) -> anno == 0 ? null : criteriaBuilder.equal(root.get("anno"), anno);
    }
}
