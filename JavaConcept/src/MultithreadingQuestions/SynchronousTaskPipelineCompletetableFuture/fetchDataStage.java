package MultithreadingQuestions.SynchronousTaskPipelineCompletetableFuture;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class fetchDataStage implements Stage {
    @Override
    public void processData(TaskPipelineContext context)  throws Exception {
        throw new Exception("Not allowed operation at this stage");
    }

    @Override
    public void fetchData(TaskPipelineContext context) throws Exception {
        System.out.println("Starting Fetch Stage");

        // Asynchronously fetching data from the file
        CompletableFuture<?> future = CompletableFuture.runAsync(() -> {
            try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Himanshu\\Desktop\\work\\Java\\Java-2\\JavaConcept\\src\\MultithreadingQuestions\\SynchronousTaskPipelineCompletetableFuture\\input.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println("Data fetched: " + line);
                    context.getFetchToProcessQueue().add(line);
                    Thread.sleep(100);
                }
                context.getFetchToProcessQueue().add("END");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        });

        future.thenRunAsync(() -> {
            System.out.println("Fetch stage completed, transitioning to Process stage.");
            context.setStage(new ProcessDataStage());
            try {
                context.getStage().processData(context);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).join(); // Wait for the fetch data task to finish
    }
    @Override
    public void saveResult(TaskPipelineContext context)  throws Exception{
        throw new Exception("Not allowed operation at this stage");
    }
    @Override
    public String toString() {
        return "fetch Data stage";
    }
}
