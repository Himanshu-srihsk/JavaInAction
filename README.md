

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


7. **Factory Pattern with Annotations -> Factory Design Pattern and simmilar to AutoWired Constructor Injection in Spring_**
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
    ### To-Do:
    - Implement a custom thread pool manager using `ExecutorService`.


4. **MultithreadedCounter**
   - Write a program that uses multiple threads to increment a shared counter variable. Use synchronization techniques to ensure the counter’s integrity.
   - Extend the solution to make the counter thread-safe without using synchronized.
     [MultithreadedCounter](./JavaConcept/src/MultithreadingQuestions/MultithreadedCounter)


5. **CyclicOrderedPrintingWithMultipleThreads**
   - Multiple Threads are involved, each with its own start point.
   - Incremental Progress indicates that the value printed by each thread increases according to a user-defined increment.
   - Using IPC specifies that inter-thread communication mechanisms (like wait() and notify()) are employed for synchronization.

     [CyclicOrderedPrintingWithMultipleThreads](./JavaConcept/src/MultithreadingQuestions/CyclicOrderedPrintingWithMultipleThreads)   


---

# Low-Level Design

1. **Parking Lot System**
   ### To-Do:
    - Design a parking lot system with classes, relationships, and actions for managing parking and vehicle retrieval.


---
