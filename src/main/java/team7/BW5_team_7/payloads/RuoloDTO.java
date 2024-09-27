package team7.BW5_team_7.payloads;

import jakarta.validation.constraints.NotEmpty;

public record RuoloDTO(
        @NotEmpty(message = "Nome del ruolo obbligatorio")
        String ruolo
) {
}
