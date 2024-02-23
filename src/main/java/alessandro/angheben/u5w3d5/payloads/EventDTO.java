package alessandro.angheben.u5w3d5.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EventDTO(

        @NotEmpty(message = "title is mandatory")
        @Size(min = 2, max = 15, message = "title has to start from 2 chars to 15 chars")
        String title,

        @NotEmpty(message = "description is mandatory")
        @Size(min = 5, max = 35, message = "title has to start from 5 chars to 35 chars")
        String description,

        @NotEmpty(message = "date is mandatory")
        String date,

        @NotEmpty(message = "place is mandatory")
                @Size(min = 3, max = 30, message = "title has to start from 3 chars to 30 chars")
        String place,

        @NotNull(message = "maxPeople is mandatory")
        Integer maxPeople


) {
}
