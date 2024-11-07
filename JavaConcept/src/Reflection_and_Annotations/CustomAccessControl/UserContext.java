package Reflection_and_Annotations.CustomAccessControl;

public class UserContext {
    private static ThreadLocal<RoleType> userRole  = new ThreadLocal<>();
    public static RoleType getUserRole() {
        return userRole.get();
    }
    public static void setUserRole(RoleType roleType) {
        userRole.set(roleType);
    }
    public static void clearUserRole() {
        userRole.remove();
    }
}
