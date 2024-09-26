package team7.BW5_team_7.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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
    private LocalDate data;

    private int anno;

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


    public Fattura(double importo, int numeroFattura, Cliente cliente, StatoFattura statoFattura) {
        this.data = LocalDate.now();
        this.anno = LocalDate.now().getYear();
        this.importo = importo;
        this.numeroFattura = numeroFattura;
        this.cliente = cliente;
        this.statoFattura = statoFattura;
    }
}

