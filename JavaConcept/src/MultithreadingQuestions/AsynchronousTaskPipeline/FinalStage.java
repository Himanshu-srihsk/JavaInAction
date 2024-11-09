package MultithreadingQuestions.AsynchronousTaskPipeline;

public class FinalStage implements Stage {
    @Override
    public void processData(TaskPipelineContext context) throws Exception {
        throw new Exception("Not allowed operation at this stage");
    }

    @Override
    public void fetchData(TaskPipelineContext context) throws Exception {
        throw new Exception("Not allowed operation at this stage");
    }

    @Override
    public void saveResult(TaskPipelineContext context) throws Exception {
        throw new Exception("Not allowed operation at this stage");
    }

    @Override
    public String toString() {
        return "Final stage";
    }
}
