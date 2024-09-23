package team7.BW5_team_7.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record NewClienteDTO(
        @NotNull(message = "La ragione sociale è obbligatoria")
        String ragioneSociale,
        @NotNull(message = "La partita iva è obbligatoria")
        String partitaIva,
        @NotNull(message = "L'email è obbligatoria")
        @Email(message = "L'email inserita non è un indirizzo valido")
        String email,
        @NotNull(message = "Il fatturato annuale è obbligatorio")
        double fatturatoAnnuale,
        @NotNull(message = "La pec èè obbligatoria")
        String pec,
        @NotNull(message = "Il telefono è obbligatorio")
        Long telefono,
        @NotNull(message = "L'email contatto è obbligatoria")
        @Email(message = "L'email inserita non è un indirizzo valido")
        String emailContatto,
        @NotNull(message = "Il nome contatto è obbligatorio")
        String nomeContatto,
        @NotNull(message = "Il cognome contatto è obbligatorio")
        String cognomeContatto,
        @NotNull(message = "Il telefono contatto è obbligatorio")
        Long telefonoContatto,
        @NotNull(message = "Il logo aziendale è obbligatorio")
        String logoAziendale,
        @NotNull(message = "Il tipo è obbligatorio")
        @Pattern(regexp = "^(PA|SAS|SPA|SRL)$")
        String tipo
) {
}
