package team7.BW5_team_7.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import team7.BW5_team_7.entities.Utente;
import team7.BW5_team_7.exceptions.BadRequestException;
import team7.BW5_team_7.payloads.UtenteLoginDTO;
import team7.BW5_team_7.payloads.UtenteLoginRespDTO;
import team7.BW5_team_7.security.JWTTools;

@Service
public class AuthService {

    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private PasswordEncoder bcrypt;
    @Autowired
    private UtenteService utenteService;

    // checkAndCreateToken
    public UtenteLoginRespDTO checkAndCreateToken(UtenteLoginDTO body){

        // controlliamo che sia presente nel db la seguente email ed estrapoliamo l'utente
        // se trova l'utente lo inserisco in una istanza
        Utente utenteTrovato = this.utenteService.findByEmail(body.email());

        // creo il token
        if (bcrypt.matches(body.password(), utenteTrovato.getPassword())) return new UtenteLoginRespDTO(this.jwtTools.createToken(utenteTrovato));
        // TODO: modificare exception
        else throw new BadRequestException("Errore nella creazione del token");
    }

}
