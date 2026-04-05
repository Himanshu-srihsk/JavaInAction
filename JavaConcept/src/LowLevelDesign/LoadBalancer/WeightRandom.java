package LowLevelDesign.LoadBalancer;

import java.util.*;

public class WeightRandom implements loadBalance {
    public String getServer(String Ip){
        Set<String> servers = IPpool.ipMap.keySet();
        List<String> serverList = new ArrayList<>();
        Iterator<String> itr= serverList.iterator();
        while(itr.hasNext()){
            String server=itr.next();
            Integer w = IPpool.ipMap.get(server);
            if(w!=null && w>0){

                for (int i = 0; i < w; i++) {
                    serverList.add(server);
                }
            }
        }
        Integer randomIndex = new Random().nextInt(serverList.size());
        String target = serverList.get(randomIndex);

        return target;
    }
}
