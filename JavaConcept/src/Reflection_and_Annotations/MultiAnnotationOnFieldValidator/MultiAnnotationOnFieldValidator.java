package Reflection_and_Annotations.MultiAnnotationOnFieldValidator;

public class MultiAnnotationOnFieldValidator {
    public static void main(String[] args) throws IllegalAccessException {
        User user = new User("ab",null);
        AnnotatationValidator validator = new AnnotatationValidator();
        validator.validate(user);
    }
}

/*Validation failed for name: Name must > 3
Validation failed for email: email must not be null*/
