package MultithreadingQuestions.SynchronousTaskPipelineCompletetableFuture;

interface  Stage {
     void processData(TaskPipelineContext context) throws Exception;
      void fetchData(TaskPipelineContext context) throws Exception;
     void saveResult(TaskPipelineContext context) throws Exception;
}

