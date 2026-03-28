package LowLevelDesign.NoSqlDbExample;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Collection {
    private final List<Map<String, Object>> documents = new ArrayList<>();
    private int autoId = 1;

    public void insert(Map<String, Object> document) {
        document.putIfAbsent("_id", autoId++); // add auto-generated _id if not present
        documents.add(document);
    }

    public List<Map<String, Object>> find(Predicate<Map<String, Object>> filter) {
        return documents.stream().filter(filter).collect(Collectors.toList());
    }

    public void update(Predicate<Map<String, Object>> filter, Map<String, Object> updatedFields) {
        for (Map<String, Object> doc : documents) {
            if (filter.test(doc)) {
                doc.putAll(updatedFields);
            }
        }
    }

    public void delete(Predicate<Map<String, Object>> filter) {
        documents.removeIf(filter);
    }

    public void printAll() {
        documents.forEach(System.out::println);
    }
}
