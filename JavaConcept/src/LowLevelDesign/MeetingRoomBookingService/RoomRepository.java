package LowLevelDesign.MeetingRoomBookingService;

import java.util.List;
import java.util.Optional;

public interface RoomRepository {
    void save(Room room);
    List<Room> findAll();
    Optional<Room> findById(String roomId);
}
