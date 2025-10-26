

# Reflection & Annotations

1. **Annotation-based Field Validator**
    - Define a custom annotation `@NotNull` to mark fields that cannot be null.
    - Create a validator method that:
        - Scans for fields annotated with `@NotNull` and ensures they are non-null.
        - Prints a message if any `@NotNull` field is null.
    - Test on a class containing both nullable and non-nullable fields.
      [AnnotationbasedFieldValidator](./JavaConcept/src/Reflection_and_Annotations/AnnotationbasedFieldValidator)


2. **Multi Annotation-based Field Validator**
    - Define custom annotations `@NotNull` and `@MinLength`.
    - Write a validator method that:
        - Supports multiple annotations on fields.
        - Ensures fields with `@NotNull` are non-null and those with `@MinLength` meet the length criteria.
    - Test with a class containing fields with various constraints.
      [MultiAnnotationOnFieldValidator](./JavaConcept/src/Reflection_and_Annotations/MultiAnnotationOnFieldValidator)


3. **Simple Dependency Injection Framework**
    - Define an `@Inject` annotation for dependency injection.
    - Create a utility that:
        - Instantiates objects for fields annotated with `@Inject`.
        - Injects dependencies automatically, e.g., injecting an `Engine` into a `Car`.
      [SimpleDependencyInjection](./JavaConcept/src/Reflection_and_Annotations/SimpleDependencyInjection)


4. **Custom Serialization Utility with Annotations**
    - Define `@Serializable` and `@IgnoreField` annotations for custom serialization.
    - Write a utility that:
        - Serializes objects to JSON format, including only `@Serializable` fields.
        - Ignores fields marked with `@IgnoreField`.
    - Test with sample classes containing dependencies on other serializable classes.
      [CustomSerializationUtility](./JavaConcept/src/Reflection_and_Annotations/CustomSerializationUtility)


5. **Annotation-based Test Runner -> _Similar to Junit Library in Spring_**
    - Create a `@Test` annotation to mark test methods.
    - Write a test runner that:
        - Finds and runs methods annotated with `@Test`.
        - Reports the result (pass/fail) and logs exceptions.
    - Functions like a mini-JUnit framework.
      [AnnotationbasedTestRunner](./JavaConcept/src/Reflection_and_Annotations/AnnotationbasedTestRunner)


6. **Custom Access Control Using Annotations -> _Kind of Proxy Design Implementation_**
    - Define an annotation `@Role` with parameters like "ADMIN", "USER", or "GUEST".
    - Annotate methods with different role requirements.
    - Write an access control method to:
        - Check a user’s role against the required role for a method.
        - Grant or deny access accordingly, even in a multi-threaded environment.
          [CustomAccessControl](./JavaConcept/src/Reflection_and_Annotations/CustomAccessControl)


7. **Factory Pattern with Annotations -> _Factory Design Pattern and simmilar to AutoWired Constructor Injection in Spring_**
    - Define a `@Factory` annotation to indicate the constructor to use for object creation.
    - Create a factory utility that:
        - Finds the annotated constructor and instantiates the object.
    - Use for dynamic instantiation based on annotations.
      [FactoryPatternwithAnnotations](./JavaConcept/src/Reflection_and_Annotations/FactoryPatternwithAnnotations)


8. **Automatic Scope Manager -> _Similar to Scope in SpringBoot_**
    - Define an annotation `@Scope` for managing object lifecycles as Singleton or Prototype.
    - Write a utility that:
        - Ensures one instance per class for Singleton.
        - Creates new instances each time for Prototype.
          [AutomaticScopeManager](./JavaConcept/src/Reflection_and_Annotations/AutomaticScopeManager)


9. **Enum-based Configuration Loader**
    - Define a `@Config` annotation with a key parameter.
    - Create an `AppConfig` enum for configuration settings, annotated with `@Config`.
    - Write a loader that reads configurations from a properties file and populates the enum constants.
      [EnumbasedConfigurationLoader](./JavaConcept/src/Reflection_and_Annotations/EnumbasedConfigurationLoader)
   

