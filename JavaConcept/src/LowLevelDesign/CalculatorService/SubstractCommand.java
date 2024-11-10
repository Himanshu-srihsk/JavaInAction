package LowLevelDesign.CalculatorService;

public class SubstractCommand implements Command{
    Command left;
    Command right;

    public SubstractCommand(Command left, Command right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public double execute() {
        return left.execute() - right.execute();
    }
}
