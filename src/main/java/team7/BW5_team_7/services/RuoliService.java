package team7.BW5_team_7.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team7.BW5_team_7.entities.Ruolo;
import team7.BW5_team_7.repositories.RuoliRepository;

@Service
public class RuoliService {
    @Autowired
    private RuoliRepository ruoliRepository;

    public Ruolo findByRuolo(String ruolo){
        return this.ruoliRepository.findByRuolo(ruolo).orElseThrow(() -> new RuntimeException("Ruolo non trovato!"));
    }

    public boolean existsByRuolo(String ruolo){
        return this.ruoliRepository.existsByRuolo(ruolo);
    }

    public void saveRuolo(Ruolo ruolo){
        this.ruoliRepository.save(ruolo);
    }

}
