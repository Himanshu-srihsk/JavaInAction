package Reflection_and_Annotations.AutomaticScopeManager;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ScopeManagerUtil {
    private static Map<Class<?>, Object> beanMap = new ConcurrentHashMap<>();
    public static<T> T getBean(Class<T> clazz) {
         Scope scope = clazz.getAnnotation(Scope.class);
         if(scope == null || scope.type() == ScopeType.SINGLETON){
             if(!beanMap.containsKey(clazz)){
                 return getSingletonInstance(clazz);
             }else{
                 return (T) beanMap.get(clazz);
             }
         }else if(scope.type() == ScopeType.PROTOTYPE){
             return getPrototypeInstance(clazz);
         }
         throw new IllegalArgumentException("Unsupported scope type: "+scope.type());
    }

    public static <T> T getSingletonInstance(Class<T> clazz){
        T instance = null;
        try{
            Constructor<T> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            instance = constructor.newInstance();
            beanMap.put(clazz, instance);
        }catch(Exception e){
           e.printStackTrace();
        }
        return clazz.cast(beanMap.get(clazz));
    }
    public static <T> T getPrototypeInstance(Class<T> clazz){
         T instance = null;
        try{
            Constructor<T> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            instance = constructor.newInstance();

        }catch(Exception e){
            e.printStackTrace();
        }
        return instance;
    }
}
