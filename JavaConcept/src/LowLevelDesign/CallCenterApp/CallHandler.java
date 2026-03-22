package LowLevelDesign.CallCenterApp;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CallHandler {
    private List<Agent> agents;
    private CallQueue callQueue;
    private Map<String, Call> map;

    public CallHandler(List<Agent> agents, CallQueue callQueue) {
        this.agents = agents;
        this.callQueue = callQueue;
        map = new ConcurrentHashMap<>();
    }

    public void assignCall(Call call) {
        Agent availableAgent = findAvailableAgent();
        if (availableAgent != null) {
            availableAgent.assignCall(call);

        } else {
            System.out.println("this call will wait in Queue until any Agent get free  =" + call.getCustomer().getPhoneNumber());
            callQueue.addCall(call);
        }
    }

    private Agent findAvailableAgent() {
        for (Agent agent : agents) {
            if (agent.isAvailable()) {
                return agent;
            }
        }
        return null;
    }
    public void setCall(Customer customer,Call call) {
        map.put(customer.getPhoneNumber(),call);
    }
    public Call getCall(Customer customer) {
        return map.get(customer.getPhoneNumber());
    }

    public void endCall(Call call) {

        if (call.getAssignedAgent() != null) {
            System.out.println("Checking assigned agent for this call  is "+ call.getAssignedAgent().getName()+ " customer name is "+ call.getCustomer().getPhoneNumber() );
            call.getAssignedAgent().endCall(call);
            map.remove(call.getCustomer().getPhoneNumber());
        }

        if (!callQueue.isEmpty()) {
            assignCall(callQueue.getNextCall());
        }
    }
}
