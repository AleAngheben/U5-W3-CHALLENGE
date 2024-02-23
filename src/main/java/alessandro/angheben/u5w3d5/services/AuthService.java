package alessandro.angheben.u5w3d5.services;

import alessandro.angheben.u5w3d5.DAO.UserDAO;
import alessandro.angheben.u5w3d5.entities.User;
import alessandro.angheben.u5w3d5.enums.Role;
import alessandro.angheben.u5w3d5.exceptions.BadRequestException;
import alessandro.angheben.u5w3d5.exceptions.UnauthorizedException;
import alessandro.angheben.u5w3d5.payloads.UserDTO;
import alessandro.angheben.u5w3d5.payloads.UserLoginDTO;
import alessandro.angheben.u5w3d5.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService userService;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder bcrypt;

    public String authenticateUser(UserLoginDTO body) {

        User user = userService.findByEmail(body.email());
        if (bcrypt.matches(body.password(), user.getPassword())) {
            return jwtTools.createToken(user);
        } else {
            throw new UnauthorizedException("not valid!!");
        }
    }

    public User save(UserDTO body) {
        userDAO.findByEmail(body.email()).ifPresent(user -> {
            throw new BadRequestException("email " + user.getEmail() + " already registered!");
        });
        userDAO.findByUsername(body.username()).ifPresent(user -> {throw new BadRequestException("username " + user.getUsername() + " already used");});

        User newUser = new User();
        newUser.setFirstName(body.firstName());
        newUser.setLastName(body.lastName());
        newUser.setUsername(body.username());
        newUser.setEmail(body.email());
        newUser.setPassword(bcrypt.encode(body.password()));
        newUser.setRole(Role.BASIC);
        return userDAO.save(newUser);

    }


}