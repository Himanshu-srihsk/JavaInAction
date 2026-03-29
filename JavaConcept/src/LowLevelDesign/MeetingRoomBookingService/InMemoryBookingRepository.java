package LowLevelDesign.MeetingRoomBookingService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryBookingRepository implements BookingRepository {
    private final Map<String, List<Booking>> roomBookings = new HashMap<>();

    @Override
    public synchronized void save(Booking booking) {
        roomBookings.computeIfAbsent(booking.getRoomId(), k -> new ArrayList<>()).add(booking);
    }

    @Override
    public List<Booking> findByRoom(String roomId) {
        return roomBookings.getOrDefault(roomId, new ArrayList<>());
    }

    @Override
    public List<Booking> findByRoomAndDate(String roomId, LocalDate date) {
        return findByRoom(roomId).stream()
                .filter(b -> b.getStart().toLocalDate().equals(date))
                .toList();
    }

    @Override
    public List<Booking> findAll() {
        return roomBookings.values()
                .stream()
                .flatMap(List::stream)
                .toList();
    }
}
