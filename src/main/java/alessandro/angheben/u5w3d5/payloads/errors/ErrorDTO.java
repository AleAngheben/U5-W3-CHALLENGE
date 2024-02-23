package alessandro.angheben.u5w3d5.payloads.errors;

import java.time.LocalDateTime;

public record ErrorDTO(String message, LocalDateTime time) {
}
