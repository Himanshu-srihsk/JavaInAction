package LowLevelDesign.JsonParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonParser {
    private int index;
    private String json;

    // Constants
    private static final char OPEN_CURLY_BRACE = '{';
    private static final char CLOSE_CURLY_BRACE = '}';
    private static final char OPEN_SQUARE_BRACKET = '[';
    private static final char CLOSE_SQUARE_BRACKET = ']';
    private static final char DOUBLE_QUOTE = '"';
    private static final char COLON = ':';
    private static final char COMMA = ',';

    public JsonElement parse(String jsonString) {
        this.index = 0;
        this.json = jsonString;
        skipWhitespace();
        return parseValue();
    }

    private JsonElement parseValue() {
        char currentChar = json.charAt(index);

        if (currentChar == OPEN_CURLY_BRACE) {
            return parseObject();
        } else if (currentChar == OPEN_SQUARE_BRACKET) {
            return parseArray();
        } else if (currentChar == DOUBLE_QUOTE) {
            return parseString();
        } else if (Character.isDigit(currentChar) || currentChar == '-') {
            return parseNumber();
        } else if (currentChar == 't' || currentChar == 'f') {
            return parseBoolean();
        } else if (currentChar == 'n') {
            return parseNull();
        }

        throw new RuntimeException("Invalid JSON");
    }

    private JsonObject parseObject() {
        Map<String, JsonElement> properties = new HashMap<>();

        // Consume the opening curly brace
        consume(OPEN_CURLY_BRACE);

        skipWhitespace();
        while (json.charAt(index) != CLOSE_CURLY_BRACE) {
            // Parse property name
            String propertyName = parseString().getValue().toString();
            skipWhitespace();

            // Consume the colon
            consume(COLON);
            skipWhitespace();

            // Parse property value
            JsonElement propertyValue = parseValue();
            properties.put(propertyName, propertyValue);

            skipWhitespace();

            // Check for a comma, indicating more properties
            if (json.charAt(index) == COMMA) {
                consume(COMMA);
                skipWhitespace();
            }
        }

        // Consume the closing curly brace
        consume(CLOSE_CURLY_BRACE);

        return new JsonObject(properties);
    }

    private JsonArray parseArray() {
        List<JsonElement> elements = new ArrayList<>();

        // Consume the opening square bracket
        consume(OPEN_SQUARE_BRACKET);

        skipWhitespace();
        while (json.charAt(index) != CLOSE_SQUARE_BRACKET) {
            // Parse array element
            JsonElement element = parseValue();
            elements.add(element);

            skipWhitespace();

            // Check for a comma, indicating more elements
            if (json.charAt(index) == COMMA) {
                consume(COMMA);
                skipWhitespace();
            }
        }

        // Consume the closing square bracket
        consume(CLOSE_SQUARE_BRACKET);

        return new JsonArray(elements);
    }

    private JsonString parseString() {
        // Consume the opening double quote
        consume(DOUBLE_QUOTE);

        StringBuilder sb = new StringBuilder();
        while (json.charAt(index) != DOUBLE_QUOTE) {
            sb.append(json.charAt(index));
            index++;
        }

        // Consume the closing double quote
        consume(DOUBLE_QUOTE);

        return new JsonString(sb.toString());
    }

    private JsonNumber parseNumber() {
        int startIndex = index;

        // Consume digits and optional decimal point
        while (Character.isDigit(json.charAt(index)) || json.charAt(index) == '.') {
            index++;
        }

        String numberStr = json.substring(startIndex, index);
        if (numberStr.contains(".")) {
            return new JsonNumber(Double.parseDouble(numberStr));
        } else {
            return new JsonNumber(Long.parseLong(numberStr));
        }
    }

    private JsonBoolean parseBoolean() {
        String boolStr = consumeWord();
        if (boolStr.equals("true")) {
            return new JsonBoolean(true);
        } else if (boolStr.equals("false")) {
            return new JsonBoolean(false);
        }

        throw new RuntimeException("Invalid boolean value");
    }

    private JsonElement parseNull() {
        consumeWord(); // Consume "null"
        return null;
    }

    private String consumeWord() {
        StringBuilder sb = new StringBuilder();
        while (Character.isLetter(json.charAt(index))) {
            sb.append(json.charAt(index));
            index++;
        }
        return sb.toString();
    }

    private void consume(char expected) {
        if (json.charAt(index) == expected) {
            index++;
        } else {
            throw new RuntimeException("Expected: " + expected);
        }
    }

    private void skipWhitespace() {
        while (Character.isWhitespace(json.charAt(index))) {
            index++;
        }
    }
}