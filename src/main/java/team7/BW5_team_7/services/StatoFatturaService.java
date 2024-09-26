package team7.BW5_team_7.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team7.BW5_team_7.entities.StatoFattura;
import team7.BW5_team_7.exceptions.BadRequestException;
import team7.BW5_team_7.exceptions.NotFoundException;
import team7.BW5_team_7.payloads.StatoFatturaDto;
import team7.BW5_team_7.repositories.StatoFatturaRepository;

import java.util.UUID;

@Service
public class StatoFatturaService {
    @Autowired
    private StatoFatturaRepository statoFatturaRepository;

    public StatoFattura save(StatoFatturaDto body) {
        StatoFattura found = this.statoFatturaRepository.findByNome(body.nome());
        if (found != null) throw new BadRequestException("Esiste già questo stato fattura");
        StatoFattura statoFattura = new StatoFattura(body.nome());
        return statoFatturaRepository.save(statoFattura);
    }

    public Page<StatoFattura> findAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return statoFatturaRepository.findAll(pageable);
    }

    public StatoFattura findByName(String name) {
        StatoFattura statoFattura = this.statoFatturaRepository.findByNome(name);
        if (statoFattura == null) throw new NotFoundException("Lo stato fattura non è stato trovato");
        return statoFattura;
    }

    public StatoFattura findById(UUID idStatoFattura) {
        return statoFatturaRepository.findById(idStatoFattura).orElseThrow();
    }

    public StatoFattura findAndUpdate(UUID idStatoFattura, StatoFatturaDto body) {
        StatoFattura found = this.findById(idStatoFattura);
        found.setNome(body.nome());
        return statoFatturaRepository.save(found);
    }

    public void findAndDelete(UUID idStatoFattura) {
        StatoFattura found = this.findById(idStatoFattura);
        statoFatturaRepository.delete(found);
    }
}
