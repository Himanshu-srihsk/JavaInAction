package LowLevelDesign.MeetingRoomBookingService;

import java.time.LocalDateTime;

public class AvailabilityFilter implements RoomFilter {

    private final AvailabilityService availabilityService;
    private final LocalDateTime start;
    private final LocalDateTime end;

    public AvailabilityFilter(AvailabilityService availabilityService, LocalDateTime start, LocalDateTime end) {
        this.availabilityService = availabilityService;
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean test(Room room) {
        return availabilityService.isAvailable(room.getId(), start, end);
    }
}
