package br.com.escconsulting.repository.converter;

import br.com.escconsulting.dto.mercado.pago.MPPaymentDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class MPPaymentDTOConverter implements AttributeConverter<MPPaymentDTO, String> {

    @Override
    public String convertToDatabaseColumn(MPPaymentDTO jsonNode) {
        try {
            return new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(jsonNode);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(
                    "Error converting JsonNode to String: " + e.getMessage()
            );
        }
    }

    @Override
    public MPPaymentDTO convertToEntityAttribute(String jsonString) {
        try {
            if (jsonString != null && !jsonString.isEmpty()) {
                return new ObjectMapper()
                        .registerModule(new JavaTimeModule())
                        .readValue(jsonString, MPPaymentDTO.class);
            }
            return null; // Retorna null caso o jsonString seja null ou vazio
        } catch (JsonProcessingException e) {
            throw new RuntimeException(
                    "Error converting String to JsonNode: " + e.getMessage()
            );
        }
    }
}