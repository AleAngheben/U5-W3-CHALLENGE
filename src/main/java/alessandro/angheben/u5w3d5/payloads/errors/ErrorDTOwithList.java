package alessandro.angheben.u5w3d5.payloads.errors;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorDTOwithList(String message,
                               LocalDateTime timestamp,
                               List<String> errorsList) {
}
