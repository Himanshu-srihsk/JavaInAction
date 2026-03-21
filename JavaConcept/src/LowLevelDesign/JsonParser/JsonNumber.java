package LowLevelDesign.JsonParser;

public class JsonNumber implements JsonElement {
    private Number value;

    public JsonNumber(Number value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }
}
