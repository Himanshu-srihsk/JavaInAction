package Reflection_and_Annotations.MultiAnnotationOnFieldValidator;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ValidateContainer.class)
public @interface Validate {
    ValidationType type();
    int minLength() default 3;
    String message() default "This field cannot be null";
}

