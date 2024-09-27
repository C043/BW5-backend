package team7.BW5_team_7.payloads;

import jakarta.validation.constraints.*;

public record NewClienteDTO(
        @NotEmpty(message = "La ragione sociale è obbligatoria")
        String ragioneSociale,
        @NotEmpty(message = "La partita iva è obbligatoria")
        String partitaIva,
        @NotEmpty(message = "L'email è obbligatoria")
        @Email(message = "L'email inserita non è un indirizzo valido")
        String email,
        @NotNull(message = "Il fatturato annuale è obbligatorio")
        double fatturatoAnnuale,
        @NotEmpty(message = "La pec èè obbligatoria")
        String pec,
        @NotNull(message = "Il telefono è obbligatorio")
        Long telefono,
        @NotEmpty(message = "L'email contatto è obbligatoria")
        @Email(message = "L'email inserita non è un indirizzo valido")
        String emailContatto,
        @NotEmpty(message = "Il nome contatto è obbligatorio")
        String nomeContatto,
        @NotEmpty(message = "Il cognome contatto è obbligatorio")
        String cognomeContatto,
        @NotNull(message = "Il telefono contatto è obbligatorio")
        Long telefonoContatto,
        @NotEmpty(message = "Il logo aziendale è obbligatorio")
        String logoAziendale,
        @NotEmpty(message = "Il tipo è obbligatorio")
        @Pattern(regexp = "^(PA|SAS|SPA|SRL)$")
        String tipo,
        @NotEmpty(message = "Il Campo Via è obbligatorio!")
        String via,
        @NotNull(message = "Il Campo Civico è obbligatorio!")
        @Min(value = 1, message = "Il civico non è valido")
        int civico,
        @NotEmpty(message = "Il Campo Città è obbligatorio!")
        String citta,
        @NotNull(message = "Il Campo Località è obbligatorio!")
        @Min(value = 1, message = "Il cap non è valido")
        int cap
) {
}
