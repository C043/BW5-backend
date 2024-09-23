package team7.BW5_team_7.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import team7.BW5_team_7.entities.Utente;
import team7.BW5_team_7.payloads.UtenteDTO;
import team7.BW5_team_7.payloads.UtenteRespDTO;
import team7.BW5_team_7.repositories.UtenteRepository;

import java.util.UUID;

@Service
public class UtenteService {


    @Autowired
    private UtenteRepository utenteRepository;


    public UtenteRespDTO save(UtenteDTO body){

        // controlli preliminari
        // TODO: controllare che lo username non sia presente nel db
        // TODO: controllare che la email non sia presente del db

        // creazione della classe Utente
        // TODO: criptare la password prima di mandare !!!
        Utente utente = new Utente(body.username(), body.email(), body.password(), body.nome(), body.cognome());

        // salvataggio del nuovo utente treamite repo
        this.utenteRepository.save(utente);

        // return del payload UtenteRespDTO
        return new UtenteRespDTO(utente.getId());

    }

    public Page<Utente> findAll (int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return this.utenteRepository.findAll(pageable);
    }

    public Utente findById(UUID idUtente){
        return this.utenteRepository.findById(idUtente).orElseThrow(() -> new RuntimeException("Utente non trovato"));
    }


    public void findAndDelete(UUID idUtente){
        Utente found = this.findById(idUtente);
        this.utenteRepository.delete(found);
    }

    public  Utente findAndUpdate(UUID idUtente, UtenteDTO body){
        // controlli preliminari
        // TODO: controllare che lo username non sia presente nel db
        // TODO: controllare che la email non sia presente del db
        Utente found = this.findById(idUtente);

        // se ok, modificare i valori dell'utente trovato
        // TODO: criptare la password prima di mandare !!!
        found.setPassword(body.password());

        found.setUsername(body.username());
        found.setNome(body.nome());
        found.setCognome(body.cognome());
        found.setEmail(body.email());

        // TODO: controllare se è stato modificato e in caso, rimandare lo stesso avatar  PS: L'avatar è server-generated
        found.setAvatar("example...");

        // una volta modificato, salvo tramite repo
        this.utenteRepository.save(found);

        // ritorno l'utente per intero
        return found;
    }

}
