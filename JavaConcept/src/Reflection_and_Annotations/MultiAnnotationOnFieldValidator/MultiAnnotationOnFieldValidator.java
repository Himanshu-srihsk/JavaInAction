package Reflection_and_Annotations.MultiAnnotationOnFieldValidator;

/*
Create a custom annotation @NotNull, @MinLength that can be applied to fields.
Write a validator method that:
Multiple annotations can be present over the field
Takes an object, finds fields annotated with @NotNull, @MinLength, and checks if they are non-null.
If any annotated field is null, print a message identifying the field.
Use this validator on a test class with a mix of nullable and non-nullable fields.
 */
public class MultiAnnotationOnFieldValidator {
    public static void main(String[] args) throws IllegalAccessException {
        User user = new User("ab",null);
        AnnotatationValidator validator = new AnnotatationValidator();
        validator.validate(user);
    }
}

/*Validation failed for name: Name must > 3
Validation failed for email: email must not be null*/
