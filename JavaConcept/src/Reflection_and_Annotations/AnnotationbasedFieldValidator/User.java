package Reflection_and_Annotations.AnnotationbasedFieldValidator;

public class User {
    @NotNull
    private String name;
    @NotNull
    private String email;
    User(String name, String email){
        this.name = name;
        this.email = email;
    }
}
