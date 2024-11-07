package Reflection_and_Annotations.AutomaticScopeManager;

/* Automatic Scope Manager
Create an annotation @Scope to mark classes as singletons.
Write a utility that:
Keeps track of all classes annotated with @Scope and returns the same instance whenever requested.
This simulates dependency management by ensuring only one instance of each annotated class exists.
*/
public class AutomaticScopeManager {
    public static void main(String[] args) {
       UserService userService1 = ScopeManagerUtil.getBean(UserService.class);
       userService1.display();
       UserService userService2 = ScopeManagerUtil.getBean(UserService.class);
       userService2.display();
       System.out.println("userService1 == userService2 :" + (userService1 == userService2));
       System.out.println("userService1 hasCode:"+userService1.hashCode() + " userService2 hasCode:"+userService2.hashCode());

       UserPrototypeService userPrototypeService1 = ScopeManagerUtil.getBean(UserPrototypeService.class);
       userPrototypeService1.display();
       UserPrototypeService userPrototypeService2 = ScopeManagerUtil.getBean(UserPrototypeService.class);
       userPrototypeService2.display();
       System.out.println("userPrototypeService1 == userPrototypeService2 :"+ (userPrototypeService1 == userPrototypeService2));
       System.out.println("userPrototypeService1 hasCode:"+userPrototypeService1.hashCode() + " userPrototypeService2 hasCode:"+userPrototypeService2.hashCode());
    }
}
