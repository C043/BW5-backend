package team7.BW5_team_7.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@ToString
public class Ruoli_utente {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    private String ruolo;

    @ManyToOne
    private Utente utente;

    public Ruoli_utente(String ruolo) {
        this.ruolo = ruolo;
    }
}
