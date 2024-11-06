package Reflection_and_Annotations.AnnotationbasedFieldValidator;

import java.lang.reflect.Field;

public class AnnotatationValidator {
    private final StringBuilder stringBuilder = new StringBuilder();
    public  boolean validate(Object object) throws IllegalAccessException {
        Class<?> userClass = object.getClass();
        Field[] fields = userClass.getDeclaredFields();

        for (Field field : fields) {
            if(field.isAnnotationPresent(NotNull.class)){

                 NotNull notNull = field.getAnnotation(NotNull.class);
                   field.setAccessible(true);
                Object fieldValue  = field.get(object);
                   if(fieldValue == null){
                       stringBuilder.append("Validation failed for ").append(field.getName()).append(": ").append(notNull.message()).append("\n");
                   }
            }
        }
        if(stringBuilder.isEmpty()){
            System.out.println("Validation passed");
            return true;
        }else {
            System.out.println(stringBuilder.toString());
            return false;
        }

    }
}
