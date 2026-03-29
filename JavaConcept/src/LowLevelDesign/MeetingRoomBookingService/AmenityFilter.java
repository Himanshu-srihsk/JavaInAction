package LowLevelDesign.MeetingRoomBookingService;

public class AmenityFilter implements RoomFilter {

    private final Amenity amenity;

    public AmenityFilter(Amenity amenity) {
        this.amenity = amenity;
    }

    @Override
    public boolean test(Room room) {
        return room.getAmenities().contains(amenity);
    }
}
