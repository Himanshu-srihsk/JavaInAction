package LowLevelDesign.MeetingRoomBookingService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WeeklyRecurrence implements RecurrenceStrategy {

    private final int interval;
    private final LocalDateTime endDate;

    public WeeklyRecurrence(int interval, LocalDateTime endDate) {
        this.interval = interval;
        this.endDate = endDate;
    }

    @Override
    public List<LocalDateTime> generateOccurrences(LocalDateTime start) {
        List<LocalDateTime> result = new ArrayList<>();
        LocalDateTime current = start;

        while (!current.isAfter(endDate)) {
            result.add(current);
            current = current.plusWeeks(interval);
        }
        return result;
    }
}
