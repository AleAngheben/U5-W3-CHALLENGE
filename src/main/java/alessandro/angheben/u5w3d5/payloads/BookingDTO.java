package alessandro.angheben.u5w3d5.payloads;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record BookingDTO(

        @NotNull(message = "event id is mandatory!")
        UUID event_id,
        @NotNull(message = "maxAttends is mandatory!")
        int maxAttends,
        @NotNull(message = "user id is mandatory!")
        UUID user_id


) {
}
