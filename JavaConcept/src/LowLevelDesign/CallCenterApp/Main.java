package LowLevelDesign.CallCenterApp;

import java.util.ArrayList;
import java.util.List;

/*
Requirements :
Handle incoming customer calls.
Assign calls to available agents.
Queue calls if all agents are busy.
Track call status and history.
Enable agent-customer interactions (e.g., ending or transferring calls)
 */
public class Main {
    public static void main(String[] args) {
        // Initialize agents
        Agent agent1 = new Agent("A1", "Alice");
        Agent agent2 = new Agent("A2", "Bob");
        Agent agent3 = new Agent("A3", "Charlie");

        List<Agent> agents = new ArrayList<>();
        agents.add(agent1);
        agents.add(agent2);
        agents.add(agent3);

        CallCenter callCenter = CallCenter.getInstance();
        callCenter.setAgents(agents);

        //  sample customers and simulate incoming calls
        Customer customer1 = new Customer("ram", "123-456-7890");
        Customer customer2 = new Customer("mohan", "234-567-8901");
        Customer customer3 = new Customer("sita", "345-678-9012");
        Customer customer4 = new Customer("himanshu", "456-789-0123");

        // Receive calls
        System.out.println("Receiving calls...");
        callCenter.receiveCall(customer1);  // Should be assigned to Alice
        callCenter.receiveCall(customer2);  // Should be assigned to Bob
        callCenter.receiveCall(customer3);  // Should be assigned to Charlie

        // At this point, all agents are busy, so this call should go to the queue
        callCenter.receiveCall(customer4);  // Should go to the queue

        System.out.println("checking intermediate  Agent Status:");
        for (Agent agent : agents) {
            System.out.println(agent.getName() + " - Available: " + agent.isAvailable());
        }

        // End a call to free up an agent
        System.out.println("\nEnding call for customer1...");
        callCenter.endCall(customer1); // Should assign customer4's call to a freed agent
        // End a call to free up an agent
        System.out.println("\nEnding call for customer2...");
        callCenter.endCall(customer2); // Should assign customer4's call to a freed agent

        System.out.println("\nFinal Agent Status:");
        for (Agent agent : agents) {
            System.out.println(agent.getName() + " - Available: " + agent.isAvailable());
        }
    }
}
/*
Receiving calls...
Assinging call to Agent Alice and Caller Phone no is 123-456-7890
Assinging call to Agent Bob and Caller Phone no is 234-567-8901
Assinging call to Agent Charlie and Caller Phone no is 345-678-9012
this call will wait in Queue until any Agent get free  =456-789-0123
checking intermediate  Agent Status:
Alice - Available: false
Bob - Available: false
Charlie - Available: false

Ending call for customer1...
Checking assigned agent for this call  is Alice customer name is 123-456-7890
ending call with Agent Alice and Caller Phone no is 123-456-7890
Assinging call to Agent Alice and Caller Phone no is 456-789-0123

Ending call for customer2...
Checking assigned agent for this call  is Bob customer name is 234-567-8901
ending call with Agent Bob and Caller Phone no is 234-567-8901

Final Agent Status:
Alice - Available: false
Bob - Available: true
Charlie - Available: false
 */