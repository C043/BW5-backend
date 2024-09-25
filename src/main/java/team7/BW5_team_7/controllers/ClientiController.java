package team7.BW5_team_7.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team7.BW5_team_7.entities.Cliente;
import team7.BW5_team_7.payloads.NewClienteDTO;
import team7.BW5_team_7.payloads.RespDTO;
import team7.BW5_team_7.security.Validation;
import team7.BW5_team_7.services.ClientiService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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

    @GetMapping
    public Page<Cliente> getAllClienti(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "ragioneSociale") String sortBy,
                                       @RequestParam(defaultValue = "5") int size) {
        return this.clientiService.findAll(page, size, sortBy);
    }

    @GetMapping("/{clienteId}")
    public Cliente getClienteById(@PathVariable UUID clienteId) {
        return this.clientiService.getClienteById(clienteId);
    }

    @DeleteMapping("/{clienteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCliente(@PathVariable UUID clienteId) {
        this.clientiService.deleteCliente(clienteId);
    }

    @PutMapping("/{clienteId}")
    public RespDTO putCliente(@PathVariable UUID clienteId, @RequestBody @Validated NewClienteDTO body, BindingResult validation) {
        this.validation.validate(validation);
        Cliente updatedCliente = this.clientiService.putCliente(clienteId, body);
        return new RespDTO(updatedCliente.getId());
    }

    @GetMapping("/fatturatoAnnuale")
    public List<Cliente> filterClientiByFatturatoAnnuale(@RequestParam(defaultValue = "0") double minimo) {
        return this.clientiService.filterByFatturatoAnnuo(minimo);
    }

    @GetMapping("/dataInserimento")
    public List<Cliente> filterByDataInserimento(@RequestParam(defaultValue = "2024-09-23") LocalDate data) {
        return this.clientiService.filterByDataInserimento(data);
    }

    @GetMapping("/dataUltimoContatto")
    public List<Cliente> filterByUltimoContatto(@RequestParam(defaultValue = "2024-09-23") LocalDate data) {
        return this.clientiService.filterByDataUltimoContatto(data);
    }

    @GetMapping("/ragioneSociale")
    public List<Cliente> filterByRagioneSociale(@RequestParam(defaultValue = " ") String contains) {
        return this.clientiService.filterByRagioneSociale(contains);
    }
}
