package team7.BW5_team_7.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team7.BW5_team_7.entities.Ruolo;
import team7.BW5_team_7.entities.Utente;
import team7.BW5_team_7.payloads.*;
import team7.BW5_team_7.security.Validation;
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
    @Autowired
    private Validation validation;


    // ** stampa tutti gli utenti
    @GetMapping
    // può farlo solo admin
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Utente> findAll(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "5") int size) {
        return this.utenteService.findAll(page, size);
    }

    // ** cerca un singolo utente
    @GetMapping("/{idUtente}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Utente findById(@PathVariable UUID idUtente) {
        return this.utenteService.findById(idUtente);
    }

    @DeleteMapping("/{idUtente}")
    @PreAuthorize("hasAuthority('ADMIN') or #idUtente == principal.id")
    public void findAndDelete(@PathVariable UUID idUtente) {
        this.utenteService.findAndDelete(idUtente);
    }


    // ** modifica un utente
    @PutMapping("/{idUtente}")
    // admin modifica tutti mentre l'utente se stesso
    @PreAuthorize("hasAuthority('ADMIN') or #idUtente == principal.id")
    public Utente findAndUpdate(@PathVariable UUID idUtente, @RequestBody UtenteDTO body, BindingResult validation) {
        this.validation.validate(validation);
        return this.utenteService.findAndUpdate(idUtente, body);
    }

    // endpoint per inserire un ruolo ad un utente
    @PutMapping("/roles/{idUtente}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Utente findAndAddRoles(@PathVariable UUID idUtente, @RequestBody @Validated AddRuoliDTO body, BindingResult validation) {
        this.validation.validate(validation);
        return this.utenteService.findAndAddRuoli(idUtente, body);
    }


    // endpoint per rimuovere un ruolo ad un utente
    @PutMapping("/roles/remove/{idUtente}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Utente findAndRemoveRoles(@PathVariable UUID idUtente, @RequestBody @Validated RemoveRuoliDTO body, BindingResult validation) {
        this.validation.validate(validation);
        return this.utenteService.findAndRemoveRuolo(idUtente, body);
    }

    // bisogna fare un metodo per creare ruoli
    @PostMapping("/roles")
    @PreAuthorize("hasAuthority('ADMIN')")
    public RuoloRespDTO newRuolo(@RequestBody RuoloDTO body, BindingResult validation) {
        this.validation.validate(validation);
        return this.ruoliService.saveNewRuolo(body);
    }

    @GetMapping("/roles")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Ruolo> findAllRoles(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "5") int size) {
        return this.ruoliService.findAllRoles(page, size);
    }
}
