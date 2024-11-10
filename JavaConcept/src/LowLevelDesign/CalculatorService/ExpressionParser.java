package LowLevelDesign.CalculatorService;
//
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class ExpressionParser {

    public static Command parse(String expression){
        Stack<Command> postfix= convertToPostfix(expression);
        return postfix.pop();
    }
    private static Stack<Command> convertToPostfix(String expression) {
        Stack<Command> postFixBuilder = new Stack<>();
        Stack<String> operators = new Stack<String>();
//         String[] tokens = expression.split("[ ,;]+");
//
//        for(String token : tokens){
//            System.out.println("token is:"+token+"hukr");
//        }
//        for(String token : tokens){
//            System.out.println("my token is "+ token);
//            if(token.equals("(")){
//                operators.push(token);
//            }else if(token.equals(")")){
//               while(!operators.isEmpty() &&!operators.peek().equals("(")){
//                   if(!operators.peek().startsWith("sin")){
//                       Command right = postFixBuilder.pop();
//                       Command left = postFixBuilder.pop();
//                       postFixBuilder.add(operatorFactory.createCommand(operators.pop(), left, right));
//                   }else{
//                       postFixBuilder.add(new SineCommand(postFixBuilder.pop()));
//                   }
//
//               }
//               operators.pop(); // remove the '('
//            }else if(isOperator(token)){
//                 while(!operators.isEmpty() && precedence(operators.peek()) >= precedence(token)){
//                     System.out.println("operator precedence " + token + " is " + precedence(operators.peek()));
//                     Command right = postFixBuilder.pop();
//                     Command left = postFixBuilder.pop();
//                     postFixBuilder.add(operatorFactory.createCommand(operators.pop(), left, right));
//                 }
//                 operators.push(token);
//            }else if(token.startsWith("sin")){
//                 operators.push(token);
//            }else{
//                postFixBuilder.add(new ConstantCommand(Double.valueOf(token)));
//            }
//        }

        StringTokenizer tokenizer = new StringTokenizer(expression, "+-*/() ", true);
        while(tokenizer.hasMoreTokens()){
            String token = tokenizer.nextToken().trim();
            if (token.isEmpty()) continue;
            if(token.equals("(")){
                operators.push(token);
            }else if(token.equals(")")){
               while(!operators.isEmpty() &&!operators.peek().equals("(")){
                   if(!operators.peek().startsWith("sin")){
                       Command right = postFixBuilder.pop();
                       Command left = postFixBuilder.pop();
                       postFixBuilder.add(operatorFactory.createCommand(operators.pop(), left, right));
                   }else{
                       postFixBuilder.add(new SineCommand(postFixBuilder.pop()));
                   }

               }
               operators.pop(); // remove the '('
            }else if(isOperator(token)){
                 while(!operators.isEmpty() && precedence(operators.peek()) >= precedence(token)){
                     //System.out.println("operator precedence " + token + " is " + precedence(operators.peek()));
                     Command right = postFixBuilder.pop();
                     Command left = postFixBuilder.pop();
                     postFixBuilder.add(operatorFactory.createCommand(operators.pop(), left, right));
                 }
                 operators.push(token);
            }else if(token.startsWith("sin")){
                 operators.push(token);
            }else{
                postFixBuilder.add(new ConstantCommand(Double.valueOf(token)));
            }
        }

        while(!operators.isEmpty()){
            if(operators.peek().startsWith("sin")){
               // System.out.println("SINE Operators ggtf: " + operators.peek());
                postFixBuilder.add(new SineCommand(postFixBuilder.pop()));
                operators.pop();
            }else{
                Command right = postFixBuilder.pop();
                Command left = postFixBuilder.pop();
              //  System.out.println("Operators ggtf: " + operators.peek());
                postFixBuilder.add(operatorFactory.createCommand(operators.pop(),left,right));
            }

        }
        return postFixBuilder;

    }
    private static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }
    private static int precedence(String operator) {
        if (operator.equals("+") || operator.equals("-")) {
            return 1;
        } else if (operator.equals("*") || operator.equals("/")) {
            return 2;
        } else {
            return 0;
        }
    }
}

