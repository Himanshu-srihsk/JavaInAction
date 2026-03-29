package LowLevelDesign.MeetingRoomBookingService;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class LockManager {
    private final ConcurrentHashMap<String, ReentrantLock> locks = new ConcurrentHashMap<>();

    public ReentrantLock getLock(String key) {
        locks.putIfAbsent(key, new ReentrantLock());
        return locks.get(key);
    }
}
