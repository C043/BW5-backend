package team7.BW5_team_7.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
public class Provincia {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Integer id;

    private String sigla;
    private String nome;
    private String regione;

    public Provincia(String sigla, String nome, String regione) {
        this.sigla = sigla;
        this.nome = nome;
        this.regione = regione;
    }


}
