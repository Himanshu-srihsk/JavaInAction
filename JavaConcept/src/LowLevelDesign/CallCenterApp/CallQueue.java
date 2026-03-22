package LowLevelDesign.CallCenterApp;

import java.util.LinkedList;
import java.util.Queue;

public class CallQueue {
    private Queue<Call> waitingCalls = new LinkedList<>();

    public void addCall(Call call) {
        waitingCalls.offer(call);
    }

    public Call getNextCall() {
        return waitingCalls.poll();
    }

    public boolean isEmpty() {
        return waitingCalls.isEmpty();
    }
}
