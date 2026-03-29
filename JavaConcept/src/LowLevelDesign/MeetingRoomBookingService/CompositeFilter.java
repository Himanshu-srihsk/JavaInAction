package LowLevelDesign.MeetingRoomBookingService;

import java.util.List;

public class CompositeFilter implements RoomFilter {

    private final List<RoomFilter> filters;

    public CompositeFilter(List<RoomFilter> filters) {
        this.filters = filters;
    }

    @Override
    public boolean test(Room room) {
        for (RoomFilter f : filters) {
            if (!f.test(room)) return false;
        }
        return true;
    }
}
