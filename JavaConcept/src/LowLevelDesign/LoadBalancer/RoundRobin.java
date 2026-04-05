package LowLevelDesign.LoadBalancer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RoundRobin implements loadBalance{
    static int ind;
    Object ob;
    public String getServer(String Ip){
        Set<String> sp = IPpool.ipMap.keySet();
        List<String> servers = new ArrayList<>();
        servers.addAll(sp);
        String target=null;
        synchronized(this){
            if(ind>servers.size()-1){
                ind=0;
            }
            target=servers.get(ind);
            ind++;
        }
        return target;
    }
}
