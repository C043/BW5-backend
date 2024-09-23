package team7.BW5_team_7.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team7.BW5_team_7.repositories.ClientiRepository;

@Service
public class ClientiService {
    @Autowired
    private ClientiRepository clientiRepository;
}
