package alessandro.angheben.u5w3d5.controllers;

import alessandro.angheben.u5w3d5.entities.Booking;
import alessandro.angheben.u5w3d5.payloads.BookingDTO;
import alessandro.angheben.u5w3d5.payloads.BookingResponseDTO;
import alessandro.angheben.u5w3d5.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Booking> getAllBookings(){return this.bookingService.getAllBookings();}

    @PostMapping

    public BookingResponseDTO saveBooking(@RequestBody BookingDTO payload){
        Booking booking = bookingService.saveBooking(payload);
        return new BookingResponseDTO(booking.getId(),booking.getEvent().getTitle());
    }



}
