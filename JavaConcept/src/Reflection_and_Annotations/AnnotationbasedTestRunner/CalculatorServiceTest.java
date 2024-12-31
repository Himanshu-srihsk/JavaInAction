package Reflection_and_Annotations.AnnotationbasedTestRunner;

public class CalculatorServiceTest {
    CalculatorService calculatorService;

//    @Before
//    public void setUp() {
//        calculatorService = new CalculatorService();
//    }

    @BeforeEach
    public void setUp() {
        calculatorService = new CalculatorService();
    }

    @Test
    void testAdd() {
        int result = calculatorService.add(2, 3);
        Assertion.assertEquals(5, result, "2 + 3 should equal 5");
    }

    @Test
    void testSubtract() {
        int result = calculatorService.subtract(5, 3);
        Assertion.assertNotEquals(1, result, "5 - 3 should not equal 1");
    }
    @Test
    void testMultiply() {
        int result = calculatorService.multiply(2, 3);
        Assertion.assertEquals(5, result, "2 * 3 should equal 6");
    }
    @Test
    void testDivide() {
        double result = calculatorService.divide(6, 3);
        Assertion.assertEquals(2.0, result, "6 / 3 should equal 2");
    }

    @Test
    void testDivideByZero() {
        Assertion.assertThrows(ArithmeticException.class, () -> {
            calculatorService.divide(10, 0);
        });

    }

}
