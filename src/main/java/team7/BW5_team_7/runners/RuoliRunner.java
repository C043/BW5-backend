package team7.BW5_team_7.runners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import team7.BW5_team_7.entities.Ruolo;
import team7.BW5_team_7.services.UtenteService;

public class RuoliRunner implements CommandLineRunner {

    // TODO: valutare ed eliminare in caso questo Runner... un pochetto inutile

    @Autowired
    private UtenteService utenteService;

    // runner creato per creare i ruoli base, come UTENTE, ADMIN etc...
    @Override
    public void run(String... args) throws Exception {

        // instanzio i ruoli di default
        Ruolo ruoloUtente = new Ruolo("UTENTE");
        Ruolo ruoloAdmin = new Ruolo("ADMIN");

    }





}
