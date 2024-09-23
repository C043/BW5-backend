package team7.BW5_team_7.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team7.BW5_team_7.entities.Cliente;
import team7.BW5_team_7.payloads.NewClienteDTO;
import team7.BW5_team_7.repositories.ClientiRepository;

@Service
public class ClientiService {
    @Autowired
    private ClientiRepository clientiRepository;

    public Cliente postCliente(NewClienteDTO body) {
        /*TO DO: metodo save*/
        return this.clientiRepository.save();
    }
}
