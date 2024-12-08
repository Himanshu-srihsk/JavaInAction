package Stream.JavaStream2;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaStream2 {
    public static <T,R> Function<T,R> handleException(Function<T,R> function){
      return input ->{
          try {
              return function.apply(input);
          }catch (Exception e){
              System.err.println("Some Exception happend"+ e.getMessage());
              return null;
          }
      };
    }
    public static void main(String[] args) {

        List<String> numbers = List.of("1", "2", "a", "4");
        List<Integer> parsedNumbers = numbers.stream().map(num -> {
            try {
                return Integer.parseInt(num);
            }catch (NumberFormatException e){
                System.err.println("NumberFormatException  happend"+ e.getMessage());
                return null;
            }catch (Exception e){
                System.err.println("Some Exception happend"+ e.getMessage());
               return null;
            }
        }).filter(Objects::nonNull)
                //or .filter(e-> e!=null)
                .collect(Collectors.toList());

        System.out.println("Parsed Integers:"+ parsedNumbers);

        //Use a Wrapper Method for Exception Handling
        List<Integer> parsedNumbers1 = numbers.stream().map(handleException(Integer::parseInt)).filter(Objects::nonNull).collect(Collectors.toList());

        System.out.println("Parsed Integers1:"+ parsedNumbers1);

        List<String> sentences = Arrays.asList("Java is fun", "Streams are powerful");
        String joinedWords  = sentences.stream().flatMap(s -> Stream.of(s.split(" "))).collect(Collectors.joining());
        System.out.println("joinedWords is "+joinedWords);//JavaisfunStreamsarepowerful

        Stream.Builder<Integer> streamBuilder = Stream.builder();
        Stream<Integer> abc = streamBuilder.add(100).add(200).add(300).build();
//        int[] abcArray = abc.mapToInt(Integer::intValue).toArray();
        int[] abcArray = abc.mapToInt(e -> e.intValue()).toArray();
        System.out.println("abcArray is "+Arrays.toString(abcArray));

        List<List<String>> nestedList = new ArrayList<>();
        nestedList.add(Arrays.asList("banana", "apple", "cherry"));
        nestedList.add(Arrays.asList("date", "fig", "grape"));
        nestedList.add(Arrays.asList("kiwi", "melon", "orange"));

        List<String> sortedString = nestedList.stream().flatMap((List<String> s) -> s.stream()).sorted().collect(Collectors.toList());
        System.out.println("sortedString is "+ sortedString);

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(list.stream().limit(3).collect(Collectors.toList()));

    }
}
