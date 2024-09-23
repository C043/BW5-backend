package team7.BW5_team_7.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "fatture")
@Getter
@Setter
@ToString
@NoArgsConstructor

public class Fatture {
    @Id
    @GeneratedValue
    private UUID idFattura;
    private Date data;
    private double importo;
    private int numeroFattura;

    public Fatture(UUID idFattura, Date data, double importo, int numeroFattura) {
        this.idFattura = idFattura;
        this.data = data;
        this.importo = importo;
        this.numeroFattura = numeroFattura;
    }
}

