package team7.BW5_team_7.payloads;

import java.time.LocalDateTime;

public record ErrorPayload(
        String message,
        LocalDateTime timestamp
) {
}
