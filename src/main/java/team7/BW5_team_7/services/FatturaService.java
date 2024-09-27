package team7.BW5_team_7.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team7.BW5_team_7.entities.Cliente;
import team7.BW5_team_7.entities.Fattura;
import team7.BW5_team_7.entities.StatoFattura;
import team7.BW5_team_7.exceptions.BadRequestException;
import team7.BW5_team_7.payloads.FatturaDto;
import team7.BW5_team_7.repositories.FatturaRepository;

import java.util.List;
import java.util.UUID;

@Service
public class FatturaService {
    @Autowired
    private ClientiService clientiService;

    @Autowired
    private StatoFatturaService statoFatturaService;

    @Autowired
    private FatturaRepository fatturaRepository;

    public List<Fattura> getAllFatture() {
        return fatturaRepository.findAll();
    }

    public Fattura getFatturaById(UUID id) {
        return fatturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fattura non trovata con id: " + id));
    }

    public Fattura createFattura(FatturaDto body) {
        if (body.numeroFattura() <= 0) {
            throw new BadRequestException("Il numero della fattura deve essere maggiore di zero.");
        }

        Fattura found = this.fatturaRepository.findByNumeroFattura(body.numeroFattura());
        if (found == null) {
            Cliente cliente = this.clientiService.getClienteById(UUID.fromString(body.cliente()));
            StatoFattura statoFattura = this.statoFatturaService.findByName(body.stato());
            Fattura newFattura = new Fattura(body.importo(), body.numeroFattura(), cliente, statoFattura);
            return fatturaRepository.save(newFattura);
        }

        throw new BadRequestException("Il numero " + body.numeroFattura() + " non è disponibile. Il primo numero disponibile per la fattura è " + (fatturaRepository.count() + 1));
    }

    public Fattura updateFattura(UUID id, FatturaDto dettagliFattura) {
        Cliente cliente = this.clientiService.getClienteById(UUID.fromString(dettagliFattura.cliente()));
        StatoFattura statoFattura = this.statoFatturaService.findByName(dettagliFattura.stato());
        Fattura fattura = getFatturaById(id);
        fattura.setData(dettagliFattura.data());
        fattura.setImporto(dettagliFattura.importo());
        fattura.setNumeroFattura(dettagliFattura.numeroFattura());
        fattura.setCliente(cliente);
        fattura.setStatoFattura(statoFattura);
        return fatturaRepository.save(fattura);
    }

    public void deleteFattura(UUID id) {
        Fattura fattura = getFatturaById(id);
        fatturaRepository.delete(fattura);
    }
}
