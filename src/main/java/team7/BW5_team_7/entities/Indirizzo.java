package team7.BW5_team_7.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team7.BW5_team_7.enums.TipoIndirizzo;

import java.util.UUID;

@Entity
@Table(name = "indirizzi")
@Getter
@Setter
@NoArgsConstructor

public class Indirizzo {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID idIndirizzo;
    private String via;
    private int civico;
    private String cap;

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
    @JsonIgnore
    private Cliente cliente;

    public Indirizzo(String via, int civico, String cap, Comune citta, Cliente cliente) {
        this.via = via;
        this.civico = civico;
        this.citta = citta;
        this.provincia = citta.getProvincia();
        this.cap = cap;
        this.tipoIndirizzo = TipoIndirizzo.SEDE_LEGALE;
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Indirizzo{" +
                "idIndirizzo=" + idIndirizzo +
                ", via='" + via + '\'' +
                ", civico=" + civico +
                ", cap=" + cap +
                ", citta=" + citta.getDenominazione() +
                ", provincia=" + provincia.getProvincia() +
                ", tipoIndirizzo=" + tipoIndirizzo +
                ", cliente=" + cliente.getId() +
                '}';
    }
}
