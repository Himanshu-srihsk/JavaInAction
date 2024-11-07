package Reflection_and_Annotations.AutomaticScopeManager;

@Scope(type = ScopeType.SINGLETON)
public class UserService {
    private UserService(){}
    public void display(){
        System.out.println("User Service Displayed");
    }
}
