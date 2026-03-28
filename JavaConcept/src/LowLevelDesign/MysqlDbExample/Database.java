package LowLevelDesign.MysqlDbExample;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public interface Database {
    void createTable(String tableName, List<Column> columns);
    void insert(String tableName, Map<String, Object> values);
    List<Row> select(String tableName, Predicate<Row> filter);
    void update(String tableName, Predicate<Row> filter, Map<String, Object> updates);
    void delete(String tableName, Predicate<Row> filter);
    void printAll(String tableName);
}
