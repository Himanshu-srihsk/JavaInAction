package LowLevelDesign.LoadBalancer;

public class Main {
    public static void main(String[] args) {
        run();
    }
    public static void run() {
        LoadBalance();
    }
    public static void LoadBalance() {
        // doGetServer(new RoundRobin());
        // doGetServer(new RandomLoadBalance());
        // doGetServer(new IpHash());
        doGetServer(new WeightRoundRobin());
        doGetServer(new WeightRandom());
    }

    public static void doGetServer(loadBalance l){
        doGetServer(l, 100);
    }
    public static void doGetServer(loadBalance l,int querytimes){
        for (int i = 0; i < querytimes; i++) {
            String serverId = l.getServer(String.valueOf(i));
            System.out.println(String.format("[%s] index:%s,%s", l.getClass().getSimpleName(), i, serverId));
        }
    }
}
