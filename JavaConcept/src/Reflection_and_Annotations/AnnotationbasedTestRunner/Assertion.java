package Reflection_and_Annotations.AnnotationbasedTestRunner;

public class Assertion {
    public static void assertEquals(Object expected, Object actual, String message) {
        if (!expected.equals(actual)) {
            throw new AssertionError(message);
        }
    }
    public static void assertNotEquals(Object expected, Object actual, String message) {
       if(expected.equals(actual)){
           throw new AssertionError(message);
       }
    }
    public static void assertThrows(Class<? extends Throwable> expectedException, Runnable task) {
        try {
            task.run();
        }catch (Throwable e){
             if(expectedException.isInstance(e)){
                 return;
             }else{
                 System.out.println("Expected " + expectedException.getName() + " but got " + e.getClass().getName());
                 throw new AssertionError("Expected " + expectedException.getName() + " but got " + e.getClass().getName());
             }
        }
        throw new AssertionError("Expected exception: " + expectedException + " but no exception was thrown");
    }
}
