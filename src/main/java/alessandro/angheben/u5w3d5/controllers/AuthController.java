package alessandro.angheben.u5w3d5.controllers;

import alessandro.angheben.u5w3d5.entities.User;
import alessandro.angheben.u5w3d5.exceptions.BadRequestException;
import alessandro.angheben.u5w3d5.payloads.UserDTO;
import alessandro.angheben.u5w3d5.payloads.UserLoginDTO;
import alessandro.angheben.u5w3d5.payloads.UserLoginResponseDTO;
import alessandro.angheben.u5w3d5.payloads.UserResponseDTO;
import alessandro.angheben.u5w3d5.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO body) {

        String accessToken = authService.authenticateUser(body);
        return new UserLoginResponseDTO(accessToken);
    }

    @PostMapping("/register")
    public UserResponseDTO createUser(@RequestBody @Validated UserDTO newUserPayload, BindingResult validation) {
        if (validation.hasErrors()) {
            System.out.println(validation.getAllErrors());
            throw new BadRequestException(validation.getAllErrors());
        } else {
            User newUser = authService.save(newUserPayload);
            return new UserResponseDTO(newUser.getId(),newUser.getUsername());
        }
    }

}
