package LowLevelDesign.MeetingRoomBookingService;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;

public class BookingService {

    private final BookingRepository bookingRepo;
    private final RoomRepository roomRepo;
    private final AvailabilityService availabilityService;
    private final LockManager lockManager;

    public BookingService(BookingRepository bookingRepo, RoomRepository roomRepo, AvailabilityService availabilityService, LockManager lockManager) {
        this.bookingRepo = bookingRepo;
        this.roomRepo = roomRepo;
        this.availabilityService = availabilityService;
        this.lockManager = lockManager;
    }
    public boolean book(String roomId, String userId, LocalDateTime start, LocalDateTime end) {
        ReentrantLock lock = lockManager.getLock(roomId);
        lock.lock();
        try {
            if (!availabilityService.isAvailable(roomId, start, end)) {
                return false;
            }
            bookingRepo.save(new Booking(UUID.randomUUID().toString(), roomId, userId, start, end, null));
            return true;
        } finally {
            lock.unlock();
        }
    }
    public List<Boolean> bookRecurring(String roomId, String userId, LocalDateTime start, Duration duration, RecurrenceStrategy strategy) {
        String recurrenceId = UUID.randomUUID().toString();
        List<Boolean> results = new ArrayList<>();
        List<LocalDateTime> occurrences = strategy.generateOccurrences(start);

        for (LocalDateTime occ : occurrences) {
            LocalDateTime end = occ.plus(duration);
            boolean success = bookWithRecurrenceId(roomId, userId, occ, end, recurrenceId);
            results.add(success);
        }

        return results;
    }

    private boolean bookWithRecurrenceId(
            String roomId, String userId,
            LocalDateTime start, LocalDateTime end,
            String recurrenceId) {

        ReentrantLock lock = lockManager.getLock(roomId);
        lock.lock();

        try {
            if (!availabilityService.isAvailable(roomId, start, end)) {
                return false;
            }

            bookingRepo.save(new Booking(
                    UUID.randomUUID().toString(),
                    roomId,
                    userId,
                    start,
                    end,
                    recurrenceId
            ));

            return true;

        } finally {
            lock.unlock();
        }
    }
    public List<Room> searchAvailableRooms(
            LocalDateTime start,
            LocalDateTime end,
            int minCapacity) {

        List<Room> result = new ArrayList<>();

        for (Room room : roomRepo.findAll()) {
            if (room.getCapacity() >= minCapacity &&
                    availabilityService.isAvailable(room.getId(), start, end)) {
                result.add(room);
            }
        }
        return result;
    }
    public List<Booking> getBookings(
            String roomId, LocalDate date) {

        return bookingRepo.findByRoomAndDate(roomId, date);
    }
    public List<Room> searchRooms(RoomFilter filter) {

        List<Room> result = new ArrayList<>();
        for (Room room : roomRepo.findAll()) {
            if (filter.test(room)) {
                result.add(room);
            }
        }
        return result;
    }
}
