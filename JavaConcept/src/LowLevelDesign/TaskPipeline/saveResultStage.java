package LowLevelDesign.TaskPipeline;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class saveResultStage implements  Stage{
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

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Himanshu\\Desktop\\work\\Java\\Java-2\\JavaConcept\\src\\LowLevelDesign\\TaskPipeline\\output.txt"))) {
            String data;
             while(!(data = context.getProcessToSaveQueue().take()).equals("END")){
                 writer.write(data);
                 writer.newLine();
                 System.out.println("Saved: " + data);
                 Thread.sleep(100);
             }
        }
        context.setStage(new FinalStage());
    }
    @Override
    public String toString() {
        return "save Result stage";
    }
}
