package Stream.JavaStream3;


import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@FunctionalInterface
interface  Predicate<T>{
    boolean isEven(T num);
}
public class JavaStream3 {
    public static <V> void main(String[] args) {
        Predicate<Integer> p = (Integer num) -> num % 2 == 0;
        int[] arr = {10, 12, 4, 5, 3, 2, 7, 9};
        System.out.println();
        int[] ans = Arrays.stream(arr).filter(p::isEven).toArray();
        Arrays.stream(ans).forEach(System.out::println);
        System.out.println("2 is isEven ? " + p.isEven(3));

        StringBuilder s = new StringBuilder();
        String k = s.append("Ram").reverse().toString();
        System.out.println("k is " + k);
        s.reverse();
        System.out.println("s is" + s.toString());
        String s1 = "Ram";
        String s2 = "Ra";
        System.out.println("compare = " + s1.compareTo(s2));

        Map<Character, Long> countMp = s1.chars().mapToObj(e -> (char) e).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        String ans1 = countMp.entrySet().stream().sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())).map(e -> String.valueOf(e.getKey()).repeat(e.getValue().intValue())).collect(Collectors.joining());
      int[] piles = {1,4,2,5,3};
        piles = Arrays.stream(piles).boxed().sorted((a,b)->(b-a)).mapToInt(e -> e.intValue())
                .toArray();
        System.out.println(Arrays.toString(piles));


        int[] array = {10, 12, 4, 5, 3, 2, 7, 9};
        List<Integer> even= Arrays.stream(array).boxed().filter(e-> e%2==0).map(e-> e*2).collect(Collectors.toList());
        System.out.println("even is "+even);

    }


}

