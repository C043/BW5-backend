package team7.BW5_team_7.services;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team7.BW5_team_7.entities.Ruolo;
import team7.BW5_team_7.entities.Utente;
import team7.BW5_team_7.exceptions.BadRequestException;
import team7.BW5_team_7.exceptions.NotFoundException;
import team7.BW5_team_7.payloads.AddRuoliDTO;
import team7.BW5_team_7.payloads.RemoveRuoliDTO;
import team7.BW5_team_7.payloads.UtenteDTO;
import team7.BW5_team_7.payloads.UtenteRespDTO;
import team7.BW5_team_7.repositories.UtenteRepository;

import java.util.UUID;

@Service
@Slf4j
public class UtenteService {


    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private RuoliService ruoliService;

    @Autowired
    private PasswordEncoder bCrypt;


    public UtenteRespDTO save(UtenteDTO body) {

        // controlli preliminari
        if (this.utenteRepository.existsByUsername(body.username())) throw new BadRequestException("lo username è già presente, riprova");

        if (this.utenteRepository.existsByEmail(body.email())) throw new BadRequestException("L'email è già presente, riprova");

        // creazione della classe Utente
        Utente utente = new Utente(
                body.username(),
                body.email(),
                bCrypt.encode(body.password()),
                body.nome(),
                body.cognome()
        );

        utente.setAvatar("https://ui-avatars.com/api/?name=" + utente.getNome() + "+" + utente.getCognome());

        // controllo se il ruolo UTENTE esiste già, sennò lo creo
        if (this.ruoliService.existsByRuolo("UTENTE")) {
            //continua senza creare il ruolo UTENTE, ma lo assegno
            Ruolo roleFound = this.ruoliService.findByRuolo("UTENTE");
            roleFound.aggiungiUtente(utente);

            // aggiungo il ruolo all'utente
            utente.aggiungiRuolo(roleFound);

        } else {
            // crea il ruolo utente
            Ruolo defaultRole = new Ruolo("UTENTE");
            utente.aggiungiRuolo(defaultRole);
            defaultRole.aggiungiUtente(utente);
            Ruolo admin = new Ruolo("ADMIN");
            this.ruoliService.saveRuolo(admin);
            this.ruoliService.saveRuolo(defaultRole);
        }


        // salvataggio del nuovo utente tramite repo
        this.utenteRepository.save(utente);

        log.info("utente creato!!");

        // return del payload UtenteRespDTO
        return new UtenteRespDTO(utente.getId());
    }

    public Page<Utente> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.utenteRepository.findAll(pageable);
    }

    public Utente findById(UUID idUtente) {
        return this.utenteRepository.findById(idUtente).orElseThrow(() -> new NotFoundException("Utente non trovato"));
    }

    public Utente findByEmail(String email) {
        return this.utenteRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Utente non trovato con l'email " + email));
    }

    public void findAndDelete(UUID idUtente) {
        Utente found = this.findById(idUtente);

        for (Ruolo ruolo : found.getRuoli()) {
            ruolo.getUtenti().remove(found);
            this.ruoliService.saveRuolo(ruolo);
        }

        this.utenteRepository.delete(found);

    }

    public Utente findAndUpdate(UUID idUtente, UtenteDTO body) {
        // controlli preliminari

        if (this.utenteRepository.existsByUsername(body.username())) throw new BadRequestException("lo username è già presente, riprova");

        if (this.utenteRepository.existsByEmail(body.email())) throw new BadRequestException("L'email è già presente, riprova");

        // cerco l'utente
        Utente found = this.findById(idUtente);

        // se ok, modificare i valori dell'utente trovato
        found.setPassword(bCrypt.encode(body.password()));

        found.setUsername(body.username());
        found.setNome(body.nome());
        found.setCognome(body.cognome());
        found.setEmail(body.email());

        // TODO: se necessario, generare un avatar
        found.setAvatar(found.getAvatar());

        // una volta modificato, salvo tramite repo
        this.utenteRepository.save(found);

        // ritorno l'utente per intero
        return found;
    }

    public Utente findAndAddRuoli(UUID idUtente, AddRuoliDTO body) {
        // cerco l'utente
        Utente found = this.findById(idUtente);

        // cerco il ruolo o ruoli
        for (String ruolo : body.ruoli()) {
            Ruolo foundRuolo = this.ruoliService.findByRuolo(ruolo);

            // controllo per ogni ruolo se è già associato all'utente
            if (found.getRuoli().contains(foundRuolo)) continue;

            found.aggiungiRuolo(foundRuolo);
        }

        this.utenteRepository.save(found);
        return found;
    }

    public Utente findAndRemoveRuolo(UUID idUtente, RemoveRuoliDTO body) {

        // cerco l'utente alla quale togliere il ruolo
        Utente found = this.findById(idUtente);

        for (String ruolo : body.ruoli()) {
            Ruolo foundRuolo = this.ruoliService.findByRuolo(ruolo);
            found.rimuoviRuolo(foundRuolo);
        }

        // salvare l'utente aggiornato
        this.utenteRepository.save(found);

        return found;

    }

}
