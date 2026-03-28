package LowLevelDesign.LoggingSystem;

public class DebugLogger extends AbstractLogger {

    DebugLogger(int levels) {
        this.levels = levels;
    }

    @Override
    protected void display(String msg, LoggerSubject loggerSubject) {

        loggerSubject.notifyAllObserver(3,"DEBUG : "+msg);
    }
}
