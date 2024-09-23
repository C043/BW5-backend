package team7.BW5_team_7.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "Stato Fatture")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class StatoFattua {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID idStatoFattura;
    private String nome;

    public StatoFattua(String nome) {
        this.nome = nome;
    }
}
