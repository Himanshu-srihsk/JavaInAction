package Reflection_and_Annotations.AnnotationbasedTestRunner;

/*Annotation-based Test Runner
Create a custom annotation @Test to mark test methods.
Write a simple test runner that:
Finds and invokes methods annotated with @Test in a given class.
Reports the result (pass/fail) of each test and logs exceptions if any test fails.
This is similar to a mini-JUnit test framework.*/
public class AnnotationbasedTestRunner {
    public static void main(String[] args) {
       CalculatorTestUtil.runTests(CalculatorServiceTest.class);
    }
}

/*
with @BeforeEach annotation
* CalculatorService Constructor Called
Test testAdd passed.
CalculatorService Constructor Called
Test testDivide passed.
CalculatorService Constructor Called
Test testSubtract passed.
CalculatorService Constructor Called
Test testDivideByZero passed.
CalculatorService Constructor Called
Test testMultiply failed with exception: java.lang.AssertionError: 2 * 3 should equal 6

Summary:
Passed: 4
Failed: 1

* */

/*
with @Before annotation
CalculatorService Constructor Called
Test testMultiply failed with exception: java.lang.AssertionError: 2 * 3 should equal 6
Test testDivideByZero passed.
Test testSubtract passed.
Test testDivide passed.
Test testAdd passed.

Summary:
Passed: 4
Failed: 1
 */