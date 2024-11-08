package Reflection_and_Annotations.AnnotationbasedSQLQueryBuilder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ManyToMany {
    String joinTable();
    String joinColumn() default "";
    String inverseJoinColumn() default "";
}
