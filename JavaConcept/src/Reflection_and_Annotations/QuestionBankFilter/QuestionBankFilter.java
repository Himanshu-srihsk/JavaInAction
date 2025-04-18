package Reflection_and_Annotations.QuestionBankFilter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class QuestionBankFilter {
  public static <T> List<String> filter(Class<T> clazz, Map<String, String> filters) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
      T instance = clazz.getDeclaredConstructor().newInstance();
      List<String> result = new ArrayList<>();
      Method[] methods = Arrays.stream(clazz.getDeclaredMethods()).filter(e -> e.isAnnotationPresent(Question.class)).toArray(Method[]::new);
      for(Method method: methods){
          Question q = method.getAnnotation(Question.class);
          boolean matchesAllFilters = true;
          for(Map.Entry<String,String> entry: filters.entrySet()){
              String filterKey = entry.getKey();
              String filterVal = entry.getValue();

              Method annotaionField = q.annotationType().getMethod(filterKey);
              String annotationFieldval = (String) annotaionField.invoke(q);

              if(!annotationFieldval.equalsIgnoreCase(filterVal)){
                  matchesAllFilters = false;
                  break;
              }
          }
          if(matchesAllFilters){
              result.add((String) method.invoke(instance));
          }
      }
      return result;
  }
}
