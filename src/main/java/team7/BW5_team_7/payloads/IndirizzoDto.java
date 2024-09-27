package team7.BW5_team_7.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record IndirizzoDto(
        @NotEmpty(message = "Il Campo Via è obbligatorio!")
        String via,
        @NotNull(message = "Il Campo Civico è obbligatorio!")
        int civico,
        @NotEmpty(message = "Il Campo Città è obbligatorio!")
        String citta,
        @NotEmpty(message = "Il Campo Località è obbligatorio!")
        String cap,
        @NotEmpty(message = "Il Campo Cliente è obbligatorio!")
        String cliente) {
}
