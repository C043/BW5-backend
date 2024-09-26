package team7.BW5_team_7.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "comuni")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Comune {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID idComune;
    private String codiceProvincia;
    private String progressivoDelComune;
    private String denominazione;

    @ManyToOne
    @JoinColumn(name = "provincia")
    private Provincia provincia;

    public Comune(String codiceProvincia, String progressivoDelComune, String denominazione, Provincia provincia) {
        this.codiceProvincia = codiceProvincia;
        this.progressivoDelComune = progressivoDelComune;
        this.denominazione = denominazione;
        this.provincia = provincia;
    }
}
