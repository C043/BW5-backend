package team7.BW5_team_7.component;


import kong.unirest.core.HttpResponse;
import kong.unirest.core.JsonNode;
import kong.unirest.core.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import team7.BW5_team_7.entities.Cliente;

@Component
public class MailgunSender {
    private final String apyKey;
    private final String domainName;
    private final String emailSendFrom;

    public MailgunSender(@Value("${mailgun.key}") String apyKey, @Value("${mailgun.domain}") String domainName, @Value("${email.send}") String emailSendFrom) {
        this.apyKey = apyKey;
        this.domainName = domainName;
        this.emailSendFrom = emailSendFrom;
    }

    public void sendRegistrationEmail(Cliente recpient) {
        HttpResponse<JsonNode> response = Unirest.post("https://api.mailgun.net/v3/" + this.domainName + "/messages")
                .basicAuth("api", this.apyKey)
                .queryString("from", this.emailSendFrom)
                .queryString("to", recpient.getEmailContatto())
                .queryString("subject", "Registrazione completata")
                .queryString("text", "Ciao " + recpient.getNomeContatto() + ", grazie per esserti registrato!")
                .asJson();
        System.out.println("Status: " + response.getStatus());
        System.out.println("Body: " + response.getBody());
        System.out.println("Headers: " + response.getHeaders());
        if (response.getStatus() == 200) {
            System.out.println("Il messaggio è stato inviato con successo: " + response.getBody());
        } else {
            System.err.println("Errore durante l'invio dell'email: " + response.getStatus() + " - " + response.getBody());
        }
    }
}
