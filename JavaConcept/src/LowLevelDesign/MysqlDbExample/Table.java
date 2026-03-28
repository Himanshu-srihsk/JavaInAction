package LowLevelDesign.MysqlDbExample;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Table {
    String name;
    List<Column> columns;
    List<Row> rows = new ArrayList<>();
    Set<Object> primaryKeySet = new HashSet<>();
    Set<Object> uniqueEmailSet = new HashSet<>();

    public Table(String name, List<Column> columns) {
        this.name = name;
        this.columns = columns;
    }

    public void insert(Map<String, Object> values) {
        Row row = new Row();
        for (Column column : columns) {
            Object val = values.get(column.name);

            if (column.isPrimaryKey) {
                if (primaryKeySet.contains(val)) {
                    throw new RuntimeException("Duplicate primary key: " + val);
                }
                primaryKeySet.add(val);
            }

            if (column.isUnique && column.name.equals("email")) {
                if (uniqueEmailSet.contains(val)) {
                    throw new RuntimeException("Duplicate unique field: " + val);
                }
                uniqueEmailSet.add(val);
            }

            row.set(column.name, val);
        }
        rows.add(row);
    }

    public List<Row> select(Predicate<Row> condition) {
        return rows.stream().filter(condition).collect(Collectors.toList());
    }

    public void update(Predicate<Row> condition, Map<String, Object> updates) {
        for (Row row : rows) {
            if (condition.test(row)) {
                //  updates.forEach(row::set);
                updates.forEach(row::set);
            }
        }
    }

    public void delete(Predicate<Row> condition) {
        rows.removeIf(condition);
    }

    public void printAll() {
        rows.forEach(System.out::println);
    }
}
