package LowLevelDesign.CalculatorService;

public class SineCommand implements Command{
    private Command command;

    public SineCommand(Command command) {
        this.command = command;
    }
    @Override
    public double execute() {
        return Math.sin(Math.toRadians(command.execute()));
    }
}
