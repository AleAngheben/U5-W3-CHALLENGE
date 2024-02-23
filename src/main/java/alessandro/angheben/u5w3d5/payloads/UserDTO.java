package alessandro.angheben.u5w3d5.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserDTO(
        @NotEmpty(message = "username is mandatory")
        @Size(min = 3, max = 13, message = "username has to go from 3 to max 13 chars")
        String username,
        @NotEmpty(message = "name is mandatory")
        @Size(min = 5, max = 15, message = "firstName has to go from 5 to max 15 chars")
        String firstName,
        @NotEmpty(message = "surname is mandatory")
        @Size(min = 5, max = 15, message = "lastName has to go from 5 to max 15 chars")
        String lastName,
        @Email(message = "email adress not valid")
        @NotNull(message = "email is manatory")
        String email,
        @NotEmpty(message = "password is mandatory")
        @Size(min = 5, max = 15, message = "password has to go from 5 to max 15 chars")
        String password





) {
}
