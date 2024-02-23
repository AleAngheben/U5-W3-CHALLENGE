package alessandro.angheben.u5w3d5.controllers;

import alessandro.angheben.u5w3d5.entities.Event;
import alessandro.angheben.u5w3d5.payloads.EventDTO;
import alessandro.angheben.u5w3d5.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public List<Event> getEvents(){return this.eventService.getEvents();}
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Event saveEvent(@RequestBody EventDTO body){
        return eventService.saveEvento(body);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Event modifyEvent(@PathVariable UUID id, @RequestBody Event body){
        return eventService.findByIdAndUpdate(id, body);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void delteEvent(@PathVariable UUID id){
        eventService.findByIdAndDelete(id);
    }





}
