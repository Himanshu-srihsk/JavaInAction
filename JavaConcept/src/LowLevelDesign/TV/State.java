package LowLevelDesign.TV;

public interface State {
    void powerOn(TVContext context) throws Exception;
    void switchChannel(TVContext context) throws Exception;
    void changeVolume(TVContext context,int value) throws Exception;
    void powerOff(TVContext context) throws Exception;
}
