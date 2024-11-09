package MultithreadingQuestions.SynchronousTaskPipelineCompletetableFuture;

import java.util.concurrent.CompletableFuture;

public class ProcessDataStage implements Stage {
    @Override
    public void processData(TaskPipelineContext context)  throws Exception {
        System.out.println("Starting Process Stage");
        CompletableFuture<Void> future = CompletableFuture.runAsync(() ->{
            try {
                String data = null;
                while(!(data = context.getFetchToProcessQueue().take()).equals("END")){
                    String processedData = data.toUpperCase();
                    System.out.println("Processed Data ="+processedData);
                    context.getProcessToSaveQueue().add(processedData);
                    Thread.sleep(100);
                }
                context.getProcessToSaveQueue().add("END");

            }catch (Exception e){
               e.printStackTrace();
            }
        });

        future.thenRunAsync(() -> {
            System.out.println("Process stage completed, transitioning to Save Result stage.");
            context.setStage(new saveResultStage());
            try {
                context.getStage().saveResult(context); // Start saving after processing is done
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).join();

    }

    @Override
    public void fetchData(TaskPipelineContext context) throws Exception {
        throw new Exception("Not allowed operation at this stage");
    }

    @Override
    public void saveResult(TaskPipelineContext context)  throws Exception{
        throw new Exception("Not allowed operation at this stage");
    }
}
