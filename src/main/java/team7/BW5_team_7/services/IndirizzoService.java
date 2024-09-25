package team7.BW5_team_7.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team7.BW5_team_7.entities.Indirizzo;
import team7.BW5_team_7.payloads.IndirizzoDto;
import team7.BW5_team_7.repositories.IndirizzoRepository;

import java.util.UUID;

@Service
public class IndirizzoService {

    private IndirizzoRepository indirizzoRepository;

    public Indirizzo save(IndirizzoDto body) {
        Indirizzo indirizzo = new Indirizzo(body.via(), body.civico(), body.localita(), body.cap(), body.comune(), body.cliente());
        return indirizzoRepository.save(indirizzo);
    }

    public Page<Indirizzo> findAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return indirizzoRepository.findAll(pageable);
    }

    public Indirizzo findById(UUID idIndirizzo) {
        return indirizzoRepository.findById(idIndirizzo).orElseThrow();
    }

    public Indirizzo findAndUpdate(UUID idIndirizzo, IndirizzoDto body) throws Throwable {
        Indirizzo found = this.findById(idIndirizzo);
        found.setVia(body.via());
        found.setCivico(body.civico());
        found.setLocalita(body.localita());
        found.setCap(body.cap());
        found.setComune(body.comune());
        found.setCliente(body.cliente());
        return this.indirizzoRepository.save(found);
    }

    public void findAndDelete(UUID idIndirizzo) {
        Indirizzo found = this.findById(idIndirizzo);
        indirizzoRepository.delete(found);
    }


}

