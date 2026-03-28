package LowLevelDesign.MysqlDbExample;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class InMemoryMySQL implements Database {
    Map<String, Table> tables = new HashMap<>();

    @Override
    public void createTable(String tableName, List<Column> columns) {
        tables.put(tableName, new Table(tableName, columns));
    }

    @Override
    public void insert(String tableName, Map<String, Object> values) {
        tables.get(tableName).insert(values);
    }

    @Override
    public List<Row> select(String tableName, Predicate<Row> filter) {
        return tables.get(tableName).select(filter);
    }


    @Override
    public void update(String tableName, Predicate<Row> filter, Map<String, Object> updates) {
        tables.get(tableName).update(filter, updates);
    }

    @Override
    public void delete(String tableName, Predicate<Row> filter) {
        tables.get(tableName).delete(filter);
    }

    @Override
    public void printAll(String tableName) {
        System.out.println("Table: " + tableName);
        tables.get(tableName).printAll();
    }
}
