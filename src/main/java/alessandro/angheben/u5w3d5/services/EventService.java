package alessandro.angheben.u5w3d5.services;

import alessandro.angheben.u5w3d5.DAO.EventDAO;
import alessandro.angheben.u5w3d5.entities.Event;
import alessandro.angheben.u5w3d5.exceptions.BadRequestException;
import alessandro.angheben.u5w3d5.exceptions.NotFoundException;
import alessandro.angheben.u5w3d5.payloads.EventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventService {

    @Autowired
    private EventDAO eventDAO;

    public List<Event> getEvents() {
        return this.eventDAO.findAll();
    }

    public Event findById(UUID id) {
        return eventDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Event findByIdAndUpdate(UUID id, Event body){
        Event found = this.findById(id);
        if (body.getTitle() != null) {found.setTitle(body.getTitle());}
        if (body.getDescription() != null) {found.setDescription(body.getDescription());}
        if (body.getDate() != null) {found.setDate(body.getDate());}
        if (body.getPlace() != null) {found.setPlace(body.getPlace());}
        if (body.getMaxPeople() != 0) {found.setMaxPeople(body.getMaxPeople());}


        return eventDAO.save(found);
    }

    public void findByIdAndDelete(UUID id){
        Event found = this.findById(id);
        if(!found.getBookings().isEmpty()){
            throw new BadRequestException("WARNING! can't delete event with id : " + id + " one or more ");
        }
        eventDAO.delete(found);
    }

    public Event saveEvento(EventDTO payload){
        Event newEvento = new Event();
        newEvento.setTitle(payload.title());
        newEvento.setDate(payload.date());
        newEvento.setDescription(payload.description());
        newEvento.setPlace(payload.place());
        newEvento.setMaxPeople(payload.maxPeople());

        return eventDAO.save(newEvento);
    }


}
