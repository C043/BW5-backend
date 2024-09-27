package team7.BW5_team_7.payloads;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record FatturaDto(
        @NotNull(message = "Il Campo Data è obbligatorio!")
        LocalDate data,

        @Positive(message = "L'importo deve essere un valore positivo!")
        double importo,

        @Positive(message = "Il numero della fattura deve essere un valore positivo!")
        @Min(value = 1)
        int numeroFattura,

        @NotEmpty(message = "Il Campo Stato è obbligatorio!")
        String stato,

        @NotNull(message = "Il Campo Cliente è obbligatorio!")
        String cliente) {
}