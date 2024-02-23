package alessandro.angheben.u5w3d5.DAO;

import alessandro.angheben.u5w3d5.entities.Booking;
import alessandro.angheben.u5w3d5.entities.Event;
import alessandro.angheben.u5w3d5.entities.User;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookingDAO extends JpaRepository<Booking, UUID> {


}
