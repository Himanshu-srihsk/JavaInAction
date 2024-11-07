package Reflection_and_Annotations.CustomSerializationUtility;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class SerialzableUtil {
    private static Map<String,Object> serializedData = new ConcurrentHashMap<>();
    public static <T> String serializeObject(Object object) {
        Class<?> clazz = object.getClass();
       List<Field> serialzableFields= Arrays.stream(clazz.getDeclaredFields())
               .filter(field -> field.isAnnotationPresent(Serializable.class))
               .collect(Collectors.toUnmodifiableList());

       for(Field field : serialzableFields){
           field.setAccessible(true);
           try {
               Object fieldValue = field.get(object);
               if(fieldValue!=null && !isPrimitiveOrWrapperOrString(fieldValue)){

                   serializeObject(fieldValue);
               }else{
                   serializedData.put(field.getName(), fieldValue);
               }
           } catch (IllegalAccessException e) {
               e.printStackTrace();
           }
       }
        return mapToJson(serializedData);
    }

    private static boolean isPrimitiveOrWrapperOrString(Object object) {
        Class<?> type = object.getClass();
        return type.isPrimitive() ||
                type == String.class ||
                type == Integer.class ||
                type == Double.class ||
                type == Boolean.class ||
                type == Long.class ||
                type == Short.class ||
                type == Float.class ||
                type == Byte.class ||
                type == Character.class;
    }
    private static String mapToJson(Map<String, Object> payload){
        StringBuilder jsonBuilder = new StringBuilder("{");
         for(Map.Entry<String,Object> entry: payload.entrySet()){
             jsonBuilder.append("\"").append(entry.getKey()).append("\": \"").append(entry.getValue()).append("\", ");
         }
         if(jsonBuilder.length()>1){
             jsonBuilder.delete(jsonBuilder.length()-2,jsonBuilder.length());
         }
        jsonBuilder.append("}");
        return jsonBuilder.toString();

    }
}

/*
* Serialized JSON: {"pincode": "560011", "city": "Bangalore", "name": "Himanshu", "age": "26"}
* */