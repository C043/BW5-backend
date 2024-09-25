package team7.BW5_team_7.security;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import team7.BW5_team_7.Exceptions.BadRequestException;

import java.util.stream.Collectors;

@Component
public class Validation {
    public void validate(BindingResult validation) {
        String messages = validation.getAllErrors().stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.joining(". "));
        if (validation.hasErrors()) throw new BadRequestException("Ci sono stati errori di validazione: " + messages);
    }
}