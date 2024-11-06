package Reflection_and_Annotations.MultiAnnotationOnFieldValidator;

import java.lang.reflect.Field;

public class AnnotatationValidator {
    private final StringBuilder stringBuilder = new StringBuilder();
    public  boolean validate(Object object) throws IllegalAccessException {
        Class<?> userClass = object.getClass();
        Field[] fields = userClass.getDeclaredFields();

        for (Field field : fields) {
         field.setAccessible(true);
         Object fieldValue = field.get(object);
          Validate[] validations = field.getAnnotationsByType(Validate.class);
            for(Validate validationType : validations){
               switch(validationType.type()){
                   case Notnull:
                       if(fieldValue ==null){
                           String message = validationType.message();
                           stringBuilder.append("Validation failed for ").append(field.getName()).append(": ").append(message).append("\n");
                       }
                       break;
                   case MinLength:
                      if(fieldValue instanceof String && ((String) fieldValue).length() < validationType.minLength()){
                          String message = validationType.message();
                          stringBuilder.append("Validation failed for ").append(field.getName()).append(": ").append(message).append("\n");
                      }
                      break;

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
