package LowLevelDesign.MeetingRoomBookingService;

import java.time.LocalDateTime;

public class Booking {
    private final String id;
    private final String roomId;
    private final String userId;
    private final LocalDateTime start;
    private final LocalDateTime end;
    private final String recurrenceId; // null for normal booking

    private BookingStatus status;

    public Booking(String id, String roomId, String userId, LocalDateTime start, LocalDateTime end, String recurrenceId) {
        this.id = id;
        this.roomId = roomId;
        this.userId = userId;
        this.start = start;
        this.end = end;
        this.recurrenceId = recurrenceId;
        this.status = BookingStatus.BOOKED;
    }

    public boolean isOverlapping(LocalDateTime s, LocalDateTime e) {
        return !(end.isBefore(s) || start.isAfter(e));
    }

    public String getRoomId() {
        return roomId;
    }
    public LocalDateTime getStart() {
        return start;
    }
    public LocalDateTime getEnd() {
        return end;
    }
    public BookingStatus getStatus() {
        return status;
    }

    public void cancel() {
        this.status = BookingStatus.CANCELLED;
    }
}