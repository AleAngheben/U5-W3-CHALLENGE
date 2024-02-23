package alessandro.angheben.u5w3d5.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "events")
@NoArgsConstructor
public class Event {

   @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String title;
    private String description;
    private LocalDate date;
    private String place;
    private int maxPeople;

    @OneToMany (mappedBy = "event")
    @JsonIgnore
    private List<Booking> bookings;

    public Event(String title, String description, LocalDate date, String place, int maxPeople, List<Booking> bookings) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.place = place;
        this.maxPeople = maxPeople;
        this.bookings = bookings;
    }
}
