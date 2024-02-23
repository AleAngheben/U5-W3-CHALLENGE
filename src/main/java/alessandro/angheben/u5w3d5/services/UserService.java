package alessandro.angheben.u5w3d5.services;

import alessandro.angheben.u5w3d5.DAO.UserDAO;
import alessandro.angheben.u5w3d5.entities.User;
import alessandro.angheben.u5w3d5.enums.Role;
import alessandro.angheben.u5w3d5.exceptions.BadRequestException;
import alessandro.angheben.u5w3d5.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public List<User> getUsers() {
        return this.userDAO.findAll();
    }

    public User findById(UUID id) {
        return userDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public User findByEmail(String email) {
        return userDAO.findByEmail(email).orElseThrow(() -> new NotFoundException("User con email " + email + " non trovato!"));

    }

    public User findByIdAndUpdate(UUID id, User body) {
        User found = this.findById(id);
        if (body.getUsername() != null) {
            found.setUsername(body.getUsername());
        }
        if (body.getFirstName() != null) {
            found.setFirstName(body.getFirstName());
        }
        if (body.getLastName() != null) {
            found.setLastName(body.getLastName());
        }
        if (body.getEmail() != null) {
            found.setEmail(body.getEmail());
        }
        if (body.getPassword() != null) {
            found.setPassword(body.getPassword());
        }
        if (body.getRole() != null) {
            found.setRole(body.getRole());
        }

        return userDAO.save(found);
    }

    public User findByTokenAndChangeRole(User user) {
       // User userToChange = this.findById(id);
        if (user.getRole().name().equals("BASIC")) {
            user.setRole(Role.ADMIN);
        } else if (user.getRole().name().equals("ADMIN")) {
            user.setRole(Role.BASIC);
        } else {
            throw new BadRequestException("You can choose BASIC or ADMIN");
        }
        return userDAO.save(user);
    }

    public void findByIdAndDelete(UUID id) {
        User found = this.findById(id);
        if (!found.getBookings().isEmpty()) {
            throw new BadRequestException("ATTENZIONE impossibile cancellare User di id: " + id + " è associato a uno o più prenotazioni.");
        }
        userDAO.delete(found);
    }


}
