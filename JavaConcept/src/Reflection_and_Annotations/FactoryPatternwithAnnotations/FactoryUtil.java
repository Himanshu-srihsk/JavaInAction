package Reflection_and_Annotations.FactoryPatternwithAnnotations;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class FactoryUtil {
    public static <T> T createInstance(String type, Object... args) {
        try{
            Class<T> typeClass = (Class<T>) Class.forName(type);
            Constructor[] constructors = typeClass.getDeclaredConstructors();
            Constructor<?> factoryConstructor = Arrays.stream(constructors).filter((Constructor c) -> c.isAnnotationPresent(Factory.class))
                    .findFirst()
                    .orElseThrow(()-> new RuntimeException("No @Factory constructor found in class " + typeClass.getName()));
            factoryConstructor.setAccessible(true);
            return typeClass.cast(factoryConstructor.newInstance(args));
        }catch (ClassNotFoundException e){
             throw new RuntimeException("Class not found", e);
        }
        catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Failed to create instance", e);
        }
    }
}
