package team7.BW5_team_7.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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

}
