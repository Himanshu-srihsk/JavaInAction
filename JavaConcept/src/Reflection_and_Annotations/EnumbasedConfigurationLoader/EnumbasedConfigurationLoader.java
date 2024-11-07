package Reflection_and_Annotations.EnumbasedConfigurationLoader;

/*Enum-based Configuration Loader
Define an annotation @Config that takes a key as a parameter.
Create an enum AppConfig where each enum constant represents a configuration setting, annotated with @Config.
Write a loader that:
Reads configurations from a properties file.
Populates the enum constants with values based on the annotation keys.*/

public class EnumbasedConfigurationLoader {
    public static void main(String[] args) throws NoSuchFieldException {
         ConfigLoader configLoader = new ConfigLoader("Reflection_and_Annotations/EnumbasedConfigurationLoader/Config.properties");
         configLoader.loadConfigValues();

         System.out.println("configuration properties loaded successfully");
         for(AppConfig config: AppConfig.values()){
             System.out.println(config.name() + ": " + config.getValue());
         }
    }
}