10. **Annotation-Based SQL Query Builder -> _Similar to ORM framework like Hibernate/JPA Mapping in SpringBoot_** 
     - This project implements an **Annotation-Based SQL Query Builder** that uses annotations such as `@Table`, `@Column`, and `@Id` for a simple ORM-like setup. The query builder generates SQL CRUD (Create, Read, Update, Delete) statements by reading annotations from a given entity class.
       - **SQL Query Generation**: The builder reads annotations like `@Table`, `@Column`, `@Id`, `@OneToOne`, `@OneToMany`, and `@ManyToMany` to generate SQL CRUD statements dynamically.
       - **Supports Relationships**: It handles various relationships between entities, including:
          - `@OneToOne`
          - `@OneToMany`
          - `@ManyToMany`
          - `@Column` (Standard column mapping)
       - **Inner Classes**: The builder can process inner classes that contain these annotations as well.
       - **Spring Data JPA Demonstration**: This project demonstrates how annotations can simplify the mapping of database tables to Java classes, similar to how Spring Data JPA works.
         [AnnotationbasedSQLQueryBuilder](./JavaConcept/src/Reflection_and_Annotations/AnnotationbasedSQLQueryBuilder)
    ### To-Do:
    - **CreateQueryBuilder**: Not yet implemented.
    - **DeleteQueryBuilder**: Not yet implemented.
    - **UpdateQueryBuilder**: Not yet implemented.
    - **SelectQueryBuilder**: Completed and functional. Generates dynamic `SELECT` SQL queries based on entity annotations.
    - **selectQuerySimulator**: To be implemented to simulate and showcase the working of the `SELECT` query builder.

11. **Custom Annotation Processor for Immutable Classes**
    ### To-Do:
      - Define an annotation @Immutable and apply it to classes that should be immutable.
      - Write a processor that:
      - Inspects all fields of a class marked with @Immutable.
      - Checks if all fields are final and of immutable types (like String, Integer).
      - If a field is mutable or not final, print an error indicating the class violates immutability.


12. **Enum Reflection Utility**
    ### To-Do:
    - Write a utility that, given any enum class, can print all enum constants:
    - Invoke any method on the enum constants using reflection.


13. **Custom Logger Annotation**
    ### To-Do:
    - Define an annotation @LogExecutionTime that can be applied to methods:
    - Create an interceptor utility that.
      - Uses reflection to find methods annotated with @LogExecutionTime
      - When such a method is invoked, log its execution time.
    - Apply this to methods in a test class to see execution timing for each

14. **Question Bank Filter**
    - Define an annotation @Questions that can be applied to methods:
    - it contains attributes like topic and difficulty 
    - Filter the Questions dynamically at run time based on topic or difficulty level provided


---

# Multithreading

1. **Thread-safe Singleton Implementation**
    - Implement a thread-safe singleton using `BillPlugh` or `Double-checked Locking`.
    - The inclusion of tests for serialization and deserialization to ensure the Singleton behaves as expected when serialized and deserialized 
      
      [ThreadSafeSingletonPattern](./JavaConcept/src/MultithreadingQuestions/ThreadSafeSingletonPattern)


2. **Producer-Consumer Problem**
    - Implement a producer-consumer model where producers add items to a shared bounded buffer and consumers remove items.
    - Implement this with multiple producer and consumer threads and use wait() and notifyAll() for inter-thread communication.
      
     [ProducerConsumerProblem](./JavaConcept/src/MultithreadingQuestions/ProducerConsumerProblem)


3. **Thread Pool Implementation**
     ### In Progress:
    - Implement a custom thread pool manager simmilar to `ThreadPoolExecutor`.
     
   [ThreadPoolImplementation](./JavaConcept/src/MultithreadingQuestions/ThreadPoolImplementation)


4. **MultithreadedCounter**
   - Write a program that uses multiple threads to increment a shared counter variable. Use synchronization techniques to ensure the counter’s integrity.
   - Extend the solution to make the counter thread-safe without using synchronized.
     
    [MultithreadedCounter](./JavaConcept/src/MultithreadingQuestions/MultithreadedCounter)


5. **CyclicOrderedPrintingWithMultipleThreads**
   - Multiple Threads are involved, each with its own start point.
   - Incremental Progress indicates that the value printed by each thread increases according to a user-defined increment.
   - Using IPC specifies that inter-thread communication mechanisms (like wait() and notify()) are employed for synchronization.

     [CyclicOrderedPrintingWithMultipleThreads](./JavaConcept/src/MultithreadingQuestions/CyclicOrderedPrintingWithMultipleThreads)   


