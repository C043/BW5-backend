package team7.BW5_team_7.entities;

import jakarta.persistence.*;
import lombok.*;
import team7.BW5_team_7.enums.TipoIndirizzo;

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
    @Setter(AccessLevel.NONE)
    private UUID idIndirizzo;
    private String via;
    private int civico;
    private int cap;

    @ManyToOne
    @JoinColumn(name = "id_citta")
    private Comune citta;

    @ManyToOne
    @JoinColumn(name = "provincia")
    private Provincia provincia;

    @Enumerated(EnumType.STRING)
    private TipoIndirizzo tipoIndirizzo;

    @ManyToOne
    @JoinColumn(name = "cliente")
    private Cliente cliente;

    public Indirizzo(String via, int civico, int cap, Comune citta, Cliente cliente) {
        this.via = via;
        this.civico = civico;
        this.citta = citta;
        this.provincia = citta.getProvincia();
        this.cap = cap;
        this.tipoIndirizzo = TipoIndirizzo.SEDE_LEGALE;
        this.cliente = cliente;
    }
}
