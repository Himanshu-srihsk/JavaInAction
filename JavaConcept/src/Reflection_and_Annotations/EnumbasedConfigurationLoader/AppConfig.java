package Reflection_and_Annotations.EnumbasedConfigurationLoader;

public enum AppConfig {
    @Config(key = "app.name")
    APP_NAME,
    @Config(key = "app.version")
    APP_CONFIG,
    @Config(key = "app.environment")
    APP_ENVIRONMENT;
    private String value;
    public String getValue() {
        return value;
    }
    void setValue(String value) {
        this.value = value;
    }
}
