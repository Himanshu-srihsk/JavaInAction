package Reflection_and_Annotations.SimpleDependencyInjection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class DependencyInjectorUtil {
    private static Map<Class<?>,Object> instances = new ConcurrentHashMap<>();
    public static <T> T injectDependencies(T obj) {
        Class<?> objClass = obj.getClass();
        List<Field> injectedFields =Arrays.stream(objClass.getDeclaredFields()).filter(e -> e.isAnnotationPresent(Inject.class)).collect(Collectors.toList());
        for(Field field:injectedFields){
            field.setAccessible(true);
            try{
                Object dependency = field.getType().getDeclaredConstructor().newInstance();
                field.set(obj,dependency);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return obj;

    }

    public static <T> T getInstance(Class<T> clazz) {
        if (!instances.containsKey(clazz)) {
            try {
                // Use clazz.getDeclaredConstructor().newInstance() directly
                T object = clazz.getDeclaredConstructor().newInstance();
                injectDependencies(object);
                instances.put(clazz, object);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return clazz.cast(instances.get(clazz));
    }
}
