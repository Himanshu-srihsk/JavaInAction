package LowLevelDesign.MeetingRoomBookingService;

import java.util.Set;

public class Room {
    private final String id;
    private final int capacity;
    private final Set<Amenity> amenities;

    public Room(String id, int capacity, Set<Amenity> amenities) {
        this.id = id;
        this.capacity = capacity;
        this.amenities = amenities;
    }

    public String getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }
    public Set<Amenity> getAmenities() {
        return amenities;
    }
}
