package team7.BW5_team_7.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team7.BW5_team_7.entities.Fattura;
import team7.BW5_team_7.services.FatturaService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/fatture")
public class FatturaController {

    @Autowired
    private FatturaService fatturaService;

    // Ottieni tutte le fatture
    @GetMapping
    public ResponseEntity<List<Fattura>> getAllFatture() {
        List<Fattura> fatture = fatturaService.getAllFatture();
        return ResponseEntity.ok(fatture);
    }

    // Crea una nuova fattura
    @PostMapping
    public ResponseEntity<Fattura> createFattura(@RequestBody Fattura fattura) {
        Fattura nuovaFattura = fatturaService.createFattura(fattura);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuovaFattura);
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
