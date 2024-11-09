package MultithreadingQuestions.SynchronousTaskPipelineCompletetableFuture;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TaskPipelineContext {
    private Stage stage;
    private final BlockingQueue<String> fetchToProcessQueue = new LinkedBlockingQueue<>();
    private final BlockingQueue<String> processToSaveQueue = new LinkedBlockingQueue<>();


    TaskPipelineContext(){
        stage = new fetchDataStage();
    }
    public TaskPipelineContext(Stage stage) {
        this.stage = stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public BlockingQueue<String> getFetchToProcessQueue() {
        return fetchToProcessQueue;
    }

    public BlockingQueue<String> getProcessToSaveQueue() {
        return processToSaveQueue;
    }
    public void execute() throws Exception {
        getStage().fetchData(this);
    }
}
