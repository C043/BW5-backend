package team7.BW5_team_7.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team7.BW5_team_7.entities.Cliente;
import team7.BW5_team_7.entities.Fattura;
import team7.BW5_team_7.entities.FatturaSpec;
import team7.BW5_team_7.payloads.FatturaDto;
import team7.BW5_team_7.repositories.FatturaRepository;
import team7.BW5_team_7.services.ClientiService;
import team7.BW5_team_7.services.FatturaService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/fatture")
public class FatturaController {

    @Autowired
    private FatturaService fatturaService;

    @Autowired
    private ClientiService clientiService;

    @Autowired
    private FatturaRepository fatturaRepository;

    // Ottieni tutte le fatture
    @GetMapping
    public List<Fattura> getAllFatture(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "5") int size,
                                       @RequestParam(defaultValue = "") String sortBy,
                                       @RequestParam(required = false) UUID cliente) {
        if (cliente == null) return fatturaService.getAllFatture();
        Cliente found = this.clientiService.getClienteById(cliente);
        Specification<Fattura> spec = Specification.where(FatturaSpec.clienteFilter(found));
        return this.fatturaRepository.findAll(spec);
    }

    // Crea una nuova fattura
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Fattura createFattura(@RequestBody FatturaDto body) {
        return this.fatturaService.createFattura(body);
    }

    // Ottieni una fattura per ID
    @GetMapping("/{id}")
    public ResponseEntity<Fattura> getFatturaById(@PathVariable UUID id) {
        Fattura fattura = fatturaService.getFatturaById(id);
        return ResponseEntity.ok(fattura);
    }

    // Aggiorna una fattura
    @PutMapping("/{id}")
    public ResponseEntity<Fattura> updateFattura(@PathVariable UUID id, @RequestBody Fattura dettagliFattura) {
        Fattura fatturaAggiornata = fatturaService.updateFattura(id, dettagliFattura);
        return ResponseEntity.ok(fatturaAggiornata);
    }

    // Elimina una fattura
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFattura(@PathVariable UUID id) {
        fatturaService.deleteFattura(id);
        return ResponseEntity.noContent().build();
    }
}
