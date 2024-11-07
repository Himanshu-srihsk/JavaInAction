package Reflection_and_Annotations.EnumbasedConfigurationLoader;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private Properties properties;
    public ConfigLoader(String propertyFileName) {
        properties = new Properties();
        try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertyFileName)){
            if (inputStream == null) {
                throw new IllegalArgumentException("Properties file not found: " + propertyFileName);
            }
            properties.load(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("Unable to load properties from file", e);
        }
    }
    public void loadConfigValues() throws NoSuchFieldException {
        for(AppConfig config: AppConfig.values()){
           Config annotation = config.getClass().getDeclaredField(config.name()).getAnnotation(Config.class);
           if(annotation !=null){
             String key = annotation.key();
             String value = properties.getProperty(key);
             if(value != null){
                 config.setValue(value);
                 System.out.println("Loaded value for " + config.name() + ": " + config.getValue());
             }else{
                 System.out.println("Warning: No value found for key: " + key);
             }
           }
        }
    }

}
