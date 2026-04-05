package LowLevelDesign.LoadBalancer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class WeightRoundRobin implements loadBalance {
    private static Integer position = 0;
    Object ob;
    public String getServer(String Ip){
        Set<String> servers = IPpool.ipMap.keySet();
        List<String> serverList = new ArrayList<>();

        Iterator<String> iterator = servers.iterator();
        while (iterator.hasNext()) {
            String serverItem = iterator.next();
            Integer weight = IPpool.ipMap.get(serverItem);
            if (weight > 0) {
                for (int i = 0; i < weight; i++) {
                    serverList.add(serverItem);
                }
            }

        }
        synchronized(this){
            if(position > serverList.size()){
                position = 0;
            }
            String target = serverList.get(position);
            position++;
            return target;
        }

    }
}
