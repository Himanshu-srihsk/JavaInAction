package Reflection_and_Annotations.AnnotationbasedFieldValidator;

/*
Create a custom annotation @NotNull that can be applied to fields.
Write a validator method that:
Takes an object, finds fields annotated with @NotNull, and checks if they are non-null.
If any annotated field is null, print a message identifying the field.
Use this validator on a test class with a mix of nullable and non-nullable fields.
 */
public class AnnotationbasedFieldValidator {
    AnnotatationValidator annotatationValidator;
    public static void main(String[] args) throws IllegalAccessException {
       User user = new User(null, null);
        AnnotatationValidator validator = new AnnotatationValidator();
        validator.validate(user);
    }
}
/*
Validation failed for name: This field cannot be null
Validation failed for email: This field cannot be null
 */