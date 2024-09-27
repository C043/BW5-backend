package team7.BW5_team_7.payloads;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record RemoveRuoliDTO(
        @NotEmpty(message = "Almeno un ruolo obbligatorio")
        List<String> ruoli
) {
}
