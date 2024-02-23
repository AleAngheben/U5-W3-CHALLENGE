package alessandro.angheben.u5w3d5.services;

import alessandro.angheben.u5w3d5.DAO.BookingDAO;
import alessandro.angheben.u5w3d5.DAO.EventDAO;
import alessandro.angheben.u5w3d5.entities.Booking;
import alessandro.angheben.u5w3d5.entities.Event;
import alessandro.angheben.u5w3d5.entities.User;
import alessandro.angheben.u5w3d5.exceptions.NotFoundException;
import alessandro.angheben.u5w3d5.payloads.BookingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class BookingService {

    @Autowired
    private BookingDAO bookingDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    public List<Booking> getAllBookings(){return this.bookingDAO.findAll();}

    public Booking saveBooking(BookingDTO body){
        User user = userService.findById(body.user_id());
        Event event = eventService.findById(body.event_id());
        Booking newBooking = new Booking();
        newBooking.setUser(user);
        newBooking.setEvent(event);
        newBooking.setMaxAttends(body.maxAttends());
        return bookingDAO.save(newBooking);
    }

    public Booking findById(UUID id){
        return bookingDAO.findById(id).orElseThrow(()->new NotFoundException(id));
    }

    public Booking findByIdAndUpdate(UUID id, BookingDTO body){
        Booking found = this.findById(id);
        User user = userService.findById(body.user_id());
        Event event = eventService.findById(body.event_id());

        found.setEvent(event);
        found.setUser(user);
        return bookingDAO.save(found);
    }

    public void findByIdAndDelete(UUID id){
        Booking found = this.findById(id);
        bookingDAO.delete(found);
    }



}
