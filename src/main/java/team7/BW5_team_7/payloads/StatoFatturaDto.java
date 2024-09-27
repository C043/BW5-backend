package team7.BW5_team_7.payloads;

import jakarta.validation.constraints.NotEmpty;

public record StatoFatturaDto(
        @NotEmpty(message = "Il Campo nome è obbligatorio!")
        String nome) {
}
