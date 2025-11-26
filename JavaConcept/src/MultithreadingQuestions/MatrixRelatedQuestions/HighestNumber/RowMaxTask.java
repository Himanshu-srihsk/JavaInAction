package MultithreadingQuestions.MatrixRelatedQuestions.HighestNumber;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Callable;

public class RowMaxTask implements Callable<Integer> {
    int[] row;
    int rowIndex;
    private static final Random rand = new Random();
    RowMaxTask(int[] row,int rowIndex){
        this.row = row;
        this.rowIndex = rowIndex;
    }
    @Override
    public Integer call() throws Exception {
        int delay = rand.nextInt(1000);
        Thread.sleep(delay);
        System.out.println(Thread.currentThread().getName() +
                " processed Row " + rowIndex +
                " (slept " + delay + " ms)");
        int max =  Arrays.stream(row).max().orElse(Integer.MIN_VALUE);
        return max;
    }
}
