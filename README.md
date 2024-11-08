# Reflection & Annotations

1. **Annotation-based Field Validator**
    - Define a custom annotation `@NotNull` to mark fields that cannot be null.
    - Create a validator method that:
        - Scans for fields annotated with `@NotNull` and ensures they are non-null.
        - Prints a message if any `@NotNull` field is null.
    - Test on a class containing both nullable and non-nullable fields.


2. **Multi Annotation-based Field Validator**
    - Define custom annotations `@NotNull` and `@MinLength`.
    - Write a validator method that:
        - Supports multiple annotations on fields.
        - Ensures fields with `@NotNull` are non-null and those with `@MinLength` meet the length criteria.
    - Test with a class containing fields with various constraints.


3. **Simple Dependency Injection Framework**
    - Define an `@Inject` annotation for dependency injection.
    - Create a utility that:
        - Instantiates objects for fields annotated with `@Inject`.
        - Injects dependencies automatically, e.g., injecting an `Engine` into a `Car`.


4. **Custom Serialization Utility with Annotations**
    - Define `@Serializable` and `@IgnoreField` annotations for custom serialization.
    - Write a utility that:
        - Serializes objects to JSON format, including only `@Serializable` fields.
        - Ignores fields marked with `@IgnoreField`.
    - Test with sample classes containing dependencies on other serializable classes.


5. **Annotation-based Test Runner**
    - Create a `@Test` annotation to mark test methods.
    - Write a test runner that:
        - Finds and runs methods annotated with `@Test`.
        - Reports the result (pass/fail) and logs exceptions.
    - Functions like a mini-JUnit framework.


6. **Custom Access Control Using Annotations**
    - Define an annotation `@Role` with parameters like "ADMIN", "USER", or "GUEST".
    - Annotate methods with different role requirements.
    - Write an access control method to:
        - Check a user’s role against the required role for a method.
        - Grant or deny access accordingly, even in a multi-threaded environment.


7. **Factory Pattern with Annotations**
    - Define a `@Factory` annotation to indicate the constructor to use for object creation.
    - Create a factory utility that:
        - Finds the annotated constructor and instantiates the object.
    - Use for dynamic instantiation based on annotations.


8. **Automatic Scope Manager**
    - Define an annotation `@Scope` for managing object lifecycles as Singleton or Prototype.
    - Write a utility that:
        - Ensures one instance per class for Singleton.
        - Creates new instances each time for Prototype.


9. **Enum-based Configuration Loader**
    - Define a `@Config` annotation with a key parameter.
    - Create an `AppConfig` enum for configuration settings, annotated with `@Config`.
    - Write a loader that reads configurations from a properties file and populates the enum constants.

10. **Annotation-Based SQL Query Builder** 
     - This project implements an **Annotation-Based SQL Query Builder** that uses annotations such as `@Table`, `@Column`, and `@Id` for a simple ORM-like setup. The query builder generates SQL CRUD (Create, Read, Update, Delete) statements by reading annotations from a given entity class.
       - **SQL Query Generation**: The builder reads annotations like `@Table`, `@Column`, `@Id`, `@OneToOne`, `@OneToMany`, and `@ManyToMany` to generate SQL CRUD statements dynamically.
       - **Supports Relationships**: It handles various relationships between entities, including:
          - `@OneToOne`
          - `@OneToMany`
          - `@ManyToMany`
          - `@Column` (Standard column mapping)
       - **Inner Classes**: The builder can process inner classes that contain these annotations as well.
       - **Spring Data JPA Demonstration**: This project demonstrates how annotations can simplify the mapping of database tables to Java classes, similar to how Spring Data JPA works.
    ### To-Do:
    - **CreateQueryBuilder**: Not yet implemented.
    - **DeleteQueryBuilder**: Not yet implemented.
    - **UpdateQueryBuilder**: Not yet implemented.
    - **SelectQueryBuilder**: Completed and functional. Generates dynamic `SELECT` SQL queries based on entity annotations.
    - **selectQuerySimulator**: To be implemented to simulate and showcase the working of the `SELECT` query builder.



---

# Multithreading

1. **Thread-safe Singleton Implementation**
    ### To-Do:
    - Implement a thread-safe singleton using `synchronized` or Double-checked Locking.


2. **Producer-Consumer Problem**
   ### To-Do:
    - Solve the Producer-Consumer problem using `BlockingQueue` or `wait`/`notify`.


3. **Thread Pool Implementation**
    ### To-Do:
    - Implement a custom thread pool manager using `ExecutorService`.


---

# Low-Level Design

1. **Parking Lot System**
   ### To-Do:
    - Design a parking lot system with classes, relationships, and actions for managing parking and vehicle retrieval.


---
