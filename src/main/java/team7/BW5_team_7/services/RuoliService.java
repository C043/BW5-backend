package team7.BW5_team_7.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import team7.BW5_team_7.Exceptions.BadRequestException;
import team7.BW5_team_7.Exceptions.NotFoundException;
import team7.BW5_team_7.entities.Ruolo;
import team7.BW5_team_7.payloads.RuoloDTO;
import team7.BW5_team_7.payloads.RuoloRespDTO;
import team7.BW5_team_7.repositories.RuoliRepository;

@Service
public class RuoliService {

    @Autowired
    private RuoliRepository ruoliRepository;



    public Page<Ruolo> findAllRoles(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return this.ruoliRepository.findAll(pageable);
    }

    public Ruolo findByRuolo(String ruolo){
        return this.ruoliRepository.findByRuolo(ruolo).orElseThrow(() -> new NotFoundException("Ruolo non trovato!"));
    }

    public boolean existsByRuolo(String ruolo){
        return this.ruoliRepository.existsByRuolo(ruolo);
    }

    public void saveRuolo(Ruolo ruolo){
        this.ruoliRepository.save(ruolo);
    }

    public RuoloRespDTO saveNewRuolo(RuoloDTO body){
        if (this.ruoliRepository.existsByRuolo(body.ruolo())) throw new BadRequestException("Il ruolo creato esiste già");

        Ruolo newRuolo = new Ruolo(body.ruolo());
        this.ruoliRepository.save(newRuolo);

        return new RuoloRespDTO(newRuolo.getId());
    }

}
