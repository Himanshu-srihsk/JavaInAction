package Reflection_and_Annotations.CustomAccessControl;

public enum RoleType {
    ADMIN("admin"),
    GUEST("guest"),
    USER("user");
    String role;
    RoleType(String role){
        this.role = role;
    }
}
