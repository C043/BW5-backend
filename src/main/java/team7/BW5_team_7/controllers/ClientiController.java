package team7.BW5_team_7.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team7.BW5_team_7.entities.Cliente;
import team7.BW5_team_7.entities.CustomerSpec;
import team7.BW5_team_7.payloads.NewClienteDTO;
import team7.BW5_team_7.payloads.RespDTO;
import team7.BW5_team_7.repositories.ClientiRepository;
import team7.BW5_team_7.security.Validation;
import team7.BW5_team_7.services.ClientiService;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/clienti")
public class ClientiController {
    @Autowired
    private ClientiService clientiService;

    @Autowired
    private Validation validation;

    @Autowired
    private ClientiRepository clientiRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RespDTO postCliente(@RequestBody @Validated NewClienteDTO body, BindingResult validation) {
        this.validation.validate(validation);
        Cliente newCliente = this.clientiService.postCliente(body);
        return new RespDTO(newCliente.getId());
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN', 'UTENTE')")
    public Object getAllClienti(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "ragioneSociale") String sortBy,
                                @RequestParam(defaultValue = "5") int size,
                                @RequestParam(required = false) String fatturatoAnnuale,
                                @RequestParam(required = false) LocalDate dataInserimento,
                                @RequestParam(required = false) LocalDate ultimoContatto,
                                @RequestParam(required = false) String ragioneSociale) {
        if (fatturatoAnnuale == null && ultimoContatto == null && ragioneSociale == null && dataInserimento == null)
            return this.clientiService.findAll(page, size, sortBy);
        Specification<Cliente> spec =
                Specification.where(CustomerSpec.fatturatoAnnualeFilter(fatturatoAnnuale))
                        .and(CustomerSpec.parteDellaRagioneSocialeFilter(ragioneSociale))
                        .and(CustomerSpec.dataInserimentoFilter(dataInserimento))
                        .and(CustomerSpec.dataUltimoContattoFilter(ultimoContatto));
        return this.clientiRepository.findAll(spec);
    }

    @GetMapping("/{clienteId}")
    @PreAuthorize("hasAuthority('ADMIN', 'UTENTE')")
    public Cliente getClienteById(@PathVariable UUID clienteId) {
        return this.clientiService.getClienteById(clienteId);
    }

    @DeleteMapping("/{clienteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteCliente(@PathVariable UUID clienteId) {
        this.clientiService.deleteCliente(clienteId);
    }

    @PutMapping("/{clienteId}")
    @PreAuthorize("hasAuthority('ADMIN', 'UTENTE')")
    public RespDTO putCliente(@PathVariable UUID clienteId, @RequestBody @Validated NewClienteDTO body, BindingResult validation) {
        this.validation.validate(validation);
        Cliente updatedCliente = this.clientiService.putCliente(clienteId, body);
        return new RespDTO(updatedCliente.getId());
    }
}
