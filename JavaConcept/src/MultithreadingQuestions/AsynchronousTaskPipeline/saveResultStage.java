package MultithreadingQuestions.AsynchronousTaskPipeline;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.concurrent.CompletableFuture;

public class saveResultStage implements Stage {
    @Override
    public void processData(TaskPipelineContext context)  throws Exception {
        throw new Exception("Not allowed operation at this stage");
    }

    @Override
    public void fetchData(TaskPipelineContext context) throws Exception {
        throw new Exception("Not allowed operation at this stage");
    }

    @Override
    public void saveResult(TaskPipelineContext context)  throws Exception{

        CompletableFuture<Void> future = CompletableFuture.runAsync(() ->{
            try {
                try(BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Himanshu\\Desktop\\work\\Java\\Java-2\\JavaConcept\\src\\MultithreadingQuestions\\AsynchronousTaskPipeline\\output.txt"))) {
                    String data;
                    while(!(data = context.getProcessToSaveQueue().take()).equals("END")){
                        writer.write(data);
                        writer.newLine();
                        System.out.println("Saved: " + data);
                        Thread.sleep(100);
                    }
                }

            }catch (Exception e){
               e.printStackTrace();
            }
        });

        future.join();
        System.out.println("Save result completed, transitioning to Final stage.");
        context.setStage(new FinalStage());

    }
    @Override
    public String toString() {
        return "save Result stage";
    }
}
