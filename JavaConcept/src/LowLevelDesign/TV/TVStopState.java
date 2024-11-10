package LowLevelDesign.TV;

public class TVStopState implements State{
    @Override
    public void powerOn(TVContext context) throws Exception {
       System.out.println("TV is turned on");
       context.setState(new TVStartState());
    }

    @Override
    public void switchChannel(TVContext context) throws Exception {
      throw new Exception("Not allowed operation , first switch on TV");
    }

    @Override
    public void changeVolume(TVContext context,int value) throws Exception {
        throw new Exception("Not allowed operation , first switch on TV");
    }

    @Override
    public void powerOff(TVContext context) throws Exception {
        throw new Exception("Not allowed operation , first switch on TV");
    }

    @Override
    public String toString() {
        return "TVStopState{}";
    }
}