6. **CustomBlockingQueueImpl**
   - Custom Blocking Queue with Condition Variables
   - Create a BlockingQueue implementation using Condition objects from ReentrantLock, ReadWriteLock, StampLock.
   - use Strategy Design pattern to implement run time algorithm fo locking and synchronization
   - Demonstrate producers adding items and consumers taking items.

     [CustomBlockingQueueImpl](./JavaConcept/src/MultithreadingQuestions/CustomBlockingQueueImpl)


7. **DeadlockSimulationandResolution**
   - Write a program that intentionally creates a deadlock situation between two threads.
   - After observing the deadlock, modify the code to resolve it using one or more techniques (like ordering resources, timeout, or tryLock()).

     [DeadlockSimulationandResolution](./JavaConcept/src/MultithreadingQuestions/DeadlockSimulationandResolution)

8. **Synchronous Task Pipeline Using CompletetableFuture**
   - uses CompletableFuture for chaining the tasks across stages, where the fetch, process, and save stages each run in separate threads
   - Each stage start once that stage is fully completed i.e process start once fetch is over and save Stage start onec process is over. after processs Stage it moves to final stage
   - Fetch Stage: Reads data from a file asynchronously and passes it to the process stage.
   - Process Stage: Processes the fetched data (e.g., transforms it to uppercase) and sends it to the save stage.
   - Save Stage: Writes the processed data to an output file.

     [SynchronousTaskPipelineCompletetableFuture](./JavaConcept/src/MultithreadingQuestions/SynchronousTaskPipelineCompletetableFuture)


9. **Asynchronous Task Pipeline Similar to Producer Consumer ..**
   ### To-Do:
    - uses CompletableFuture for chaining the tasks across stages, where the fetch, process, and save stages each run in separate threads
    - Fetch Data: Asynchronously fetches data from the file and puts it into the BlockingQueue. It doesn’t wait for the fetch to complete before moving to the next stage. 
    - Process Data: Asynchronously processes data from the BlockingQueue and immediately passes it to the BlockingQueue of the save stage. 
    - Save Data: Asynchronously consumes processed data from the BlockingQueue and saves it to the file.   
   

10. **MultithreadedFileReader**   
    - Write a program to read a large file in parallel using multiple threads. 
    - Divide the file into parts, each read by a separate thread, and then combine the results in order. 
    - Ensure thread safety if threads need to update a shared data structure.
      
     [MultithreadedFileReader](./JavaConcept/src/MultithreadingQuestions/MultithreadedFileReader)

11. **ParallelFileSearch**
    - Write a program to search for a specific keyword within multiple files in parallel.
    - Each thread should search one file, and the program should return a list of files where the keyword was found.
    - Use Future and Callable to handle tasks and collect results.
    
      [ParallelFileSearch](./JavaConcept/src/MultithreadingQuestions/ParallelFileSearch)

12. **PriorityTaskScheduler**
    ### To-Do:

13. **ThreadSafeCachewithExpiryMechanism**
    - Thread-safe Cache with Expiry Mechanism 
    - Design a thread-safe cache with the following properties:
    - Each item in the cache has a time-to-live (TTL) value. 
    - Expired items should be removed automatically without blocking the main operation. 
    - Multiple threads should be able to read and write to the cache simultaneously. 
    - Implement this using ConcurrentHashMap and ScheduledExecutorService for expiration handling.
      
      [ThreadSafeCachewithExpiryMechanism](./JavaConcept/src/MultithreadingQuestions/ThreadSafeCachewithExpiryMechanism)

15. **Parallel Data Aggregation:**
    - Calculate aggregate metrics (e.g., sum, average) on large datasets using parallel streams.
    - Experiment with different stream operations to optimize performance.
    - Measure and compare performance differences between parallel and sequential streams.
    
      [ParallelDataAggregationUsingStreams](./JavaConcept/src/MultithreadingQuestions/ParallelDataAggregationUsingStreams)

    **Examples2:**
    - Calculate the total salary of all employees.
    - Calculate the average salary in each department.
    - Find the oldest employee in each department.
    - Find the highest-paid employee in the organization.
         
      [ParallelDataAggregationUsingStream2](./JavaConcept/src/MultithreadingQuestions/ParallelDataAggregationUsingStreams)

