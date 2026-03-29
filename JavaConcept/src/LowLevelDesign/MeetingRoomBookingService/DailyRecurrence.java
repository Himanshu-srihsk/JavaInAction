package LowLevelDesign.MeetingRoomBookingService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DailyRecurrence implements RecurrenceStrategy {

    private final int interval;
    private final LocalDateTime endDate;

    public DailyRecurrence(int interval, LocalDateTime endDate) {
        this.interval = interval;
        this.endDate = endDate;
    }

    @Override
    public List<LocalDateTime> generateOccurrences(LocalDateTime start) {
        List<LocalDateTime> result = new ArrayList<>();
        LocalDateTime current = start;

        while (!current.isAfter(endDate)) {
            result.add(current);
            current = current.plusDays(interval);
        }
        return result;
    }
}
