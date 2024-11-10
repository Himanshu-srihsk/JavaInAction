package LowLevelDesign.CalculatorService;

public class MultiplyCommand implements Command{
    Command left;
    Command right;

    public MultiplyCommand(Command left, Command right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public double execute() {
        return left.execute() * right.execute();
    }
}
