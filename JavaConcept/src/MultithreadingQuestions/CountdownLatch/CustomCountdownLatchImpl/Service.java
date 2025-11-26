package MultithreadingQuestions.CountdownLatch.CustomCountdownLatchImpl;


public class Service implements Runnable{
    private final String name;
    private final int startupTime;
    private final CountDownLatch latch;

    public Service(String name, int startupTime, CountDownLatch latch) {
        this.name = name;
        this.startupTime = startupTime;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            System.out.println(name + " is starting...");
            Thread.sleep(startupTime);
            System.out.println(name + " is up!");
        }catch (Exception e){
           e.printStackTrace();
        }finally {
            latch.countDown();
        }
    }
}
