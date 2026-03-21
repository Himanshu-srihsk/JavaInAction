package LowLevelDesign.JsonParser;

// Features Required:
// Parse JSON: Read and interpret JSON data.
// Handle Different Types: Support various data types (string, number, boolean, array, object, null).
// Error Handling: Gracefully handle syntax errors and unexpected input.
// Nested Structures: Support nested JSON structures.
// Flexible Parsing: Allow parsing of partial JSON data.
// Object Mapping: Convert JSON data into a usable object structure.


public class Main {
    public static void main(String[] args) {
        String jsonString = "{ \"name\": \"John\", \"age\": 30, \"city\": \"New York\", \"isAdmin\": true, \"scores\": [10, 20, 30] }";
        System.out.println(jsonString);
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(jsonString);

        System.out.println(jsonElement.getValue());
    }
}
/*
{ "name": "John", "age": 30, "city": "New York", "isAdmin": true, "scores": [10, 20, 30] }
{city=New York, scores=[10, 20, 30], name=John, isAdmin=true, age=30}
 */