package team7.BW5_team_7.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"utenti"})
public class Ruolo {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    private String ruolo;

//    @ManyToOne
//    @JoinColumn(name = "utente_id")
//    private Utente utente;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "ruoli_utenti",
            joinColumns = @JoinColumn(name = "ruolo_id"),
            inverseJoinColumns = @JoinColumn(name = "utente_id")
    )
    private List<Utente> utenti = new ArrayList<>();


    public Ruolo(String ruolo, List<Utente> utenti) {
        this.ruolo = ruolo;
        this.utenti = utenti;
    }

    public Ruolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public void aggiungiUtente(Utente utente){
        this.utenti.add(utente);
    }

//    @Override
//    public String toString() {
//        return "Ruolo{" +
//                "id=" + id +
//                ", ruolo='" + ruolo + '\'' +
//                //", utente=" + utente +
//                '}';
//    }
}
