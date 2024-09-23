package team7.BW5_team_7.payloads;

import jakarta.validation.constraints.NotEmpty;

public record StatoFattura(
        @NotEmpty(message = "Il Campo Località è obbligatorio!")
        String nome) {
}
