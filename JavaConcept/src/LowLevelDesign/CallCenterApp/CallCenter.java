package LowLevelDesign.CallCenterApp;

import java.util.List;

public class CallCenter {
    private CallHandler callHandler;
    private static CallCenter instance;

    private CallCenter(){

    }
    public static CallCenter getInstance(){
        if( instance == null ){
            synchronized(CallCenter.class){
                instance = new CallCenter();
            }
        }
        return instance;
    }
    public void setAgents(List<Agent> agents) {
        this.callHandler = new CallHandler(agents, new CallQueue());
    }

    public void receiveCall(Customer customer) {
        Call call = new Call(customer);
        callHandler.setCall(customer,call);
        callHandler.assignCall(call);
    }

    public void endCall(Customer customer) {
        Call call = callHandler.getCall(customer);
        callHandler.endCall(call);
    }
}
