package LowLevelDesign.CallCenterApp;

public class Call {
    private Customer customer;
    private CallStatus status;
    private Agent assignedAgent;

    public Call(Customer customer) {
        this.customer = customer;
        this.status = CallStatus.WAITING;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public CallStatus getStatus() {
        return status;
    }

    public void setStatus(CallStatus status) {
        this.status = status;
    }

    public Agent getAssignedAgent() {
        return assignedAgent;
    }

    public void setAssignedAgent(Agent assignedAgent) {
        this.assignedAgent = assignedAgent;
    }

}
