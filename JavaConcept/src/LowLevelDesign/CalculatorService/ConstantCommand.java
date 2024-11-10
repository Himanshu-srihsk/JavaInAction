package LowLevelDesign.CalculatorService;

public class ConstantCommand implements Command{
    Double a;
    public ConstantCommand(Double a) {
        this.a = a;
    }

    @Override
    public double execute() {
        return a;
    }
}
