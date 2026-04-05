package LowLevelDesign.LoadBalancer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomLoadBalance implements loadBalance{
    public String getServer(String Ip){
        Set<String> sp = IPpool.ipMap.keySet();
        List<String> servers = new ArrayList<>();
        servers.addAll(sp);
        int randomIndex = new Random().nextInt(servers.size());
        String target = servers.get(randomIndex);

        return target;
    }
}
