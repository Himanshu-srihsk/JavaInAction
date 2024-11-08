package MultithreadingQuestions.ProducerConsumerProblem;

public class Consumer implements Runnable {
    SharedResource sc;
    int item;

    public Consumer(SharedResource sc) {
        this.sc = sc;
    }
    public void run() {
        for(int i = 0; i< 5;i++){
            try {
                sc.Consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
