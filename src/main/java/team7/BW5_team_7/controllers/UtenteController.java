package team7.BW5_team_7.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import team7.BW5_team_7.entities.Utente;
import team7.BW5_team_7.payloads.UtenteDTO;
import team7.BW5_team_7.payloads.UtenteRespDTO;
import team7.BW5_team_7.services.UtenteService;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;
    // TODO: gestire le autorizzazioni

    // endpoint per creare un nuovo utente
    @PostMapping
    public UtenteRespDTO saveNewUtente(@RequestBody UtenteDTO body){
        return this.utenteService.save(body);
    }

    // ** stampa tutti gli utenti
    @GetMapping
    public Page<Utente> findAll(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "5") int size){
        return this.utenteService.findAll(page, size);
    }

    // ** cerca un singolo utente
    @GetMapping("/{idUtente}")
    public Utente findById(@PathVariable UUID idUtente){
        return this.utenteService.findById(idUtente);
    }

    // ** elimina un utente
    @DeleteMapping("/{idUtente}")
    public void findAndDelete(@PathVariable UUID idUtente){
        this.utenteService.findAndDelete(idUtente);
    }

    // ** modifica un utente
    @PutMapping("/{idUtente}")
    public Utente findAndUpdate(@PathVariable UUID idUtente, @RequestBody UtenteDTO body){
        return this.utenteService.findAndUpdate(idUtente, body);
    }
}
