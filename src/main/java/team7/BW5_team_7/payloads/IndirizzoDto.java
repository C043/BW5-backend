package team7.BW5_team_7.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import team7.BW5_team_7.entities.Cliente;

public record IndirizzoDto(
        @NotEmpty(message = "Il Campo Via è obbligatorio!")
        String via,
        int civico,
        @NotEmpty(message = "Il Campo Località è obbligatorio!")
        String localita,
        @NotNull(message = "Il Campo Località è obbligatorio!")
        int cap,
        @NotEmpty(message = "Il Campo Comune è obbligatorio!")
        String comune,
        @NotEmpty(message = "Il Campo Cliente è obbligatorio!")
        Cliente cliente) {
}
