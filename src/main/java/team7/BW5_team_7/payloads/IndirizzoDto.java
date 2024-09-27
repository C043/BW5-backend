package team7.BW5_team_7.payloads;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record IndirizzoDto(
        @NotEmpty(message = "Il Campo Via è obbligatorio!")
        String via,
        @NotNull(message = "Il Campo Civico è obbligatorio!")
        @Min(value = 1, message = "Il numero civico inserito non è valido")
        int civico,
        @NotEmpty(message = "Il Campo Città è obbligatorio!")
        String citta,
        @NotNull(message = "Il Campo Località è obbligatorio!")
        @Min(value = 1, message = "Il cap inserito non è valido")
        int cap,
        @NotEmpty(message = "Il Campo Cliente è obbligatorio!")
        String cliente) {
}
