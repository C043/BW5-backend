package team7.BW5_team_7.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Data
public class Utente {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    private String username;
    private String email;
    private String password;
    private String nome;
    private String cognome;
    private String avatar;

    @ManyToMany(mappedBy = "utenti")
    private List<Ruolo> ruoli = new ArrayList<>();

    public Utente(String username,
                  String email,
                  String password,
                  String nome,
                  String cognome) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
    }

    public void aggiungiRuolo(Ruolo ruolo){
        this.ruoli.add(ruolo);
    }

// TODO: da rifare il metodo -->
//    public void rimuoviRuolo(Ruolo ruolo) {
//        this.ruoli.remove(ruolo);
//        ruolo.setUtente(null);
//    }


}
