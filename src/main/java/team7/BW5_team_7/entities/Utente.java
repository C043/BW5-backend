package team7.BW5_team_7.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@Data
@JsonIgnoreProperties({
        "authorities",
        "enabled",
        "accountNonLocked",
        "credentialsNonExpired",
        "accountNonExpired"})
public class Utente implements UserDetails {

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

    @ManyToMany(mappedBy = "utenti", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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

    // TODO: fare metodo per rimuovere un ruolo?
    public void aggiungiRuolo(Ruolo ruolo){
        this.ruoli.add(ruolo);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.ruoli.stream()
                .map(ruolo -> new SimpleGrantedAuthority(ruolo.getRuolo()))
                .collect(Collectors.toList());
    }



}
