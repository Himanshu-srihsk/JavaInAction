package LowLevelDesign.TaskPipeline;

import java.io.BufferedReader;
import java.io.FileReader;

public class fetchDataStage implements Stage{
    @Override
    public void processData(TaskPipelineContext context)  throws Exception {
        throw new Exception("Not allowed operation at this stage");
    }

    @Override
    public void fetchData(TaskPipelineContext context) throws Exception {
        System.out.println("Starting Fetch Stage");
        try(BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Himanshu\\Desktop\\work\\Java\\Java-2\\JavaConcept\\src\\LowLevelDesign\\TaskPipeline\\input.txt"))){
            String line = br.readLine();
            while(line!=null){
                System.out.println("Data fetched: " + line);
                context.getFetchToProcessQueue().add(line);
                Thread.sleep(100);
                line = br.readLine();
            }
            context.getFetchToProcessQueue().add("END");
        }
        context.setStage(new ProcessDataStage());

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
