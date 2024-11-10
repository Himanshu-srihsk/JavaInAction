package LowLevelDesign.CalculatorService;

public class DivideCommand implements Command{
    Command left;
    Command right;

    public DivideCommand(Command left, Command right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public double execute() {
        Double b = right.execute();
        if(b == 0){
            throw new ArithmeticException("Division by zero is not allowed");
        }
        return left.execute() / b;
    }
}