16. **ForkJoinPoolQuestions**
    **Example 1: Recursive Merge Sort**
      - Implements the merge sort algorithm using ForkJoinPool.
      - Divides the array into smaller segments, sorts them recursively, and merges the results.
      - Demonstrates the efficiency of parallel processing for sorting large datasets
        
       [RecursiveArraySorting](./JavaConcept/src/MultithreadingQuestions/ForkJoinPoolQuestions/RecursiveArraySorting)
    
    **Example 2: Recursive Array Sum**
      - Calculates the sum of array elements using ForkJoinPool.
      - Splits the array into smaller segments, computes the sum recursively, and combines the results.
      - Highlights the benefits of parallel computation for aggregating data.
      
       [RecursiveSumOfArray](./JavaConcept/src/MultithreadingQuestions/ForkJoinPoolQuestions/RecursiveSumOfArray)

17. **CyclicBarrierWithCustomActions**
    **Example 1: Cyclic Barrier example**
       - Implement a Cyclic Barrier with Custom Actions 
       - Add a feature where, once the barrier is reached, a specified action (ex: printing a message) is performed.
        
       [CyclicBarrierEx1](./JavaConcept/src/MultithreadingQuestions/CyclicBarrierWithCustomActions/CyclicBarrierEx1)
    
    **Example 1: Custom Cyclic Barrier Implementaion**
       - Implement a Cyclic Barrier with Custom Actions 
       - Build a custom version of CyclicBarrier that allows threads to wait at a barrier until a certain number of threads reach it. 
       - Add a feature where, once the barrier is reached, a specified action (ex: printing a message or resetting some variables) is performed.
       
       [CustomCyclicBarrierEx2](./JavaConcept/src/MultithreadingQuestions/CyclicBarrierWithCustomActions/CustomCyclicBarrierEx2)
       
    **Example 3: MapReduceWithCyclicBarrier**
      - Suppose you are processing a large file in multiple chunks using 4 threads. 
      - each thread finishes processing its chunk, Performs Map phase -> build a local Map<String, Integer> word To Count while Ignores stop words
      - all threads must synchronize to combine the results or proceed to the next stage together i.e. to perform reduce Phase. A CyclicBarrier ensures all threads reach this merge point before moving on.
      - The barrier’s action combines all partial maps → performs Reduce phase and output map sorted by count in desc
        
      [MapReduceWithCyclicBarrier](./JavaConcept/src/MultithreadingQuestions/CyclicBarrierWithCustomActions/MapReduceWithCyclicBarrier)

18. **BuildFaultTolerantMessageQueue**
    ### To-Do:
      - Design a simple message queue that supports basic operations like enqueue, dequeue, and ack. 
      - Use multiple threads to simulate producers and consumers. 
      - Add fault tolerance so that if a message is dequeued but not acknowledged, it is re-queued for another attempt.

19. **Simulate a Multithreaded Stock Price Monitoring System**
    ### To-Do:

20. **Simulate a Bank Transaction System**
    ### To-Do:
     - Implement a bank transaction system where multiple threads represent users who transfer money between accounts.
     - Ensure thread safety to prevent issues such as inconsistent balances or lost updates.
     - Add a daily report feature that calculates the total money in all accounts without affecting transactions
21. **Implement a Multi-threaded Web Crawler**
    ### To-Do:
    - Design a simple web crawler that accepts a URL and crawls all pages linked from the initial URL up to a specified depth.
    - Use a thread-safe data structure to keep track of visited URLs and avoid duplicate work.
    - Use multiple threads to speed up the crawling process and implement a throttling mechanism to avoid overloading the server.

22.  **Implement a Rate Limiter**
     ### To-Do:
      - Design a rate limiter class that limits the number of requests a user can make within a certain time window (e.g., 10 requests per second).
      - The class should allow concurrent requests from multiple threads and block requests that exceed the allowed limit.
      - Use multithreading and synchronization to ensure thread safety.

