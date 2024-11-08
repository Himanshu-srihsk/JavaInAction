package MultithreadingQuestions.ProducerConsumerProblem;

public class Producer implements Runnable {
    SharedResource sc;
    int item;

    public Producer(SharedResource sc) {
        this.sc = sc;
    }
    public void run() {
       for(int i = 0; i< 5;i++){
           try {
               sc.Produce(i+ " by " +Thread.currentThread().getName().toString());
               Thread.sleep(100);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
    }
}
