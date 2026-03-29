package LowLevelDesign.MeetingRoomBookingService;

import java.util.Set;

public class MultiAmenityFilter implements RoomFilter {

    private final Set<Amenity> required;

    public MultiAmenityFilter(Set<Amenity> required) {
        this.required = required;
    }

    @Override
    public boolean test(Room room) {
        return room.getAmenities().containsAll(required);
    }
}
