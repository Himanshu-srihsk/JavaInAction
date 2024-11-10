package LowLevelDesign.TV;

public class TVContext {
    private static TVContext instance;
    private static State state;
    private TVContext(){}
    public static TVContext getInstance() {
        if(instance==null){
            synchronized (TVContext.class){
                if(instance==null){
                    instance = new TVContext();
                    state = new TVStopState();
                }
            }
        }
        return instance;
    }
    public void setState(State state) {
        this.state = state;
    }
    public State getState() {
        return state;
    }
}
