package MultithreadingQuestions.ParallelFileSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/*
Write a program to search for a specific keyword within multiple files in parallel.
Use a fixed thread pool to limit the number of threads.
Each thread should search one file, and the program should return a list of files where the keyword was found.
Use Future and Callable to handle tasks and collect results.
 */
public class ParallelFileSearch {
    public static void main(String[] args) {
       int numThreads = 3;
       String keyword = "Himanshu";
       String prefix = "C:\\Users\\Himanshu\\Desktop\\work\\Java\\Java-2\\JavaConcept\\src\\MultithreadingQuestions\\ParallelFileSearch\\";
       List<String> fileNames = Arrays.asList("File1.txt", "File2.txt","File3.txt").stream().map(r -> prefix+r).collect(Collectors.toList());
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        List<Future<String>> futures = new ArrayList<>();
        try {
            fileNames.forEach(file ->{
                FileSerachTask task = new FileSerachTask(file, keyword, false);
                futures.add(executorService.submit(task));

            });

            System.out.println("Keyword "+ keyword +" found in these files "+ futures.stream().map(e -> {
                try {
                    return e.get();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                } catch (ExecutionException ex) {
                    throw new RuntimeException(ex);
                }
            })
                            .filter(e -> e!=null)
                    .collect(Collectors.toList()));
        }catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            executorService.shutdown();
        }
    }
}

/*

Started Reading C:\Users\Himanshu\Desktop\work\Java\Java-2\JavaConcept\src\MultithreadingQuestions\ParallelFileSearch\File2.txt Reader name is pool-1-thread-2 Search query is is himanshu
Started Reading C:\Users\Himanshu\Desktop\work\Java\Java-2\JavaConcept\src\MultithreadingQuestions\ParallelFileSearch\File1.txt Reader name is pool-1-thread-1 Search query is is himanshu
Started Reading C:\Users\Himanshu\Desktop\work\Java\Java-2\JavaConcept\src\MultithreadingQuestions\ParallelFileSearch\File3.txt Reader name is pool-1-thread-3 Search query is is himanshu
Found: himanshu in file: C:\Users\Himanshu\Desktop\work\Java\Java-2\JavaConcept\src\MultithreadingQuestions\ParallelFileSearch\File2.txt
completed Reading C:\Users\Himanshu\Desktop\work\Java\Java-2\JavaConcept\src\MultithreadingQuestions\ParallelFileSearch\File2.txt Reader name is pool-1-thread-2 Search query is is himanshu
completed Reading C:\Users\Himanshu\Desktop\work\Java\Java-2\JavaConcept\src\MultithreadingQuestions\ParallelFileSearch\File3.txt Reader name is pool-1-thread-3 Search query is is himanshu
Found: himanshu in file: C:\Users\Himanshu\Desktop\work\Java\Java-2\JavaConcept\src\MultithreadingQuestions\ParallelFileSearch\File1.txt
completed Reading C:\Users\Himanshu\Desktop\work\Java\Java-2\JavaConcept\src\MultithreadingQuestions\ParallelFileSearch\File1.txt Reader name is pool-1-thread-1 Search query is is himanshu
Keyword Himanshu found in these files [C:\Users\Himanshu\Desktop\work\Java\Java-2\JavaConcept\src\MultithreadingQuestions\ParallelFileSearch\File1.txt, C:\Users\Himanshu\Desktop\work\Java\Java-2\JavaConcept\src\MultithreadingQuestions\ParallelFileSearch\File2.txt]
 */