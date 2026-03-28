package LowLevelDesign.NoSqlDbExample;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public interface NoSQLDatabase {
    void insert(String collectionName, Map<String, Object> document);
    List<Map<String, Object>> find(String collectionName, Predicate<Map<String, Object>> filter);
    void update(String collectionName, Predicate<Map<String, Object>> filter, Map<String, Object> updatedFields);
    void delete(String collectionName, Predicate<Map<String, Object>> filter);
    void printAll(String collectionName);
}
