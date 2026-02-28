package Stream.JavaStream4;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Collector<String, StringBuilder, String> myCollector =
                Collector.of(

                        // supplier
                        () -> new StringBuilder(),

                        // accumulator
                        (sb, element) -> {
                            if (sb.length() > 0) {
                                sb.append(",");
                            }
                            sb.append(element);
                        },

                        // combiner (parallel streams ke liye)
                        (sb1, sb2) -> {
                            if (sb1.length() > 0 && sb2.length() > 0) {
                                sb1.append(",");
                            }
                            sb1.append(sb2);
                            return sb1;
                        },

                        // finisher
                        sb -> sb.toString()
                );
        String result = Stream.of("A","B","C").collect(myCollector);
        System.out.println("Result is "+result);

        //Same can be done using
        String result1 = Stream.of("A","B","C").collect(Collectors.joining());
        System.out.println("Result1 is "+result1);

        Collector<Integer,int[],Integer> sumCollector = Collector.of(
                () -> new int[1],
                (arr,element) -> arr[0]+=element,
                (arr1,arr2) -> {
                    arr1[0] += arr2[0];
                    return arr1;
                },
                arr -> arr[0]
        );
        int sum = Stream.of(1,2,3,4)
                .collect(sumCollector);

        System.out.println("Sum is "+sum);


        int[] arrm = { 2, 0, 6, 1, 5, 3, 7 };
        Set<Integer> S = IntStream.of(arrm)        // Returns IntStream
                .boxed() // Used to convert IntStream to Stream<Integer>
                .collect(Collectors.toSet());

        List<Integer> saList = Arrays.asList(2000,3000,1000);

        int summmd = saList.stream()
                .mapToInt(Integer::intValue)  // Convert Stream<Integer> to IntStream
                .sum();  // Sum the elements

        int[] freq = { 25, 10, 20 };
        // get the current node's cost
        //let say i=0,j=2
        int i1=1;
        int j1=2;
        int total_sum = IntStream.of(freq).sum();
        int total_sum1 = IntStream.rangeClosed(i1,j1).sum();
        int total = IntStream.rangeClosed(i1, j1).map(k -> freq[k]).sum();
        System.out.println("Range sum is from "+i1+" to "+ j1+" is "+total+" while total sum is :"+total_sum+" checklist:"+total_sum1); //Range sum is from 1 to 2 is 30 while total sum is :55 checklist:3


        int[] samb= {23,24,12,33,45};
        int sumHukr= IntStream.of(samb).sum();

        IntStream st= Arrays.stream(samb);
        Stream<Integer> shdj = Arrays.stream(samb).boxed();
        int sumHukr1= st.sum();

        System.out.println("-----------------------------");
        System.out.println("Sumhukr:"+ sumHukr+ " and Sumhukr1="+sumHukr1); // Sumhukr:137 and Sumhukr1=137
        System.out.println("-----------------------------");

        List<List<String>> seLists = Arrays.asList(
                Arrays.asList("I","Love","java"),
                Arrays.asList("Microservices","are","good"),
                Arrays.asList("Mouse","pointer","nknvd")
        );

        List<String> ans4 = seLists.stream().flatMap(senetence -> senetence.stream().map(e -> e.toUpperCase())).collect(Collectors.toUnmodifiableList());
        System.out.println("ans4 is:"+ans4);

        List<Integer> prac = Arrays.asList(2,1,4,7,10);
        Stream<Integer> numstStream = prac.stream()
                .filter((Integer x1)-> x1>=3)
                .peek((Integer x1)->System.out.println("after filter:"+x1))
                .map((Integer x1)-> (x1*-1))
                .peek((Integer x1)->System.out.println("after map:"+x1))
                .sorted()
                .peek((Integer x1)->System.out.println("after sorted:"+x1));

        List<Integer> ansprac = numstStream.collect(Collectors.toList());
        /*
        after filter:4
        after map:-4
        after filter:7
        after map:-7
        after filter:10
        after map:-10
        after sorted:-10
        after sorted:-7
        after sorted:-4
         */

        List<Integer> a5=Arrays.asList(2,1,4,7,10);
        int sumArr = a5.stream().reduce((a,b)->a+b).get();
        int secondHighest = a5.stream().sorted((a,b)-> b-a).limit(2).skip(1).findFirst().get();
        System.out.println("Sum of arr ="+sumArr+" secondHighest ="+secondHighest);
        //Sum of arr =24 secondHighest =7

    }

}
