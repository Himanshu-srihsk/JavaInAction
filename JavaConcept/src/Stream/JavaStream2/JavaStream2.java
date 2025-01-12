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

        List<List<Integer>> arr =Arrays.asList(Arrays.asList(1,2,3), Arrays.asList(-2,0,5,7), Arrays.asList(-3,4,6,8,9));
        List<Integer> ans = arr.stream().flatMap(e -> e.stream()).sorted((a,b) -> b-a).collect(Collectors.toList());
        System.out.println("ans is "+ ans);

        Map<Integer,String> map = Map.ofEntries(Map.entry(1,"Banana"),Map.entry(2,"Apple"),Map.entry(3,"Orange"),Map.entry(4,"Mango"));
        System.out.println("unSorted map "+ map );

//        Map<Integer,String> treemap = new TreeMap<>((x,y) -> x.compareTo(y));
//        map.forEach((k,v) -> treemap.put(k,v));
//        System.out.println("Sorted map "+ treemap );

        List<Map.Entry<Integer,String>> cc  = map.entrySet().stream().sorted((a,b)->  a.getValue().compareTo(b.getValue())).collect(Collectors.toList());
        System.out.println("Sorted List "+ cc );
        Map<Integer,String> ccMap = new LinkedHashMap<>();
        for(var entry: cc){
            ccMap.put(entry.getKey(),entry.getValue());
        }
        Map<Integer, String> ccMap1 = cc.stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldVal, newVal) -> oldVal,
                        LinkedHashMap::new
                ));

        System.out.println("Sorted List ccMap "+ ccMap );
        System.out.println("Sorted List ccMap "+ ccMap1 );

        Map<Integer,String> ccMap2 = map.entrySet().stream().sorted((a,b) -> a.getValue().compareTo(b.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldVal,newVal) -> newVal, () -> new LinkedHashMap<>()));

        System.out.println("Sorted List ccMap "+ ccMap2 );


        String test = "testing trails";
        Map<Character,Long> freqMap = test.chars().mapToObj(e-> (char)e).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        List<Map.Entry<Character,Long>> freqMapSorted = freqMap.entrySet().stream().sorted((a,b) -> b.getValue().compareTo(a.getValue())).collect(Collectors.toList());
        System.out.println("freqMap="+freqMap);


        System.out.println("freqMapSorted="+freqMapSorted);


    }
}
