package Reflection_and_Annotations.AnnotationbasedTestRunner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CalculatorTestUtil {
    public static <T> void runTests(Class<T> testClass){
        Optional<Method> beforeMethod = null;
        Optional<Method> beforEachMethod = null;
        int passed = 0;
        int failed = 0;

        try{
           List<Method> testMethods = Arrays.stream(testClass.getDeclaredMethods())
                   .filter(method -> method.isAnnotationPresent(Test.class)).collect(Collectors.toList());

           beforeMethod = Arrays.stream(testClass.getDeclaredMethods())
                   .filter(method -> method.isAnnotationPresent(Before.class)).findFirst();
            beforEachMethod = Arrays.stream(testClass.getDeclaredMethods())
                    .filter(method -> method.isAnnotationPresent(BeforeEach.class)).findFirst();
            T testClassObject = (T)testClass.newInstance();
            if(beforeMethod.isPresent()){
                beforeMethod.get().invoke(testClassObject);
            }
            for(Method testMethod: testMethods){
//                if(beforeMethod.isPresent()){
//                    beforeMethod.get().invoke(testClassObject);
//                    beforeMethod=Optional.empty();
//                }
                if(beforEachMethod.isPresent()){
                    beforEachMethod.get().invoke(testClassObject);
                }
                try {
                    testMethod.setAccessible(true);
                    testMethod.invoke(testClassObject);
                    System.out.println("Test " + testMethod.getName() + " passed.");
                    passed++;
                } catch (Exception e) {
                    System.out.println("Test " + testMethod.getName() + " failed with exception: " + e.getCause());
                     failed++;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        // Display summary of test results
        System.out.println("\nSummary:");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
    }
}
