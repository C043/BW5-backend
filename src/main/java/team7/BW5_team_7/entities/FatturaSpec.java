package team7.BW5_team_7.entities;

import org.springframework.data.jpa.domain.Specification;

public class FatturaSpec {
    public static Specification<Fattura> clienteFilter(Cliente cliente) {
        return (root, query, criteriaBuilder) -> cliente == null ? null : criteriaBuilder.equal(root.get("cliente"), cliente);
    }
}
