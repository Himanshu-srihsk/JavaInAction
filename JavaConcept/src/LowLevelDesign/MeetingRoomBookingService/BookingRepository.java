package LowLevelDesign.MeetingRoomBookingService;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository {
    void save(Booking booking);
    List<Booking> findByRoom(String roomId);
    List<Booking> findByRoomAndDate(String roomId, LocalDate date);
    List<Booking> findAll();
}
