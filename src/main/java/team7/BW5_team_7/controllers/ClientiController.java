package team7.BW5_team_7.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team7.BW5_team_7.entities.Cliente;
import team7.BW5_team_7.payloads.NewClienteDTO;
import team7.BW5_team_7.payloads.RespDTO;
import team7.BW5_team_7.security.Validation;
import team7.BW5_team_7.services.ClientiService;

@RestController
@RequestMapping("/clienti")
public class ClientiController {
    @Autowired
    private ClientiService clientiService;

    @Autowired
    private Validation validation;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RespDTO postCliente(@RequestBody @Validated NewClienteDTO body, BindingResult validation) {
        this.validation.validate(validation);
        Cliente newCliente = this.clientiService.postCliente(body);
        return new RespDTO(newCliente.getId());
    }
}
