package team7.BW5_team_7.entities;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Comune {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codiceProvinciaStorico;
    private String nome;

    @ManyToOne
    @JoinColumn(name = "provincia_id", nullable = false)
    private Provincia provincia;


    public Comune() {}

    public Comune(String codiceProvinciaStorico, String nome, Provincia provincia) {
        this.codiceProvinciaStorico = codiceProvinciaStorico;
        this.nome = nome;
        this.provincia = provincia;
    }


}
