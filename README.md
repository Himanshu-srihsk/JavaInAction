

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
4. **Custom Generic HashMap Implementation**
    - HashMap with Collision Handling
    - Generic Hashmap with put, get and remove methods
      [HashmapImpl](./JavaConcept/src/LowLevelDesign/HashmapImpl)
5. **LRU Cache**
    - Easily adaptable cache design supporting custom eviction policies and storage backend
      [CacheDesignImpl](./JavaConcept/src/LowLevelDesign/CacheDesignImpl)
6. **Splitwise app**
7. **Vending Machine**
8. **TV**
   - TV State Management System using State Design Pattern
     [TV](./JavaConcept/src/LowLevelDesign/TV)
9. **Book My show**
10. **Elevator**
    ### In Progress:
    -  Multi-Elevator System: An Extensible, Strategy-Driven Elevator Simulation with Priority Queuing
       [Elevator](./JavaConcept/src/LowLevelDesign/Elevator)
11. **Cricbuzz live score**
12. **Calculator Service**
    - Advanced Calculator Service with Support for Basic and Scientific Operations
    - Calculator service evaluates mathematical expressions with support for basic operations (+, -, *, /) 
    - trigonometric functions (e.g., sin, cos), and parentheses.
    - Used Command and Interpreter design patterns for flexibility and extensibility
      [CalculatorService](./JavaConcept/src/LowLevelDesign/CalculatorService)


---