23. **Semaphore based Downloadmanager**
    **Example 1: Downloadmanager**
    - Build a file downloader application where only 5 downloads can happen in parallel to avoid server overload. 
    - New download requests should wait using Semaphore. 
    - Also prioritize certain downloads (VIP,HIGH,MEDIUM,LOW) based on Task Priority

    [DownloadManager](./JavaConcept/src/MultithreadingQuestions/SemaphoreProblem/DownloadManager)


24. **CountDown latch Example**
     ### To-Do:

---

# Low-Level Design

1. **Parking Lot System**
   ### To-Do:
    - Design a parking lot system with classes, relationships, and actions for managing parking and vehicle retrieval.


2. **Task Pipeline**
   - Task Pipeline with State Design Pattern for File Data Fetching, Processing, and Saving (Synchronous).
   - State Design Pattern: Ensures flexible transition between the stages of data handling.
   - Synchronous Operation: The stages execute one after another without concurrency or parallel processing.
   -  Data Flow: Data is passed between stages using a BlockingQueue to ensure correct sequencing and avoid data loss.
   -  File Processing: Data is fetched from a file, processed (e.g., converted to uppercase), and then saved to another file.
      [TaskPipeline](./JavaConcept/src/LowLevelDesign/TaskPipeline)
   
3. **Json Parser**
   ### To-Do:


4. **Custom Generic HashMap Implementation**
    - HashMap with Collision Handling
    - Generic Hashmap with put, get and remove methods
      [HashmapImpl](./JavaConcept/src/LowLevelDesign/HashmapImpl)

5. **LRU Cache**
    - Easily adaptable cache design supporting custom eviction policies and storage backend
      [CacheDesignImpl](./JavaConcept/src/LowLevelDesign/CacheDesignImpl)

6. **Splitwise app**
   ### To-Do:

7. **Vending Machine**
   ### To-Do:

8. **TV**
   - TV State Management System using State Design Pattern
     [TV](./JavaConcept/src/LowLevelDesign/TV)

9. **Book My show**
   ### To-Do:

10. **Elevator**
    ### In Progress:
    -  Multi-Elevator System: An Extensible, Strategy-Driven Elevator Simulation with Priority Queuing
       [Elevator](./JavaConcept/src/LowLevelDesign/Elevator)

11. **Cricbuzz live score**
    ### To-Do:

12. **Calculator Service**
    - Advanced Calculator Service with Support for Basic and Scientific Operations
    - Calculator service evaluates mathematical expressions with support for basic operations (+, -, *, /) 
    - trigonometric functions (e.g., sin, cos), and parentheses.
    - Used Command and Interpreter design patterns for flexibility and extensibility
      [CalculatorService](./JavaConcept/src/LowLevelDesign/CalculatorService)

13. **Snake and Ladder**
    ### To-Do:

14. **Tic Tace Toe**
    ### To-Do:

15. **Design Logging System**
    ### To-Do:

16. **File and Directory System**
    ### To-Do:

17. **Meeting Room Booking Service**
    ### To-Do:

18. **Locker Management System**
    ### To-Do:

19. **Trello Application**
    ### To-Do:

20. **Design Twitter LLD**
    ### To-Do:

21. **Design Online Code Platform**
    ### To-Do:

22. **Design Consistent Hashing**
    ### To-Do:

23. **Design Google Authenticator**
    ### To-Do:

24. **Design Music Stream service**
    ### To-Do:

25. **Quad Tree Design implemetation**
    ### To-Do:

26. **Design Torrent**
    ### To-Do:

27. **Design Online Code Platform**
    ### To-Do:

28. **Design Traffic Signal System**
    ### To-Do:

29. **Ecommerce Filter and Pricing Logic**
    - Design an extensible filtering system for an e-commerce platform that allows dynamic filtering of products 
    - based on attributes like category, brand, and price using the **Interpreter design pattern.** 
    - Additionally, calculate the total price of the filtered products using the **Visitor pattern.** 
    - The system should support flexible combinations of filter conditions (AND, OR) and be open for future enhancements like discount logic,
      new filters, or operations.

---
# Java8 Stream

1. Java Stream part 1
   [JavaStream](./JavaConcept/src/Stream)

2. Java Stream part 2
   [JavaStream2](./JavaConcept/src/Stream/JavaStream2)