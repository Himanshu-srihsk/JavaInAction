package MultithreadingQuestions.DeadlockSimulationandResolution;

public class User {
    private String name;
    private int age;
    private Profile profile;

    public User(String name, int age, Profile profile) {
        this.name = name;
        this.age = age;
        this.profile = profile;
    }
}
