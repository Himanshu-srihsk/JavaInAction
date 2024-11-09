package LowLevelDesign.TaskPipeline;

public class PipelineUsingStateDesignPattern {
    public static void main(String[] args) throws Exception {
          TaskPipelineContext context = new TaskPipelineContext();
             context.getStage().fetchData(context);
             context.getStage().processData(context);
             context.getStage().saveResult(context);
             System.out.println(context.getStage());
        System.out.println("Pipeline completed successfully.");
    }
}
