package team7.BW5_team_7.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team7.BW5_team_7.entities.Fattura;
import team7.BW5_team_7.services.FatturaService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/fattura")


public class FatturaController {
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

    // Filtra le fatture per cliente
    @GetMapping("/filter/cliente/{clienteId}")
    public ResponseEntity<List<Fattura>> filterByCliente(@PathVariable UUID clienteId) {
        List<Fattura> fatture = fatturaService.filterByCliente(clienteId);
        return ResponseEntity.ok(fatture);
    }

    // Filtra le fatture per stato
    @GetMapping("/filter/stato/{statoFattura}")
    public ResponseEntity<List<Fattura>> filterByStato(@PathVariable String statoFattura) {
        List<Fattura> fatture = fatturaService.filterByStato(statoFattura);
        return ResponseEntity.ok(fatture);
    }

    // Filtra le fatture per data
    @GetMapping("/filter/data")
    public ResponseEntity<List<Fattura>> filterByData(@RequestParam LocalDate data) {
        List<Fattura> fatture = fatturaService.filterByData(data);
        return ResponseEntity.ok(fatture);
    }

    // Filtra le fatture per anno
    @GetMapping("/filter/anno/{anno}")
    public ResponseEntity<List<Fattura>> filterByAnno(@PathVariable int anno) {
        List<Fattura> fatture = fatturaService.filterByAnno(anno);
        return ResponseEntity.ok(fatture);
    }

    // Filtra le fatture per range di importi
    @GetMapping("/filter/range")
    public ResponseEntity<List<Fattura>> filterByRangeImporto(@RequestParam double minImporto, @RequestParam double maxImporto) {
        List<Fattura> fatture = fatturaService.filterByRangeImporto(minImporto, maxImporto);
        return ResponseEntity.ok(fatture);
    }
}
