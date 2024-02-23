package alessandro.angheben.u5w3d5.controllers;

import alessandro.angheben.u5w3d5.entities.Booking;
import alessandro.angheben.u5w3d5.entities.User;
import alessandro.angheben.u5w3d5.services.UserService;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> getUsers(){return this.userService.getUsers();}

@GetMapping("/me")
    public User getUser(@AuthenticationPrincipal User user){
        return user;
}

@GetMapping("/me/bookings")
    public List<Booking> getBookingsUser(@AuthenticationPrincipal User user){return user.getBookings();}
}