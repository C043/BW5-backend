package team7.BW5_team_7.controllers;

import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team7.BW5_team_7.entities.Indirizzo;
import team7.BW5_team_7.payloads.IndirizzoDto;
import team7.BW5_team_7.payloads.IndirizzoDtoResp;
import team7.BW5_team_7.services.IndirizzoService;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/indirizzi")
public class IndirizzoController {

    private IndirizzoService indirizzoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")// SOLO GI ADMIN POSSONO ELIMINARE LE RISORSE
    public IndirizzoDtoResp saveIndirizzo(@RequestBody @Validated IndirizzoDto body, BindingResult validationResult) throws
            BadRequestException {
        if (validationResult.hasErrors()) {
            String messages = validationResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Controlla i seguenti errori: " + messages);
        } else {
            return new IndirizzoDtoResp(this.indirizzoService.save(body).getIdIndirizzo());
        }
    }

    @GetMapping
    public Page<Indirizzo> findAll(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(defaultValue = "idIndirizzo") String sortBy) {
        return this.indirizzoService.findAll(page, size, sortBy);
    }

    @GetMapping("/{idIndirizzo}")
    public Indirizzo findById(@PathVariable UUID idIndirizzo) {
        return this.indirizzoService.findById(idIndirizzo);
    }

    @PutMapping("/{idIndirizzo}")
    @PreAuthorize("hasAuthority('ADMIN')")// SOLO GI ADMIN POSSONO MODIFICARE LE RISORSE
    @ResponseStatus(HttpStatus.CREATED)
    public IndirizzoDtoResp findAndUpdate(@PathVariable UUID idIndirizzo, @RequestBody IndirizzoDto body) throws Throwable {
        return new IndirizzoDtoResp(this.indirizzoService.findAndUpdate(idIndirizzo, body).getIdIndirizzo());
    }

    @DeleteMapping("/{idIndirizzo}")
    @PreAuthorize("hasAuthority('ADMIN')")// SOLO GI ADMIN POSSONO ELIMINARE LE RISORSE
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable UUID idIndirizzo) {
        this.indirizzoService.findAndDelete(idIndirizzo);
    }
}
