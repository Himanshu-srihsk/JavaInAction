package LowLevelDesign.JsonParser;

import java.util.HashMap;
import java.util.Map;

public class JsonObject implements JsonElement {
    private Map<String, JsonElement> properties;

    public JsonObject(Map<String, JsonElement> properties) {
        this.properties = properties;
    }

    public Object getValue() {
        Map<String, Object> result = new HashMap<>();
        properties.forEach((key, value) -> result.put(key, value.getValue()));
        return result;
    }
}
