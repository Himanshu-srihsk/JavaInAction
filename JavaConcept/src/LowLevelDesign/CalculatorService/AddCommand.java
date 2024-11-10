package LowLevelDesign.CalculatorService;

public class AddCommand implements Command{
    Command left;
    Command right;

    public AddCommand(Command left, Command right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public double execute() {
        return left.execute() + right.execute();
    }
}
