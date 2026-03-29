package LowLevelDesign.MeetingRoomBookingService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AvailabilityService {

    private final BookingRepository repo;

    public AvailabilityService(BookingRepository repo) {
        this.repo = repo;
    }

    public boolean isAvailable(String roomId, LocalDateTime start, LocalDateTime end) {
        for (Booking b : repo.findByRoom(roomId)) {
            if (b.getStatus() == BookingStatus.BOOKED &&
                    b.isOverlapping(start, end)) {
                return false;
            }
        }
        return true;
    }
}