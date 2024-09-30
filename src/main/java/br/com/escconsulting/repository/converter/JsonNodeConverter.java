package br.com.escconsulting.repository.converter;

import br.com.escconsulting.entity.mercado.pago.MercadoPagoPayment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class JsonNodeConverter implements AttributeConverter<MercadoPagoPayment, String> {

    @Override
    public String convertToDatabaseColumn(MercadoPagoPayment jsonNode) {
        try {
            return new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(jsonNode);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(
                    "Error converting JsonNode to String: " + e.getMessage()
            );
        }
    }

    @Override
    public MercadoPagoPayment convertToEntityAttribute(String jsonString) {
        try {
            return new ObjectMapper().registerModule(new JavaTimeModule()).readValue(jsonString, MercadoPagoPayment.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(
                    "Error converting String to JsonNode: " + e.getMessage()
            );
        }
    }
}