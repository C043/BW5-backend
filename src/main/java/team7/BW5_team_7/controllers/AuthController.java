package team7.BW5_team_7.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team7.BW5_team_7.payloads.UtenteDTO;
import team7.BW5_team_7.payloads.UtenteLoginDTO;
import team7.BW5_team_7.payloads.UtenteLoginRespDTO;
import team7.BW5_team_7.payloads.UtenteRespDTO;
import team7.BW5_team_7.services.AuthService;
import team7.BW5_team_7.services.UtenteService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UtenteService utenteService;
    @Autowired
    private AuthService authService;

    // metodo per creare il token
    @PostMapping("/login")
    public UtenteLoginRespDTO findAndCreateToken(@RequestBody UtenteLoginDTO body){
        return this.authService.checkAndCreateToken(body);
    }

    // endpoint per creare un nuovo utente
    // TODO: validare assolutamente i dati in ingresso con il @Validated (Mario ha già creato una classe apposita, grande Mario!)
    @PostMapping("/register")
    public UtenteRespDTO saveNewUtente(@RequestBody UtenteDTO body){
        return this.utenteService.save(body);
    }

}
