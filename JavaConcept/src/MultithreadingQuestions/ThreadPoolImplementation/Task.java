package MultithreadingQuestions.ThreadPoolImplementation;

public class Task implements Runnable{
    int taskId;
    public Task(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        try {
            System.out.println("Executing task ID: " + taskId + " on " + Thread.currentThread().getName());
            Thread.sleep(10000);
            System.out.println("task ID: " + taskId + " is Completed " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Task ID: " + taskId + " was interrupted.");
        }

    }
    @Override
    public String toString() {
        return "Task ID: " + taskId;
    }
}
