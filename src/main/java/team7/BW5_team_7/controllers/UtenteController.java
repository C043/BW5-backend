package team7.BW5_team_7.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team7.BW5_team_7.entities.Ruolo;
import team7.BW5_team_7.entities.Utente;
import team7.BW5_team_7.payloads.*;
import team7.BW5_team_7.services.RuoliService;
import team7.BW5_team_7.services.UtenteService;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;
    @Autowired
    private RuoliService ruoliService;

    // TODO: gestire le autorizzazioni

    // endpoint per creare un nuovo utente
    // TODO: validare assolutamente i dati in ingresso con il @Validated

//    @PostMapping
//    public UtenteRespDTO saveNewUtente(@RequestBody UtenteDTO body){
//        return this.utenteService.save(body);
//    }

    // ** stampa tutti gli utenti
    // TODO: mettere il sort se richiesto
    @GetMapping
    // può farlo solo admin
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Utente> findAll(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "5") int size){
        return this.utenteService.findAll(page, size);
    }

    // ** cerca un singolo utente
    @GetMapping("/{idUtente}")
    // pure admin
    @PreAuthorize("hasAuthority('ADMIN')")
    public Utente findById(@PathVariable UUID idUtente){
        return this.utenteService.findById(idUtente);
    }

    // ** elimina un utente
//    @DeleteMapping("/{idUtente}")
//    // admin elimina tutti mentre l'utente se stesso
//    @PreAuthorize("hasAuthority('ADMIN')")
//    public void findAndDelete(@PathVariable UUID idUtente){
//        this.utenteService.findAndDelete(idUtente);
//    }

    // -----------------------------------------
    @DeleteMapping("/{idUtente}")
    @PreAuthorize("hasAuthority('ADMIN') or #idUtente == principal.id")
    public void findAndDelete(@PathVariable UUID idUtente) {
        this.utenteService.findAndDelete(idUtente);
    }



    // ** modifica un utente
    @PutMapping("/{idUtente}")
    // admin modifica tutti mentre l'utente se stesso
    public Utente findAndUpdate(@PathVariable UUID idUtente, @RequestBody UtenteDTO body){
        return this.utenteService.findAndUpdate(idUtente, body);
    }

    // endpoint per inserire un ruolo ad un utente
    @PutMapping("/roles/{idUtente}")
    public Utente findAndAddRoles(@PathVariable UUID idUtente, @RequestBody @Validated AddRuoliDTO body){
       return this.utenteService.findAndAddRuoli(idUtente, body);
    }



    // endpoint per rimuovere un ruolo ad un utente


    // bisogna fare un metodo per creare ruoli
    @PostMapping("/roles")
    public RuoloRespDTO newRuolo(@RequestBody RuoloDTO body){
        return this.ruoliService.saveNewRuolo(body);
    }

    @GetMapping("/roles")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Ruolo> findAllRoles(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "5") int size){
        return this.ruoliService.findAllRoles(page, size);
    }
}
