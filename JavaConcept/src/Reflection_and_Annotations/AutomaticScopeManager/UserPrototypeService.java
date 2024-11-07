package Reflection_and_Annotations.AutomaticScopeManager;

@Scope(type = ScopeType.PROTOTYPE)
public class UserPrototypeService {
    private UserPrototypeService(){}
    public void display(){
        System.out.println("User Prototype Service Displayed");
    }
}
