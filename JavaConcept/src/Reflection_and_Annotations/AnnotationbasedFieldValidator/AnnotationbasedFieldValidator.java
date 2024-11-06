package Reflection_and_Annotations.AnnotationbasedFieldValidator;

public class AnnotationbasedFieldValidator {
    AnnotatationValidator annotatationValidator;
    public static void main(String[] args) throws IllegalAccessException {
       User user = new User(null, null);
        AnnotatationValidator validator = new AnnotatationValidator();
        validator.validate(user);
    }
}
