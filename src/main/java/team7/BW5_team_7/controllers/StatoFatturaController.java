package team7.BW5_team_7.controllers;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team7.BW5_team_7.entities.StatoFattura;
import team7.BW5_team_7.payloads.StatoFatturaDto;
import team7.BW5_team_7.payloads.StatoFattureDtoResp;
import team7.BW5_team_7.services.StatoFatturaService;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/statoFatture")
public class StatoFatturaController {
    @Autowired
    private StatoFatturaService statoFatturaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN', 'UTENTE')")
    public StatoFattureDtoResp saveIndirizzo(@RequestBody @Validated StatoFatturaDto body, BindingResult validationResult) throws
            BadRequestException {
        if (validationResult.hasErrors()) {
            String messages = validationResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Controlla i seguenti errori: " + messages);
        } else {
            return new StatoFattureDtoResp(this.statoFatturaService.save(body).getIdStatoFattura());
        }
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<StatoFattura> findAll(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(defaultValue = "idStatoFattura") String sortBy) {
        return this.statoFatturaService.findAll(page, size, sortBy);
    }

    @GetMapping("/{idStatoFattura}")
    @PreAuthorize("hasAuthority('ADMIN', 'UTENTE')")
    public StatoFattura findById(@PathVariable UUID idStatoFattura) {
        return this.statoFatturaService.findById(idStatoFattura);
    }

    @PutMapping("/{idStatoFattura}")
    @PreAuthorize("hasAuthority('ADMIN', 'UTENTE')")
    @ResponseStatus(HttpStatus.CREATED)
    public StatoFattureDtoResp findAndUpdate(@PathVariable UUID idStatoFattura, @RequestBody StatoFatturaDto body) throws Throwable {
        return new StatoFattureDtoResp(this.statoFatturaService.findAndUpdate(idStatoFattura, body).getIdStatoFattura());
    }

    @DeleteMapping("/{idStatoFattura}")
    @PreAuthorize("hasAuthority('ADMIN')")// SOLO GI ADMIN POSSONO ELIMINARE LE RISORSE
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable UUID idStatoFattura) {
        this.statoFatturaService.findAndDelete(idStatoFattura);
    }
}
