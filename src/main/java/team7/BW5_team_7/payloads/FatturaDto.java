package team7.BW5_team_7.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import team7.BW5_team_7.entities.Cliente;

import java.time.LocalDate;

public record FatturaDto(
        @NotNull(message = "Il Campo Data è obbligatorio!")
        LocalDate data,

        @Positive(message = "L'importo deve essere un valore positivo!")
        double importo,

        @NotEmpty(message = "Il Campo Numero è obbligatorio!")
        String numero,

        @NotEmpty(message = "Il Campo Stato è obbligatorio!")
        String stato,

        @NotNull(message = "Il Campo Cliente è obbligatorio!")
        Cliente cliente) {
}