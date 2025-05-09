package MultithreadingQuestions.MultithreadedFileReader;

import java.io.*;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/*
Multi threaded File Reader
Write a program to read a large file in parallel using multiple threads.
Divide the file into parts, each read by a separate thread, and then combine the results in order.
Ensure thread safety if threads need to update a shared data structure.
 */
public class MultithreadedFileReader {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
       String filepath = "C:\\Users\\Himanshu\\Desktop\\work\\Java\\Java-2\\JavaConcept\\src\\MultithreadingQuestions\\MultithreadedFileReader\\input.txt";
       int numThreads = 3;

        File file = new File(filepath);

        int division = (int)file.length() / numThreads;
        ExecutorService poolThreads = Executors.newFixedThreadPool(numThreads);
        BlockingQueue<Future<Content>> contentQueue = new LinkedBlockingQueue<>();

        try{
            for(int i = 0;i<numThreads;i++){
                int start = i* division;
                int end = (i == numThreads - 1)? (int) file.length() - 1 : (i + 1) * division - 1;
                PartReader reader = new PartReader(filepath,start,end,i+1);
                contentQueue.add(poolThreads.submit(reader));
            }
          List<Content> sortedList = contentQueue.stream().map(contentFuture -> {
              try {
                  return contentFuture.get();
              } catch (InterruptedException e) {
                  throw new RuntimeException(e);
              } catch (ExecutionException e) {
                  throw new RuntimeException(e);
              }
          }).sorted().collect(Collectors.toUnmodifiableList());

            String combinedContent = sortedList.stream()
                    .map(Content::getContent)
                    .collect(Collectors.joining());

            System.out.println("Combined Data:\n" + combinedContent);
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            poolThreads.shutdown();
        }

    }
}

/*
Started Reading C:\Users\Himanshu\Desktop\work\Java\Java-2\JavaConcept\src\MultithreadingQuestions\MultithreadedFileReader\input.txt Reader name is pool-1-thread-3 Start point is 7776 End point is 11664
Started Reading C:\Users\Himanshu\Desktop\work\Java\Java-2\JavaConcept\src\MultithreadingQuestions\MultithreadedFileReader\input.txt Reader name is pool-1-thread-1 Start point is 0 End point is 3887
Started Reading C:\Users\Himanshu\Desktop\work\Java\Java-2\JavaConcept\src\MultithreadingQuestions\MultithreadedFileReader\input.txt Reader name is pool-1-thread-2 Start point is 3888 End point is 7775
Completed Reading C:\Users\Himanshu\Desktop\work\Java\Java-2\JavaConcept\src\MultithreadingQuestions\MultithreadedFileReader\input.txt Reader name is pool-1-thread-1 Start point is 0 End point is 3887
Completed Reading C:\Users\Himanshu\Desktop\work\Java\Java-2\JavaConcept\src\MultithreadingQuestions\MultithreadedFileReader\input.txt Reader name is pool-1-thread-2 Start point is 3888 End point is 7775
Completed Reading C:\Users\Himanshu\Desktop\work\Java\Java-2\JavaConcept\src\MultithreadingQuestions\MultithreadedFileReader\input.txt Reader name is pool-1-thread-3 Start point is 7776 End point is 11664
Combined Data:
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture

Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchroous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture

Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Dsign Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Hello World
Asynchronous Pipeline
State Design Pattern
Multithreading in Java
File Processing Example
Concurrent Programming
Task Pipeline with BlockingQueue
CompletableFuture
Himanshu ............ I am practicing LLD and Multithreading concept
 */