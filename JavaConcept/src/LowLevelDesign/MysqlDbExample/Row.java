package LowLevelDesign.MysqlDbExample;

import java.util.HashMap;
import java.util.Map;

public class Row {
    private final Map<String, Object> data = new HashMap<>();

    public void set(String column, Object value) {
        data.put(column, value);
    }

    public Object get(String column) {
        return data.get(column);
    }

    public Map<String, Object> getData() {
        return data;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
