package team7.BW5_team_7.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team7.BW5_team_7.component.MailgunSender;
import team7.BW5_team_7.entities.Cliente;
import team7.BW5_team_7.enums.TipoCliente;
import team7.BW5_team_7.exceptions.BadRequestException;
import team7.BW5_team_7.exceptions.NotFoundException;
import team7.BW5_team_7.payloads.NewClienteDTO;
import team7.BW5_team_7.repositories.ClientiRepository;
import team7.BW5_team_7.repositories.IndirizzoRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ClientiService {
    @Autowired
    private ClientiRepository clientiRepository;

    @Autowired
    private IndirizzoRepository indirizzoRepository;

    @Autowired
    private ComuneService comuneService;

    @Autowired
    private IndirizzoService indirizzoService;

    @Autowired
    private MailgunSender mailgunSender;

    public Cliente postCliente(NewClienteDTO body) {
        if (this.clientiRepository.existsByEmail(body.email())) throw new BadRequestException("Il cliente esiste già");
        Cliente clienteDaSalvare = this.indirizzoService.saveCliente(body);
        clienteDaSalvare.setLogoAziendale("https://ui-avatars.com/api/?name=" + clienteDaSalvare.getRagioneSociale().split(" ")[0] + clienteDaSalvare.getRagioneSociale().split(" ")[1]);
        mailgunSender.sendRegistrationEmail(clienteDaSalvare);
        return clienteDaSalvare;
    }

    public Page<Cliente> findAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.clientiRepository.findAll(pageable);
    }

    public List<Cliente> findAll() {
        return this.clientiRepository.findAll();
    }

    public Cliente getClienteById(UUID id) {
        return this.clientiRepository.findById(id).orElseThrow(() -> new NotFoundException("Nessun cliente trovato con id: " + id));
    }

    public void deleteCliente(UUID id) {
        Cliente found = this.getClienteById(id);
        this.clientiRepository.delete(found);
    }

    public Cliente putCliente(UUID id, NewClienteDTO body) {
        Cliente found = this.getClienteById(id);
        found.setCognomeContatto(body.cognomeContatto());
        found.setEmail(body.email());
        found.setPec(body.pec());
        found.setEmailContatto(body.emailContatto());
        found.setTipo(TipoCliente.valueOf(body.tipo()));
        found.setTelefono(body.telefono());
        found.setNomeContatto(body.nomeContatto());
        found.setRagioneSociale(body.ragioneSociale());
        found.setPartitaIva(body.partitaIva());
        found.setFatturatoAnnuale(body.fatturatoAnnuale());
        found.setTelefonoContatto(body.telefonoContatto());
        this.clientiRepository.save(found);
        return found;
    }
}