package LowLevelDesign.CalculatorService;

public class operatorFactory {
    public static Command createCommand(String operator, Command left, Command right){
       // System.out.println("operator is " + operator);
        switch (operator){
            case "+":
                return new AddCommand(left, right);
            case "-":
                return new SubstractCommand(left, right);
            case "*":
                return new MultiplyCommand(left, right);
                case "/":
                    return new DivideCommand(left, right);
            default:
                throw new IllegalArgumentException("Invalid operator");
        }
    }
}
