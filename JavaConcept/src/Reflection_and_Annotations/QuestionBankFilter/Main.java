package Reflection_and_Annotations.QuestionBankFilter;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Create a Custom Annotation name @Question that can be applied to method which contains attributes like topic and difficulty
Filter the Questions dynamically at run time based on topic or difficulty level provided
 */
public class Main {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        Map<String, String> filter1 = new HashMap<>();
        filter1.put("topic", "Multithreading");
        filter1.put("difficulty", "Easy");

        List<String> questions = QuestionBankFilter.filter(QuestionBank.class, filter1);
        System.out.println("Filtered Questions for above topic Multithreading and difficulty Easy:");
        questions.forEach(System.out::println);

        Map<String, String> filter2 = new HashMap<>();
        filter2.put("topic", "Spring Boot");
        filter2.put("difficulty", "Easy");

        List<String> questions2 = QuestionBankFilter.filter(QuestionBank.class, filter2);
        System.out.println("Filtered Questions for above topic Spring Boot:");
        questions2.forEach(System.out::println);

        Map<String, String> filter3 = new HashMap<>();
        filter3.put("topic", "Multithreading");

        List<String> question3 = QuestionBankFilter.filter(QuestionBank.class, filter3);
        System.out.println("Filtered Questions for above topic Multithreading with any Difficulty:");
        question3.forEach(System.out::println);
    }
}
/*
Filtered Questions for above topic Multithreading and difficulty Easy:
What is fork join pool
Filtered Questions for above topic Spring Boot:
What is difference between controller and RestController
Filtered Questions for above topic Multithreading with any Difficulty:
What is Thread pool executor
What is fork join pool
 */