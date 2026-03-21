package MultithreadingQuestions.TaskScheduler.TaskSchedulerSystem;

public class StrategyFactory {
    public static SchedulingStrategy getStrategy(ScheduleType type) {
        return switch (type) {
            case ONE_TIME -> new OneTimeStrategy();
            case FIXED_RATE -> new FixedRateStrategy();
            case FIXED_DELAY -> new FixedDelayStrategy();
        };
    }
}
