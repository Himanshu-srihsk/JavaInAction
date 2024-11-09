package MultithreadingQuestions.SynchronousTaskPipelineCompletetableFuture;

/*
Design a task pipeline with three stages: fetch data, process data, and save results.
Each stage should run in a separate thread or thread pool, processing data as it arrives from the previous stage.
Use a BlockingQueue to pass data between stages and CompletableFuture for asynchronous task chaining.
 */
public class SynchronousTaskPipelineCompletetableFuture {
    public static void main(String[] args) throws Exception {

        TaskPipelineContext context = new TaskPipelineContext();
        System.out.println(context.getStage());
        context.execute();;
        System.out.println(context.getStage());
        System.out.println("Pipeline completed successfully.");
    }
}
