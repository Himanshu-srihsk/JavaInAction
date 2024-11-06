package Reflection_and_Annotations.MultiAnnotationOnFieldValidator;

public class User {
    @Validate(type = ValidationType.Notnull, message = "Name must not be null")
    @Validate(type = ValidationType.MinLength, message = "Name must > 3")
    private String name;
    @Validate(type = ValidationType.Notnull, message = "email must not be null")
    private String email;
    User(String name, String email){
        this.name = name;
        this.email = email;
    }
}
