package team7.BW5_team_7;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(name = "indirizzi")
@Getter
@Setter
@ToString
@NoArgsConstructor

public class Indirizzo {
    @Id
    @GeneratedValue
    private UUID idIndirizzo;
    private String via;
    private int civico;
    private String localita;
    private int cap;
    private String comune;
    @Enumerated(EnumType.STRING)
    private TipoIndirizzo tipoIndirizzo;

    public Indirizzo(String via, int civico, String localita, int cap, String comune, TipoIndirizzo tipoIndirizzo) {
        this.via = via;
        this.civico = civico;
        this.localita = localita;
        this.cap = cap;
        this.comune = comune;
        this.tipoIndirizzo = tipoIndirizzo;
    }
}
