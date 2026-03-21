package LowLevelDesign.JsonParser;

public class JsonBoolean implements JsonElement {
    private Boolean value;

    public JsonBoolean(Boolean value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }
}
