package Reflection_and_Annotations.CustomAccessControl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RoleBasedAccessControl {
    public static void invoke(Object object, String name, Object...args) {

       try {
           Class<?> clazz = object.getClass();
           // Method method = clazz.getDeclaredMethod(name, getParameterTypes(args));
           Method method = findMethodWithRoleAnnotation(clazz, name, args);
           if(method!=null){
               method.setAccessible(true);
               Role role = method.getAnnotation(Role.class);
               if(role!=null){
                   RoleType[] allowedRoles = role.values();
                   RoleType currentUserRole = UserContext.getUserRole();
                   if(isAllowedAccessForRole(currentUserRole, allowedRoles)){
                       method.invoke(object,args);
                   }else{
                       // throw new RuntimeException("Access denied for user with role: "+ currentUserRole);
                       System.out.println("Access denied. Customer role: " + currentUserRole + " is not allowed to invoke " + method.getName());
                   }

               }else{
                   method.invoke(object,args);
               }
           }
       }
       catch (Exception e) {
           e.printStackTrace();
       }
    }
    public static Method findMethodWithRoleAnnotation(Class<?> object,String name, Object...args){
           for(Method method: object.getDeclaredMethods()){
               if(method.getName().equals(name) && matchParameters(method.getParameterTypes(), args)){
                   return method;
               }
           }
        return null;
    }
    private static boolean matchParameters(Class<?>[] parameterTypes, Object[] args) {
        if (parameterTypes.length != args.length) {
            return false;
        }
        for (int i = 0; i < parameterTypes.length; i++) {
            if(parameterTypes[i].isPrimitive()){
                if (!getWrapperClass(parameterTypes[i]).isAssignableFrom(args[i].getClass())) {
                    return false;
                }
            }else{
                // Example Integer is assignable to Number
                if(!parameterTypes[i].isAssignableFrom(args[i].getClass())) {
                    return false;
                }
            }

        }
        return true;
    }
    private static Class<?> getWrapperClass(Class<?> primitiveClass) {
        if (primitiveClass == int.class) return Integer.class;
        if (primitiveClass == long.class) return Long.class;
        if (primitiveClass == double.class) return Double.class;
        if (primitiveClass == float.class) return Float.class;
        if (primitiveClass == boolean.class) return Boolean.class;
        if (primitiveClass == char.class) return Character.class;
        if (primitiveClass == byte.class) return Byte.class;
        if (primitiveClass == short.class) return Short.class;
        return primitiveClass;  // Return the class itself if it's not a primitive
    }

    public static Class<?>[] getParameterTypes(Object... args){
        Class<?>[] parameterTypes = new Class[args.length];
        for(int i=0; i<args.length; i++){
            parameterTypes[i] = args[i].getClass();
        }
        // System.out.println("Parameter = " + parameterTypes);
        return parameterTypes;
    }
    public static Boolean isAllowedAccessForRole(RoleType currentRole, RoleType[] roles){
        for(RoleType roleType: roles){
            if(roleType == currentRole){
                return true;
            }
        }
        return false;
    }
}
