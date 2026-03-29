package LowLevelDesign.MeetingRoomBookingService;

import java.util.*;

public class InMemoryRoomRepository implements RoomRepository {

    private final Map<String, Room> rooms = new HashMap<>();

    @Override
    public void save(Room room) {
        rooms.put(room.getId(), room);
    }

    @Override
    public List<Room> findAll() {
        return new ArrayList<>(rooms.values());
    }

    @Override
    public Optional<Room> findById(String roomId) {
        return Optional.ofNullable(rooms.get(roomId));
    }
}
