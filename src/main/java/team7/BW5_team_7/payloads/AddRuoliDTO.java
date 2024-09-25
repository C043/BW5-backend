package team7.BW5_team_7.payloads;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record AddRuoliDTO(
        @NotEmpty(message = "È obbliogatorio inserire almeno un  ruolo")
        List<String> ruoli
) {
}
