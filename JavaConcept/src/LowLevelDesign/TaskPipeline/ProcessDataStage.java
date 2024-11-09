package LowLevelDesign.TaskPipeline;

public class ProcessDataStage implements Stage{
    @Override
    public void processData(TaskPipelineContext context)  throws Exception {
        System.out.println("Starting Process Stage");
        String data = null;
        while(!(data = context.getFetchToProcessQueue().take()).equals("END")){
            String processedData = data.toUpperCase();
            System.out.println("Processed Data ="+processedData);
            context.getProcessToSaveQueue().add(processedData);
            Thread.sleep(100);
        }
        context.getProcessToSaveQueue().add("END");
        context.setStage(new saveResultStage());
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
