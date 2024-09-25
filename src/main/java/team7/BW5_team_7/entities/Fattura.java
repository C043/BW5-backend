package team7.BW5_team_7.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "fattura")
@Getter
@Setter
@ToString
@NoArgsConstructor

public class Fattura {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID idFattura;

    @Column(name = "data")
    private Date data;

    @Column(name = "importo")
    private double importo;

    @Column(name = "numeroFattura")
    private int numeroFattura;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "stato_fattura")
    private StatoFattura statoFattura;


    public Fattura(UUID idFattura, Date data, double importo, int numeroFattura, Cliente cliente) {
        this.idFattura = idFattura;
        this.data = data;
        this.importo = importo;
        this.numeroFattura = numeroFattura;
        this.cliente = cliente;

    }
}

