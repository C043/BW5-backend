package team7.BW5_team_7.payloads;

import jakarta.validation.constraints.NotEmpty;

import javax.xml.stream.XMLEventWriter;

public record UtenteLoginDTO(
        @NotEmpty(message = "L'email è obbligatoria")
        String email,
        @NotEmpty(message = "Password obbligatoria")
        String password
) {
}
