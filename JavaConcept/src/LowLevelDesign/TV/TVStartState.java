package LowLevelDesign.TV;

public class TVStartState implements State{
    @Override
    public void powerOn(TVContext context) throws Exception {
        throw new Exception("Not allowed operation , TV is already on");
    }

    @Override
    public void switchChannel(TVContext context) throws Exception {
       System.out.println("Switching channel");
    }

    @Override
    public void changeVolume(TVContext context, int value) throws Exception {
        System.out.println("Changed Volume to " + value);
    }

    @Override
    public void powerOff(TVContext context) throws Exception {
        System.out.println("Turned off TV");
        context.setState(new TVStopState());
    }

    @Override
    public String toString() {
        return "TVStartState{}";
    }
}
