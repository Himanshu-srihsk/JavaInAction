package LowLevelDesign.BootstrapAggregationService;

public class Consumer {
    private String id;
    private String name;

    public Consumer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public String getName() { return name; }
}
