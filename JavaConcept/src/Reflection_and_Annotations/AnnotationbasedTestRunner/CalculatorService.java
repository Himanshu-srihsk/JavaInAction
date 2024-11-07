package Reflection_and_Annotations.AnnotationbasedTestRunner;

public class CalculatorService {
    public CalculatorService(){
        System.out.println("CalculatorService Constructor Called");
    }
    public int add(int a, int b) {
        return a + b;
    }
    public int subtract(int a, int b) {
        return a - b;
    }
    public int multiply(int a, int b) {
        return a * b;
    }
    public double divide(int a, int b) {
        return a / b;
    }
}
