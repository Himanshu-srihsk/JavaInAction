package LowLevelDesign.MeetingRoomBookingService;

public class CapacityFilter implements RoomFilter {

    private final int minCapacity;

    public CapacityFilter(int minCapacity) {
        this.minCapacity = minCapacity;
    }

    @Override
    public boolean test(Room room) {
        return room.getCapacity() >= minCapacity;
    }
}
