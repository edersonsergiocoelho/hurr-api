package br.com.escconsulting.repository.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class JsonNodeConverter implements AttributeConverter<JsonNode, String> {

    @Override
    public String convertToDatabaseColumn(JsonNode jsonNode) {
        try {
            return new ObjectMapper().writeValueAsString(jsonNode);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(
                    "Error converting JsonNode to String: " + e.getMessage()
            );
        }
    }

    @Override
    public JsonNode convertToEntityAttribute(String jsonString) {
        try {
            return new ObjectMapper().readValue(jsonString, JsonNode.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(
                    "Error converting String to JsonNode: " + e.getMessage()
            );
        }
    }
}