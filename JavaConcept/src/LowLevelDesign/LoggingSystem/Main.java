package LowLevelDesign.LoggingSystem;

/*
uses singleton + chain of Responsibility + Observer Pattern

Design a logging system with the following requirements:

The system should support multiple log levels: INFO ,DEBUG, ERROR

Logs should be routed based on log level.
The system should support multiple output channels such as: Console, File ,(Extensible for future like DB, Kafka, etc.)

Different output channels may subscribe to different log levels.
The logger should be implemented as a Singleton.
The design should follow SOLID principles and be extensible
 */
public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger();
        logger.info("this is an info");
        logger.debug("this is a debug");
        logger.error("this is an error");
    }
}
/*
CONSOLE MESSAGE : INFO : this is an info
Writing to File INFO : this is an info
CONSOLE MESSAGE : DEBUG : this is a debug
CONSOLE MESSAGE : ERROR : this is an error
 */