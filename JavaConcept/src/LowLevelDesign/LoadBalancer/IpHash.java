package LowLevelDesign.LoadBalancer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class IpHash implements loadBalance{
    public String getServer(String Ip){
        if (Ip == null) {
            Ip = "127.0.0.1";
        }
        Set<String> servers = IPpool.ipMap.keySet();
        List<String> serverList = new ArrayList<>();
        serverList.addAll(servers);
        String remoteId = Ip;
        Integer index = remoteId.hashCode() % serverList.size();
        String target = serverList.get(index);
        return target;
    }
}
