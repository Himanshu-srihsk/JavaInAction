package LowLevelDesign.CallCenterApp;

public class Agent {
    private String id;
    private String name;
    private boolean isAvailable;
    public Agent(String id, String name) {
        this.id = id;
        this.name = name;
        this.isAvailable = true;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
    public void assignCall(Call call) {
        System.out.println("Assinging call to Agent " + this.name + " and Caller Phone no is "+ call.getCustomer().getPhoneNumber());
        this.isAvailable = false;
        call.setStatus(CallStatus.IN_PROGRESS);
        call.setAssignedAgent(this);
    }

    public void endCall(Call call) {
        System.out.println("ending call with Agent " + this.name + " and Caller Phone no is "+ call.getCustomer().getPhoneNumber());
        this.isAvailable = true;
        call.setStatus(CallStatus.COMPLETED);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
