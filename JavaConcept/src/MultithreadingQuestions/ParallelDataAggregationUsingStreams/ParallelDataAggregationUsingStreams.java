package MultithreadingQuestions.ParallelDataAggregationUsingStreams;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
Parallel Data Aggregation Using Streams
	Given a large dataset, write a program to calculate aggregate metrics (example : average, sum)
	 using the parallel stream API.
	Experiment with different stream operations to optimize performance,
	and measure the performance difference between parallel and sequential streams.

 */

class AggregateMetrics {
    private final int sum;
    private final double average;
    private final int max;
    private final int min;

    public AggregateMetrics(int sum, double average, int max, int min) {
        this.sum = sum;
        this.average = average;
        this.max = max;
        this.min = min;
    }

    @Override
    public String toString() {
        return "Sum=" + sum + ", Average=" + average + ", Max=" + max + ", Min=" + min;
    }
}
public class ParallelDataAggregationUsingStreams {
    public static List<Integer> generateData(int size){
        Random random = new Random();
        return  IntStream.range(0, size)
                .mapToObj(i -> random.nextInt(100))
                .collect(Collectors.toList());
    }
    public static AggregateMetrics calculateMetricsSequentially(List<Integer> dataSets){
        int sum = dataSets.stream().reduce(0,Integer::sum);
        double average = dataSets.stream().mapToDouble(Integer::doubleValue).average().orElse(0.0);
        int max = dataSets.stream().mapToInt(Integer::intValue).max().orElse(-1);
        int min = dataSets.stream().mapToInt(Integer::intValue).min().orElse(-1);
        return new AggregateMetrics(sum, average, max, min);
    }
    public static AggregateMetrics calculateMetricsInParallel(List<Integer> dataSets){
        int sum = dataSets.parallelStream().reduce(0,Integer::sum);
        double average = dataSets.parallelStream().mapToDouble(Integer::doubleValue).average().orElse(0.0);
        int max = dataSets.parallelStream().mapToInt(Integer::intValue).max().orElse(-1);
        int min = dataSets.parallelStream().mapToInt(Integer::intValue).min().orElse(-1);
        return new AggregateMetrics(sum, average, max, min);
    }
    public static void main(String[] args) {
       int dataSetSize = 10_000_000;
        List<Integer> dataSets = generateData(dataSetSize);
        // System.out.println("DataSets are "+dataSets);
        long startSequential = System.currentTimeMillis();
        AggregateMetrics sequentialMetrics = calculateMetricsSequentially(dataSets);
        long endSequential = System.currentTimeMillis();


        long startParallel = System.currentTimeMillis();
        AggregateMetrics parallelMetrics = calculateMetricsInParallel(dataSets);
        long endParallel = System.currentTimeMillis();

        System.out.println("Sequential Stream Metrics: " + sequentialMetrics);
        System.out.println("Time Taken (Sequential): " + (endSequential - startSequential) + " ms");

        System.out.println("Parallel Stream Metrics: " + parallelMetrics);
        System.out.println("Time Taken (Parallel): " + (endParallel - startParallel) + " ms");

    }
}

/*
Sequential Stream Metrics: Sum=495002255, Average=49.5002255, Max=99, Min=0
Time Taken (Sequential): 531 ms
Parallel Stream Metrics: Sum=495002255, Average=49.5002255, Max=99, Min=0
Time Taken (Parallel): 95 ms
 */