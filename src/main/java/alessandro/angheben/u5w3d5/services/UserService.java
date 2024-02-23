package alessandro.angheben.u5w3d5.services;

import alessandro.angheben.u5w3d5.DAO.UserDAO;
import alessandro.angheben.u5w3d5.entities.User;
import alessandro.angheben.u5w3d5.exceptions.BadRequestException;
import alessandro.angheben.u5w3d5.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void findByIdAndDelete(UUID id) {
        User found = this.findById(id);
        if (!found.getBookings().isEmpty()) {
            throw new BadRequestException("ATTENZIONE impossibile cancellare User di id: " + id + " è associato a uno o più prenotazioni.");
        }
        userDAO.delete(found);
    }


}
