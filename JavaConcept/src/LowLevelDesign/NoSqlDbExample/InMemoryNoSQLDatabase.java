package LowLevelDesign.NoSqlDbExample;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class InMemoryNoSQLDatabase implements NoSQLDatabase {
    private final Map<String, Collection> collections = new HashMap<>();

    private Collection getOrCreateCollection(String name) {
        return collections.computeIfAbsent(name, k -> new Collection());
    }

    @Override
    public void insert(String collectionName, Map<String, Object> document) {
        getOrCreateCollection(collectionName).insert(document);
    }

    @Override
    public List<Map<String, Object>> find(String collectionName, Predicate<Map<String, Object>> filter) {
        return getOrCreateCollection(collectionName).find(filter);
    }

    @Override
    public void update(String collectionName, Predicate<Map<String, Object>> filter, Map<String, Object> updatedFields) {
        getOrCreateCollection(collectionName).update(filter, updatedFields);
    }

    @Override
    public void delete(String collectionName, Predicate<Map<String, Object>> filter) {
        getOrCreateCollection(collectionName).delete(filter);
    }

    @Override
    public void printAll(String collectionName) {
        getOrCreateCollection(collectionName).printAll();
    }
}
