package team7.BW5_team_7.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team7.BW5_team_7.entities.Cliente;
import team7.BW5_team_7.entities.Comune;
import team7.BW5_team_7.entities.Indirizzo;
import team7.BW5_team_7.enums.TipoIndirizzo;
import team7.BW5_team_7.exceptions.NotFoundException;
import team7.BW5_team_7.payloads.IndirizzoDto;
import team7.BW5_team_7.repositories.ComuneRepository;
import team7.BW5_team_7.repositories.IndirizzoRepository;

import java.util.UUID;

@Service
public class IndirizzoService {
    @Autowired
    private IndirizzoRepository indirizzoRepository;
    @Autowired
    private ComuneRepository comuneRepository;
    @Autowired
    private ClientiService clientiService;

    public Indirizzo save(IndirizzoDto body) {

        Comune foundComune = this.comuneRepository.findByDenominazione(body.citta());
        if (foundComune == null) throw new NotFoundException("il Comune cercato non è Stato trovato!");
        Cliente foundCliente = this.clientiService.getClienteById(UUID.fromString(body.cliente()));
        if (foundCliente == null) throw new NotFoundException("il Comune cercato non è Stato trovato!");
        Indirizzo indirizzo = new Indirizzo(body.via(), body.civico(), body.cap(), foundComune, foundCliente);
        return indirizzoRepository.save(indirizzo);
    }

    public Page<Indirizzo> findAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return indirizzoRepository.findAll(pageable);
    }

    public Indirizzo findById(UUID idIndirizzo) {
        return indirizzoRepository.findById(idIndirizzo).orElseThrow(() -> new NotFoundException("Indirizzo Non Trovato!"));
    }

    public Indirizzo findAndUpdate(UUID idIndirizzo, IndirizzoDto body) throws Throwable {
        Comune foundComune = this.comuneRepository.findByDenominazione(body.citta());
        if (foundComune == null) throw new NotFoundException("il Comune cercato non è Stato trovato!");
        Cliente foundCliente = this.clientiService.getClienteById(UUID.fromString(body.cliente()));
        if (foundCliente == null) throw new NotFoundException("il Comune cercato non è Stato trovato!");
        Indirizzo found = this.findById(idIndirizzo);
        found.setVia(body.via());
        found.setCivico(body.civico());
        found.setCap(body.cap());
        found.setCitta(foundComune);
        found.setProvincia(foundComune.getProvincia());
        found.setCliente(foundCliente);
        return this.indirizzoRepository.save(found);
    }

    public void findAndDelete(UUID idIndirizzo) {
        Indirizzo found = this.findById(idIndirizzo);
        indirizzoRepository.delete(found);
    }

    public Indirizzo findIndirizzoSetSede(UUID idIndirizzo, Indirizzo sede) {
        Indirizzo found = this.findById(idIndirizzo);
        found.setTipoIndirizzo(TipoIndirizzo.SEDE_OPERATIVA);

        return this.indirizzoRepository.save(found);

    }

    public Indirizzo findIndirizzoSetSedeAmministrativa(UUID idIndirizzo, Indirizzo sede) {
        Indirizzo found = this.findById(idIndirizzo);
        found.setTipoIndirizzo(TipoIndirizzo.SEDE_LEGALE);

        return this.indirizzoRepository.save(found);

    }
}

