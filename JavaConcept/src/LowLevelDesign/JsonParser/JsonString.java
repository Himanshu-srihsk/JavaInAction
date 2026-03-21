package LowLevelDesign.JsonParser;

public class JsonString implements JsonElement {
    private String value;

    public JsonString(String value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }
}
