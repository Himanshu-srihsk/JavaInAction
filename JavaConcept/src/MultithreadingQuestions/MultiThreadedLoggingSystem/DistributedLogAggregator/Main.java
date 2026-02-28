package MultithreadingQuestions.MultiThreadedLoggingSystem.DistributedLogAggregator;

/*
Design a Distributed Logging Client that:
Buffers logs locally
Sends logs in batches to remote logging server
Retries on failure
Uses circuit breaker if remote server is down
Avoids data loss (configurable durability)

Functional Requirements
Application threads log locally.
Logs are stored in memory buffer.
Background thread sends logs in batches to remote server.
If send fails:
Retry 3 times with exponential backoff.
If remote service keeps failing:
Open circuit breaker
Stop sending temporarily
When circuit closes:
Resume sending
Optional:
Spill logs to disk if memory full
 */
public class Main {
    public static void main(String[] args) {

    }
}
