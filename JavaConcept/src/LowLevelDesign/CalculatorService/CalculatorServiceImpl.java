package LowLevelDesign.CalculatorService;

public class CalculatorServiceImpl {
    public static void main(String[] args) {
       Command command = new MultiplyCommand( new SubstractCommand(new AddCommand(new ConstantCommand(Double.valueOf(2)), new ConstantCommand(Double.valueOf(3)))
               ,new ConstantCommand(Double.valueOf(4)))
               ,new DivideCommand(new ConstantCommand(Double.valueOf(5)),new ConstantCommand(Double.valueOf(2))));

       try{
           double result = command.execute();
           System.out.println("result is "+result); //result is 2.5
       }catch(ArithmeticException e){
           System.out.println("caught exceptioon "+ e);
       }

      // String expression = "2 + 3 - 4 * ( 5 / 2 ) * ( 3 - 4 ) + 3";
       String expression = "2 + 3 - 4 * ( 5 / 2 ) + sin ( 30 )";
       // String expression = "2+ 3 - 4 * (5 / 2 ) * (3 - 4) + 3";
        try {
            Command command1 = ExpressionParser.parse(expression);
            double result = command1.execute();
            System.out.println("Result: " + result);  // Expected Result: -4.5

        }catch (Exception e){
            System.out.println("exception occured "+e);
            e.printStackTrace();
        }

    }
}
/*
result is 2.5
Result: -4.5
 */
