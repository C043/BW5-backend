package team7.BW5_team_7.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UtenteDTO(
        @NotEmpty(message = "Username obbligatorio")
        String username,
        @NotEmpty(message = "email obbligatorio")
        @Email
        String email,
        @NotEmpty(message = "password obbligatorio")
        //@Pattern(regexp = "/^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/")
        @Size(min = 4, max = 16, message = "la password deve avere da 4 a 16 caratteri massimo")
        String password,
        @NotEmpty(message = "nome obbligatorio")
        String nome,
        @NotEmpty(message = "cognome obbligatorio")
        String cognome

) {
    /*  Ha una lunghezza minima di 8 caratteri. Regolalo modificando {8,}
        Almeno una lettera inglese maiuscola. Puoi rimuovere questa condizione rimuovendo (?=.*?[AZ])
        Almeno una lettera minuscola inglese. Puoi rimuovere questa condizione rimuovendo (?=.*?[az])
        Almeno una cifra. Puoi rimuovere questa condizione rimuovendo (?=.*?[0-9])
        Almeno un carattere speciale. Puoi rimuovere questa condizione rimuovendo (?=.*?[#?!@$%^&*-])
    */
}
